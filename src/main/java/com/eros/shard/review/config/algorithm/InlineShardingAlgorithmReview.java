package com.eros.shard.review.config.algorithm;

import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.core.strategy.route.inline.InlineShardingStrategy;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationProperties;

import java.util.Collection;

/**
 * @author xuwentao
 * @Date: 2021/4/1 10:25
 * @Description:
 *
 * 请注意！！！ 不存在InlineShardingAlgorithm, 其他几类都是算法包含在策略中
 * 这里使用{@link InlineShardingStrategyConfiguration#InlineShardingStrategyConfiguration(String shardingColumn, String algorithmExpression)}
 * 会通过解析 algorithmExpression 生成最后的策略
 * {@link InlineShardingStrategy} 直接含有{@link InlineShardingStrategy#doSharding(Collection availableTargetNames, Collection shardingValues, ConfigurationProperties properties)}
 * 参数:
 *      availableTargetNames: 如果是db 例如: db0、db1这样的集合
 *      shardingValues: 分片键的集合
 *      properties: 系统级全局配置中 allow.range.query.with.inline.sharding 是否存在
 */
public class InlineShardingAlgorithmReview {
}
