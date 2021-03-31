package com.eros.shard.jdbc.shardingRule.strategy;

import com.eros.shard.jdbc.shardingRule.ShardingRuleConfigTestImpl;
import com.eros.shard.jdbc.shardingRule.strategy.algorithm.DbHintShardingAlgorithm;
import com.eros.shard.jdbc.shardingRule.strategy.algorithm.TableHintShardingAlgorithm;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.hint.HintManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author xuwentao
 * @Date: 2021/3/31 17:41
 * @Description:
 */
public class NoneStrategyTest extends ShardingRuleConfigTestImpl {

    private final static Logger logger = LoggerFactory.getLogger(NoneStrategyTest.class);

    @Override
    public ShardingRuleConfiguration shardingRuleConfiguration() {
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 表规则配置
        List<TableRuleConfiguration> tableRuleConfigurationList = tableRuleConfigurationList();
        shardingRuleConfig.setTableRuleConfigs(tableRuleConfigurationList);
        // 默认分库策略配置
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        // 默认分表策略配置
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        return shardingRuleConfig;
    }

}
