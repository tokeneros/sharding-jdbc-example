package com.eros.shard.jdbc;

import com.eros.shard.config.AbstractPreciseShardingAlgorithm;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationPropertyKey;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author xuwentao
 * @Date: 2021/3/29 17:34
 * @Description:
 */
public class ShardJdbcDataSource {

    public final static String tables = "qmai_order";

    protected List<String> getTable() {
        String[] split = tables.split(",");
        List<String> objects = new ArrayList<>();
        Collections.addAll(objects, split);
        return objects;
    }


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

    /**
     * 配置数据源
     *
     * @return
     */
    private Map<String, DataSource> dataSource() {
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
        return dataSourceMap;
    }

    /**
     * 配置分片规则
     *
     * @return
     */
    private ShardingRuleConfiguration shardingRuleConfiguration() {
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

    /**
     * sharding-jdbc 的数据源，支持分表分库
     * @return
     */
    public DataSource shardingSphereDataSource(ShardingRuleConfiguration shardingRuleConfig){
        Map<String, DataSource> dataSourceMap = dataSource();
        DataSource dataSource = null;
        // 创建 ShardingSphereDataSource
        try {
            Properties properties = new Properties();
            // 打印sql语句
            properties.put("sql.show", "true");
            properties.put("sql.simple", "false");
            properties.put(ConfigurationPropertyKey.CHECK_TABLE_METADATA_ENABLED.getKey(), "true");
            dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    public static void main(String[] args) {

    }

}
