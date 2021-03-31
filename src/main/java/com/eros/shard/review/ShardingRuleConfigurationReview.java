package com.eros.shard.review;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptRuleConfiguration;

import java.util.Collection;

/**
 * @author xuwentao
 * @Date: 2021/3/31 14:15
 * @Description:
 *
 * @see ShardingRuleConfiguration
 * 分片规则配置 - 我们通过此配置来决定分表分库的算法和结果
 *
 * @see ShardingRuleConfiguration#setDefaultTableShardingStrategyConfig(ShardingStrategyConfiguration) - 常用
 * {@link ShardingStrategyConfigurationReview}
 * 设置默认表级别分片策略
 *
 * @see ShardingRuleConfiguration#setDefaultDatabaseShardingStrategyConfig(ShardingStrategyConfiguration) - 常用
 * 设置默认库级别分片策略
 *
 * @see ShardingRuleConfiguration#setDefaultDataSourceName(String)
 * 设置默认数据源名称 - 主要为了解决某些基础表，数据量不大，我们不需要分表分库，请求默认数据源，
 * todo 具体使用方式未知
 *
 * @see ShardingRuleConfiguration#setTableRuleConfigs(Collection) - 常用
 * {@link TableRuleConfigurationReview}
 * 设置表规则集 - 我们sql操作的是表，拦截sql后，使用sharding-jdbc特有的解析引擎对其进行拆分，找出具体表，之后按照表规则来进行匹配
 *
 * @see ShardingRuleConfiguration#setEncryptRuleConfig(EncryptRuleConfiguration)
 * 设置加密配置 - 底层有些字段需要进行加密处理，如密码等敏感数据
 *
 * @see ShardingRuleConfiguration#setDefaultKeyGeneratorConfig(KeyGeneratorConfiguration)
 * 设置默认主键生成器
 *      a. 常规来说，我们需要在业务中操作主键，不可能等到数据插入后获取
 * todo 具体使用方式未知
 *
 * @see ShardingRuleConfiguration#setMasterSlaveRuleConfigs(Collection)
 * 设置主从配置规则
 * todo 具体使用方式未知
 *
 * @see ShardingRuleConfiguration#setBindingTableGroups(Collection)
 * 设置绑定表
 * todo 具体使用方式未知
 *
 * @see ShardingRuleConfiguration#setBroadcastTables(Collection)
 * 设置广播表
 * todo 具体使用方式未知
 */
public class ShardingRuleConfigurationReview {
}
