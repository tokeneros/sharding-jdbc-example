package com.eros.shard.jdbc.shardingRule.strategy;

import com.eros.shard.jdbc.shardingRule.ShardingRuleConfigTestImpl;
import com.eros.shard.jdbc.shardingRule.strategy.algorithm.DbPreciseShardingAlgorithm;
import com.eros.shard.jdbc.shardingRule.strategy.algorithm.TablePreciseShardingAlgorithm;
import com.eros.shard.jdbc.shardingRule.strategy.algorithm.TableRangeShardingAlgorithm;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author xuwentao
 * @Date: 2021/4/1 09:25
 * @Description:
 */
public class StandardStrategyTest  extends ShardingRuleConfigTestImpl {

    private final static Logger logger = LoggerFactory.getLogger(StandardStrategyTest.class);

    @Override
    public ShardingRuleConfiguration shardingRuleConfiguration() {
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 表规则配置
        List<TableRuleConfiguration> tableRuleConfigurationList = tableRuleConfigurationList();
        shardingRuleConfig.setTableRuleConfigs(tableRuleConfigurationList);
        // 默认分库策略配置
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new DbPreciseShardingAlgorithm(), new TableRangeShardingAlgorithm()));
        // 默认分表策略配置
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new TablePreciseShardingAlgorithm(), new TableRangeShardingAlgorithm()));
        return shardingRuleConfig;
    }
}
