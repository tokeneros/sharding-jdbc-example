package com.eros.shard.review.config;

import org.apache.shardingsphere.api.config.sharding.strategy.*;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;

/**
 * @author xuwentao
 * @Date: 2021/3/31 14:59
 * @Description:
 *
 * @see ShardingStrategyConfiguration
 * 分片策略配置 - 接口
 * 下方是官方提供的实现类
 *
 * @see HintShardingStrategyConfiguration#HintShardingStrategyConfiguration(HintShardingAlgorithm) 
 * hint 分片策略 {@link com.eros.shard.review.config.algorithm.HintShardingAlgorithmReview}
 *
 * @see ComplexShardingStrategyConfiguration#ComplexShardingStrategyConfiguration(String, ComplexKeysShardingAlgorithm)
 * 复合分片策略 - 多分片键 todo 无场景测试
 *
 * @see NoneShardingStrategyConfiguration#NoneShardingStrategyConfiguration()
 * 无分片策略 - 执行全路由
 *
 * @see StandardShardingStrategyConfiguration#StandardShardingStrategyConfiguration(String, PreciseShardingAlgorithm)
 * 标准分片策略 - sql 解析引擎 分析到=和in的路由 {@link com.eros.shard.review.config.algorithm.PreciseShardingAlgorithmReview}
 * 
 * @see StandardShardingStrategyConfiguration#StandardShardingStrategyConfiguration(String, PreciseShardingAlgorithm, RangeShardingAlgorithm)
 * 标准分片策略 - sql 解析引擎 分析Between and 的路由 {@link com.eros.shard.review.config.algorithm.RangeShardingAlgorithmReview}
 *
 * @see InlineShardingStrategyConfiguration#InlineShardingStrategyConfiguration(String, String)
 * 内联分片策略 - todo 未研究
 */
public class ShardingStrategyConfigurationReview {


}
