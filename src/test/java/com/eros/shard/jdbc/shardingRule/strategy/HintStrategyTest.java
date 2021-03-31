package com.eros.shard.jdbc.shardingRule.strategy;

import com.eros.shard.jdbc.shardingRule.ShardingRuleConfigTestImpl;
import com.eros.shard.jdbc.shardingRule.strategy.algorithm.DbHintShardingAlgorithm;
import com.eros.shard.jdbc.shardingRule.strategy.algorithm.TableHintShardingAlgorithm;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.hint.HintManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author xuwentao
 * @Date: 2021/3/31 15:59
 * @Description:
 */
public class HintStrategyTest extends ShardingRuleConfigTestImpl {

    private final static Logger logger = LoggerFactory.getLogger(HintStrategyTest.class);

    @Override
    public ShardingRuleConfiguration shardingRuleConfiguration() {
        // todo hint 分片算法 需要配置, 直接指到 HintShardingValue
        HintManager hintManager = HintManager.getInstance();
        hintManager.addDatabaseShardingValue("qmai_order", 2);
        hintManager.addTableShardingValue("qmai_order", 2);

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 表规则配置
        List<TableRuleConfiguration> tableRuleConfigurationList = tableRuleConfigurationList();
        shardingRuleConfig.setTableRuleConfigs(tableRuleConfigurationList);
        // 默认分库策略配置
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new HintShardingStrategyConfiguration(new DbHintShardingAlgorithm()));
        // 默认分表策略配置
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new HintShardingStrategyConfiguration(new TableHintShardingAlgorithm()));
        return shardingRuleConfig;
    }

}
