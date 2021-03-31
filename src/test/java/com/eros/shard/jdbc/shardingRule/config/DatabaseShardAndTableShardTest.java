package com.eros.shard.jdbc.shardingRule.config;

import com.eros.shard.jdbc.ShardJdbcDataSource;
import com.eros.shard.jdbc.shardingAlgorithm.DbPreciseShardingAlgorithm;
import com.eros.shard.jdbc.shardingAlgorithm.UserPreciseShardingAlgorithm;
import com.eros.shard.jdbc.shardingRule.ShardingRuleConfigTest;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.resultset.ShardingResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * @author xuwentao
 * @Date: 2021/3/29 17:47
 * @Description:
 * Table 'spider_0.qmai_order' doesn't exist
 * 因为无法提供分表的 策略，所以找不到对应的表
 *
 * 配置 默认分表策略配置 无法解决此问题
 *
 */
public class DatabaseShardAndTableShardTest extends ShardJdbcDataSource implements ShardingRuleConfigTest {

    private final static Logger logger = LoggerFactory.getLogger(DatabaseShardAndTableShardTest.class);

    /**
     * 这里需要 会根据actualDataNodes 拆分数据节点
     * 多个表封装, 每个表可以配置独特的参数信息，也可以统一使用默认的分表策略
     * @return
     */
    public List<TableRuleConfiguration> tableRuleConfigurationList() {
        List<String> tables = getTable();
        List<TableRuleConfiguration> tableRuleConfigurationList = new ArrayList<>();
        for (String table : tables) {
            TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration(table, String.format("ds${0..1}.%s_${0..1}", table));
            tableRuleConfigurationList.add(tableRuleConfiguration);
        }
        return tableRuleConfigurationList;
    }

    @Override
    public ShardingRuleConfiguration shardingRuleConfiguration() {
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 表规则配置 todo 不配置表规则配置，默认分表策略不会激活
        List<TableRuleConfiguration> tableRuleConfigurationList = tableRuleConfigurationList();
        shardingRuleConfig.setTableRuleConfigs(tableRuleConfigurationList);
        // 绑定表组配置
//        shardingRuleConfig.setBindingTableGroups();
        // 广播表配置
//        shardingRuleConfig.setBroadcastTables();
        // 默认数据库名称
//        shardingRuleConfig.setDefaultDataSourceName("ds0");
        // 默认分库策略配置
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",
                new DbPreciseShardingAlgorithm()));
        // 默认分表策略配置
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",
                new UserPreciseShardingAlgorithm()));
        // 默认主键生成配置
//        shardingRuleConfig.setDefaultKeyGeneratorConfig();
        // 主从规则配置
//        shardingRuleConfig.setMasterSlaveRuleConfigs();
        // 加密规则配置
//        shardingRuleConfig.setEncryptRuleConfig();
        return shardingRuleConfig;
    }


    @Override
    public void insert(DataSource dataSource, Connection connection) throws SQLException {
        String sql = "INSERT INTO qmai_order (`id`, `user_id`, `order_id`, `order_msg`) VALUES (1, 2, 1, '订单2')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        if(i > 0) {
            logger.info("本次执行成功, 新增条数: {}", i);
        } else {
            logger.info("本次执行失败");
        }
    }

    @Override
    public void select(DataSource dataSource, Connection connection) throws SQLException {
//        Map<String, DataSource> stringDataSourceMap = ShardJdbcDataSource.dataSource();
//        DataSource ds0 = stringDataSourceMap.get("ds0");
//        Connection connection1 = ds0.getConnection();
        String sql = "SELECT `id`, `user_id`, `order_id`, `order_msg` FROM qmai_order ORDER BY user_id LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
        // todo 最后返回的 ShardingResultSet 并不是最终结果，其中包含ResultSets，没有归并最终集合吗
        ShardingResultSet resultSet = (ShardingResultSet)preparedStatement.getResultSet();
        List<ResultSet> resultSets = resultSet.getResultSets();
        for (ResultSet set : resultSets) {
            // 将光标移动到最后一行
            if(set.last()) {
                int rowCount = set.getRow(); // 得到当前行号，即结果集记录数
                logger.info("本次执行成功, 库: {} 表: {} 查询条数: {}", set.getMetaData().getCatalogName(1), set.getMetaData().getTableName(1), rowCount);
            } else {
                logger.info("本次执行失败, 查询条数为空");
            }
        }

        System.out.println(resultSets.size());
        // 将光标移动到最后一行
//        if(resultSet.last()) {
//            int rowCount = resultSet.getRow(); // 得到当前行号，即结果集记录数
//            logger.info("本次执行成功, 查询条数: {}", rowCount);
//        } else {
//            logger.info("本次执行失败, 查询条数为空");
//        }
    }

    @Override
    public void delete(DataSource dataSource, Connection connection) throws SQLException {
        String sql = "DELETE FROM qmai_order WHERE id = 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        if(i > 0) {
            logger.info("本次执行成功, 删除条数: {}", i);
        } else {
            logger.info("本次执行失败");
        }
    }

    @Override
    public void update(DataSource dataSource, Connection connection) throws SQLException {
        String sql = "UPDATE qmai_order SET `order_msg` = '订单1' WHERE user_id = 2";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        if(i > 0) {
            logger.info("本次执行成功, 修改条数: {}", i);
        } else {
            logger.info("本次执行失败");
        }
    }

    public static void main(String[] args) {
        DatabaseShardAndTableShardTest defaultDataSourceTest = new DatabaseShardAndTableShardTest();
        DataSource dataSource = defaultDataSourceTest.shardingSphereDataSource(defaultDataSourceTest.shardingRuleConfiguration());
        try (Connection connection = dataSource.getConnection();){
//            defaultDataSourceTest.insert(dataSource, connection);
            // TODO 当查询条件 不包括 user_id, 没办法通过分片算法达到目的， 需要额外处理
            // HintManager强制分片
            defaultDataSourceTest.select(dataSource, connection);
//            defaultDataSourceTest.update(dataSource, connection);
            // 删除 如果不包括 user_id ,即会删除所有表，形成笛卡尔集删除
//            defaultDataSourceTest.delete(dataSource, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
