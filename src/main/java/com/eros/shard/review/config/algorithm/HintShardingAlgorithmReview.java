package com.eros.shard.review.config.algorithm;

import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;

/**
 * @author xuwentao
 * @Date: 2021/3/31 16:09
 * @Description:
 *
 * @see HintShardingAlgorithm
 * hint 分片算法
 * 需要和 {@link HintManager} 一起使用, 相当于主键 {@link TableRuleConfiguration#getLogicTable()},
 * 然后设置一个值，作为路由引擎计算的值，后面解析sql，不以字段对应值来作为后一步路由计算的值
 *      {@link HintManager#addDatabaseShardingValue(String, Comparable)} 添加数据库hint 值
 *      {@link HintManager#addTableShardingValue(String, Comparable)} 添加表hint 值
 *
 * @see HintShardingAlgorithm#doSharding(Collection availableTargetNames, HintShardingValue hintShardingValue)
 * 分片执行
 * 参数:
 *      availableTargetNames: 如果是db 例如: db0、db1这样的集合
 *      hintShardingValue: 上一步HintManager 设置的值
 *
 * 例子: availableTargetNames: ["ds0","ds1"], shardingValue: {"columnName":"","logicTableName":"qmai_order","values":["0"]}
 *
 */
public class HintShardingAlgorithmReview {
}
