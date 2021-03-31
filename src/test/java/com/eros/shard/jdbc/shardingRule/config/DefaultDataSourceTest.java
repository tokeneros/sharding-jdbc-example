package com.eros.shard.jdbc.shardingRule.config;

import com.eros.shard.jdbc.ShardJdbcDataSource;
import com.eros.shard.jdbc.shardingRule.ShardingRuleConfigTest;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author xuwentao
 * @Date: 2021/3/29 17:47
 * @Description:
 * Table 'spider_0.qmai_order' doesn't exist
 * 因为无法提供分表的 策略，所以找不到对应的表
 *
 */
public class DefaultDataSourceTest extends ShardJdbcDataSource implements ShardingRuleConfigTest {

    private final static Logger logger = LoggerFactory.getLogger(DefaultDataSourceTest.class);

    @Override
    public ShardingRuleConfiguration shardingRuleConfiguration() {
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 表规则配置
//        shardingRuleConfig.setTableRuleConfigs();
        // 绑定表组配置
//        shardingRuleConfig.setBindingTableGroups();
        // 广播表配置
//        shardingRuleConfig.setBroadcastTables();
        // 默认数据库名称
        shardingRuleConfig.setDefaultDataSourceName("ds0");
        // 默认分库策略配置
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig();
        // 默认分表策略配置
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig();
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
        String sql = "INSERT INTO qmai_order (`id`, `user_id`, `order_id`, `order_msg`) VALUES (4, 4, 4, '订单2')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        if(i > 0) {
            logger.info("本次执行成功, 新增条数: {}", i);
        } else {
            logger.info("本次执行失败");
        }
    }

    @Override
    public void select(DataSource dataSource, Connection connection) {

    }

    @Override
    public void delete(DataSource dataSource, Connection connection) {

    }

    @Override
    public void update(DataSource dataSource, Connection connection) {

    }

    public static void main(String[] args) {
        DefaultDataSourceTest defaultDataSourceTest = new DefaultDataSourceTest();
        DataSource dataSource = defaultDataSourceTest.shardingSphereDataSource(defaultDataSourceTest.shardingRuleConfiguration());
        try (Connection connection = dataSource.getConnection();){
            defaultDataSourceTest.insert(dataSource, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
