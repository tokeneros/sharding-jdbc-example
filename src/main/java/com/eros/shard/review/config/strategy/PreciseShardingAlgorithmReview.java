package com.eros.shard.review.config.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author xuwentao
 * @Date: 2021/3/31 17:48
 * @Description:
 *
 * @see PreciseShardingAlgorithm 主要处理 in 和 =
 * 常规 分片算法
 *
 * @see PreciseShardingAlgorithm#doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue)
 * 分片执行
 * 参数:
 *      availableTargetNames: 如果是db 例如: db0、db1这样的集合
 *      shardingValue: 通过sql解析引擎获取到字段对应的具体值
 *
 * 例子: availableTargetNames: ["ds0","ds1"], shardingValue: {"columnName":"user_id","logicTableName":"qmai_order","value":4}
 */
public class PreciseShardingAlgorithmReview {
}
