package com.eros.shard.jdbc.shardingRule.strategy;

import com.eros.shard.jdbc.ShardJdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author xuwentao
 * @Date: 2021/3/31 15:56
 * @Description:
 */
public class ShardingStrategyRunnerTest {

    public static void main(String[] args) {
        // hit 分片策略
//        HintStrategyTest hintStrategyTest = new HintStrategyTest();
//        DataSource dataSource = hintStrategyTest.shardingSphereDataSource(hintStrategyTest.shardingRuleConfiguration());
//        try (Connection connection = dataSource.getConnection();){
//            hintStrategyTest.insert(dataSource, connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        // 无 分片策略
        NoneStrategyTest noneStrategyTest = new NoneStrategyTest();
        DataSource dataSource = noneStrategyTest.shardingSphereDataSource(noneStrategyTest.shardingRuleConfiguration());
        try (Connection connection = dataSource.getConnection();){
            noneStrategyTest.insert(dataSource, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
