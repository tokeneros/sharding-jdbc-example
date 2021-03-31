package com.eros.shard.jdbc.shardingRule.config;

import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author xuwentao
 * @Date: 2021/3/29 17:48
 * @Description:
 */
public interface ShardingRuleConfigTest {

    ShardingRuleConfiguration shardingRuleConfiguration();

    void insert(DataSource dataSource, Connection connection) throws SQLException;

    void select(DataSource dataSource, Connection connection) throws SQLException;

    void delete(DataSource dataSource, Connection connection) throws SQLException;

    void update(DataSource dataSource, Connection connection) throws SQLException;

    default void handle(PreparedStatement preparedStatement) {

    }

}
