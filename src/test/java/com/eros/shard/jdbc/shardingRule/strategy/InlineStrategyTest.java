package com.eros.shard.jdbc.shardingRule.strategy;

import com.eros.shard.jdbc.shardingRule.ShardingRuleConfigTestImpl;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author xuwentao
 * @Date: 2021/4/1 09:38
 * @Description:
 */
public class InlineStrategyTest extends ShardingRuleConfigTestImpl {

    private final static Logger logger = LoggerFactory.getLogger(InlineStrategyTest.class);

    @Override
    public ShardingRuleConfiguration shardingRuleConfiguration() {
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 表规则配置
        List<TableRuleConfiguration> tableRuleConfigurationList = tableRuleConfigurationList();
        shardingRuleConfig.setTableRuleConfigs(tableRuleConfigurationList);
        // 默认分库策略配置
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "ds${user_id % (2 * 2) / 2}"));
        // 默认分表策略配置
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "qmai_order_${user_id % 2}"));
        return shardingRuleConfig;
    }

}
