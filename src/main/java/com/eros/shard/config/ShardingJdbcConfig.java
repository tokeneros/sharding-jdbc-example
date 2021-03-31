package com.eros.shard.config;

import com.alibaba.fastjson.JSON;
import com.eros.shard.config.properties.DataSourceProperties;
import com.eros.shard.config.properties.ShardingJdbcProperties;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.api.sharding.ShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.statement.ShardingPreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * @author xuwentao
 * @Date: 2021/3/29 11:02
 * @Description:
 */
@Component
public class ShardingJdbcConfig {

    @Autowired
    private ShardingJdbcProperties shardingJdbcProperties;

    public Map<String, HikariDataSource> hikariDataSource() throws InterruptedException {
        Map<String, HikariDataSource> hikariDataSources = new HashMap<>();
        List<DataSourceProperties> dataSources = shardingJdbcProperties.getDataSources();
        if(dataSources.size() < 1) {
            throw new InterruptedException("数据库配置信息为空");
        }
        for (DataSourceProperties dataSource : dataSources) {
            HikariDataSource druidDataSource = new HikariDataSource();
            druidDataSource.setPassword(dataSource.getPassword());
            druidDataSource.setUsername(dataSource.getUsername());
            druidDataSource.setDriverClassName(dataSource.getDriverClassName());
            druidDataSource.setJdbcUrl(dataSource.getUrl());
            hikariDataSources.put(dataSource.getDbName(), druidDataSource);
        }
        return hikariDataSources;
    }

    public static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        // 获取列数
        int ColumnCount = resultSetMetaData.getColumnCount();
        // 保存当前列最大长度的数组
        int[] columnMaxLengths = new int[ColumnCount];
        // 缓存结果集,结果集可能有序,所以用ArrayList保存变得打乱顺序.
        ArrayList<String[]> results = new ArrayList<>();
        // 按行遍历
        while (rs.next()) {
            // 保存当前行所有列
            String[] columnStr = new String[ColumnCount];
            // 获取属性值.
            for (int i = 0; i < ColumnCount; i++) {
                // 获取一列
                columnStr[i] = rs.getString(i + 1);
                // 计算当前列的最大长度
                columnMaxLengths[i] = Math.max(columnMaxLengths[i], (columnStr[i] == null) ? 0 : columnStr[i].length());
            }
            // 缓存这一行.
            results.add(columnStr);
        }
        printSeparator(columnMaxLengths);
        printColumnName(resultSetMetaData, columnMaxLengths);
        printSeparator(columnMaxLengths);
        // 遍历集合输出结果
        Iterator<String[]> iterator = results.iterator();
        String[] columnStr;
        while (iterator.hasNext()) {
            columnStr = iterator.next();
            for (int i = 0; i < ColumnCount; i++) {
                // System.out.printf("|%" + (columnMaxLengths[i] + 1) + "s", columnStr[i]);
                System.out.printf("|%" + columnMaxLengths[i] + "s", columnStr[i]);
            }
            System.out.println("|");
        }
        printSeparator(columnMaxLengths);
    }

    /**
     * 输出列名.
     *
     * @param resultSetMetaData 结果集的元数据对象.
     * @param columnMaxLengths  每一列最大长度的字符串的长度.
     * @throws SQLException
     */
    private static void printColumnName(ResultSetMetaData resultSetMetaData, int[] columnMaxLengths) throws SQLException {
        int columnCount = resultSetMetaData.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            // System.out.printf("|%" + (columnMaxLengths[i] + 1) + "s", resultSetMetaData.getColumnName(i + 1));
            System.out.printf("|%s", resultSetMetaData.getColumnName(i + 1));
        }
        System.out.println("|");
    }

    /**
     * 输出分隔符.
     *
     * @param columnMaxLengths 保存结果集中每一列的最长的字符串的长度.
     */
    private static void printSeparator(int[] columnMaxLengths) {
        for (int i = 0; i < columnMaxLengths.length; i++) {
            System.out.print("+");
            // for (int j = 0; j < columnMaxLengths[i] + 1; j++) {
            for (int j = 0; j < columnMaxLengths[i]; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }


    public static void main(String[] args) {
        ShardingJdbcConfig shardingJdbcConfig = new ShardingJdbcConfig();
        DataSource dataSource = shardingJdbcConfig.shardingSphereDataSource();
        shardingJdbcConfig.insert1(dataSource);
//        shardingJdbcConfig.select1(dataSource);
    }

    public void insert1(DataSource dataSource) {
        String sql = "INSERT INTO qmai_order (`id`, `user_id`, `order_id`, `order_msg`) VALUES (4, 4, 4, '订单2');";
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if(pstmt.executeUpdate() > 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void select1(DataSource dataSource){
        String sql = "SELECT o.* FROM qmai_order AS o WHERE o.user_id=? AND o.order_id = ?";
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 4);
            ps.setInt(2, 4);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    printResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void test1() {
        ShardingJdbcConfig shardingJdbcConfig = new ShardingJdbcConfig();
        DataSource dataSource = shardingJdbcConfig.shardingSphereDataSource();
        String sql = "SELECT * FROM t_order";
        try (
                HintManager hintManager = HintManager.getInstance();
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            hintManager.setDatabaseShardingValue(3);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    //...
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public DataSource shardingSphereDataSource(){
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第 1 个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://106.15.170.42:23305/spider_0?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useAffectedRows=true");
        dataSource1.setUsername("root");
        dataSource1.setPassword("initialD");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第 2 个数据源
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://106.15.170.42:23305/spider_1?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useAffectedRows=true");
        dataSource2.setUsername("root");
        dataSource2.setPassword("initialD");
        dataSourceMap.put("ds1", dataSource2);

        // 配置 t_order 表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("qmai_order", "ds${0..1}.qmai_order_${0..1}");

        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new AbstractPreciseShardingAlgorithm()));

        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new AbstractPreciseShardingAlgorithm()));

        // 省略配置 t_order_item 表规则...
        // ...

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.setTableRuleConfigs(Arrays.asList(orderTableRuleConfig));

        // 配置分库算法
//        Properties dbShardingAlgorithmrProps = new Properties();
//        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${user_id % 2}");
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new HintShardingStrategyConfiguration(new HintShardingAlgorithm() {
//            @Override
//            public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
//                return Arrays.asList("ds${user_id % 2}");
//            }
//        }));

        // 配置分表算法
//        Properties tableShardingAlgorithmrProps = new Properties();
//        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "qmai_order_${order_id % 2}");
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new HintShardingStrategyConfiguration(new HintShardingAlgorithm() {
//            @Override
//            public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
//                return Arrays.asList("qmai_order_${order_id % 2}");
//            }
//        }));

        DataSource dataSource = null;
        // 创建 ShardingSphereDataSource
        try {
            dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

//    public ShardingRuleConfiguration shardingRuleConfiguration() {
//        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
////        shardingRuleConfiguration.setDefaultTableShardingStrategyConfig(tableShardingAlgorithm());
//        return shardingRuleConfiguration;
//    }
//
//    public ShardingAlgorithm dbShardingAlgorithm(){
//        ShardingAlgorithm shardingAlgorithm = new HintShardingAlgorithm() {
//            @Override
//            public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
//                return null;
//            }
//        };
//        return shardingAlgorithm;
//    }
//
//    public ShardingAlgorithm tableShardingAlgorithm(){
//        ShardingAlgorithm shardingAlgorithm = new HintShardingAlgorithm() {
//            @Override
//            public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
//                return null;
//            }
//        };
//        return shardingAlgorithm;
//    }

}
