package com.eros.shard.jdbc.shardingRule.strategy.algorithm;

import com.alibaba.fastjson.JSON;
import com.eros.shard.jdbc.shardingRule.utils.ShardingUtils;
import com.google.common.collect.Iterables;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.apache.shardingsphere.core.strategy.route.hint.HintShardingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author xuwentao
 * @Date: 2021/3/31 17:04
 * @Description:
 */
public class DbHintShardingAlgorithm implements HintShardingAlgorithm<Integer> {

    private final static Logger logger = LoggerFactory.getLogger(DbHintShardingAlgorithm.class);

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Integer> shardingValue) {
        // HintStrategyTest[doSharding] availableTargetNames: ["ds0","ds1"], shardingValue: {"columnName":"","logicTableName":"qmai_order","values":["0"]}
        logger.info("HintStrategyTest[doSharding] availableTargetNames: {}, shardingValue: {}", JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));
        Integer shardingInt = Iterables.getOnlyElement(shardingValue.getValues());
        List<String> results = new ArrayList<>();
        Iterator<String> iterator = availableTargetNames.iterator();
        Integer shardNum = ShardingUtils.getShardNum(shardingInt, availableTargetNames.size());
        while (iterator.hasNext()) {
            String db = iterator.next();
            if(shardNum-- > 0) {
                continue;
            } else {
                results.add(db);
                break;
            }
        }
        logger.info("HintStrategyTest[doSharding] result: {}", JSON.toJSONString(results));
        return results;
    }

}
