package com.eros.shard.jdbc.shardingRule.strategy.algorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author xuwentao
 * @Date: 2021/3/30 15:48
 * @Description:
 */
public class TableRangeShardingAlgorithm implements RangeShardingAlgorithm<Integer> {

    private final static Logger logger = LoggerFactory.getLogger(TableRangeShardingAlgorithm.class);

    /**
     * 实现between and查询
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        logger.info("TablePreciseShardingAlgorithm[doSharding] params collection: {}, rangeShardingValue: {} ", JSON.toJSONString(collection), JSON.toJSONString(rangeShardingValue));

        Collection<String> collect = new ArrayList<>();
        Range<Integer> valueRange = rangeShardingValue.getValueRange();

        logger.info("valueRange: {}, lowerEndpoint:{}, upperEndpoint:{}", JSON.toJSONString(valueRange),
                valueRange.lowerEndpoint(), valueRange.upperEndpoint());

        for (Integer i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % collection.size() + "")) {
                    collect.add(each);
                }
            }
        }

        logger.info("collect:{}", JSON.toJSONString(collect));
        return collect;
    }
}
