package com.eros.shard.jdbc.shardingRule.strategy.algorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Iterables;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author xuwentao
 * @Date: 2021/3/31 17:06
 * @Description:
 */
public class TableHintShardingAlgorithm implements HintShardingAlgorithm<Integer> {

    private final static Logger logger = LoggerFactory.getLogger(TableHintShardingAlgorithm.class);

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Integer> shardingValue) {
        logger.info("HintStrategyTest[doSharding] availableTargetNames: {}, shardingValue: {}", JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));
        Integer shardingInt = Iterables.getOnlyElement(shardingValue.getValues());
        List<String> results = new ArrayList<>();
        Iterator<String> iterator = availableTargetNames.iterator();
        Integer shardNum = shardingInt % availableTargetNames.size();
        while (iterator.hasNext()) {
            String table = iterator.next();
            if(shardNum-- > 0) {
                continue;
            } else {
                results.add(table);
                break;
            }
        }
        logger.info("HintStrategyTest[doSharding] result: {}", JSON.toJSONString(results));
        return results;
    }

}
