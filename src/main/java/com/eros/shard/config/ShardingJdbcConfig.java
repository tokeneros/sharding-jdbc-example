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

}
