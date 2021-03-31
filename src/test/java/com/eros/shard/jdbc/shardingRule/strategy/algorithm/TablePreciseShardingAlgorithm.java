package com.eros.shard.jdbc.shardingRule.strategy.algorithm;

import com.alibaba.fastjson.JSON;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author xuwentao
 * @Date: 2021/3/29 18:12
 * @Description:
 */
public class TablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    private final static Logger logger = LoggerFactory.getLogger(TablePreciseShardingAlgorithm.class);

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        // TablePreciseShardingAlgorithm[doSharding] params collection: ["qmai_order_0","qmai_order_1"], preciseShardingValue: {"columnName":"user_id","logicTableName":"qmai_order","value":4}
        logger.info("TablePreciseShardingAlgorithm[doSharding] params collection: {}, preciseShardingValue: {} ", JSON.toJSONString(collection), JSON.toJSONString(preciseShardingValue));
        Integer shardingValue = preciseShardingValue.getValue();
        String shardingCollection = collection.toArray(new String[collection.size()])[shardingValue % collection.size()];
        logger.info("TablePreciseShardingAlgorithm[doSharding] result {}", shardingCollection);
        return shardingCollection;
    }


}
