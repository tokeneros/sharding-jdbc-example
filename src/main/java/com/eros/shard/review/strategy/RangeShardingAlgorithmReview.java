package com.eros.shard.review.strategy;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * @author xuwentao
 * @Date: 2021/3/31 17:48
 * @Description:
 *
 * @see RangeShardingAlgorithm 处理 BETWEEN AND 分片
 * 常规 分片算法
 *
 * @see RangeShardingAlgorithm#doSharding(Collection availableTargetNames, RangeShardingValue shardingValue)
 * 分片执行
 * 参数:
 *      availableTargetNames: 如果是db 例如: db0、db1这样的集合
 *      shardingValue: 通过sql解析引擎获取到字段对应的具体值 - 其中lowerEndpoint 和 upperEndpoint解析自 BETWEEN lower AND upper
 *
 * 例子: params collection: ["qmai_order_0","qmai_order_1"], rangeShardingValue: {"columnName":"user_id","logicTableName":"qmai_order","valueRange":{"empty":false}}
     *      {@link Range} valueRange: {"empty":false}, lowerEndpoint:500, upperEndpoint:600
 */
public class RangeShardingAlgorithmReview {
}
