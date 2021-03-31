package com.eros.shard.jdbc.shardingAlgorithm;

import com.alibaba.fastjson.JSON;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author xuwentao
 * @Date: 2021/3/30 10:17
 * @Description:
 */
public class DbPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    private final static Logger logger = LoggerFactory.getLogger(DbPreciseShardingAlgorithm.class);

    /**
     * 基数
     */
    private final static Integer baseShardNum = 2;

    /**
     * 分片数更改
     *
     * @param shardingValue
     * @param collectionSize
     * @return
     */
    private final static Integer getShardNum(Integer shardingValue, Integer collectionSize) {
        return shardingValue % (baseShardNum * collectionSize) / baseShardNum ;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        // UserPreciseShardingAlgorithm[doSharding] params collection: ["ds0","ds1"], preciseShardingValue: {"columnName":"user_id","logicTableName":"qmai_order","value":4}
        logger.info("DbPreciseShardingAlgorithm[doSharding] params collection: {}, preciseShardingValue: {} ", JSON.toJSONString(collection), JSON.toJSONString(preciseShardingValue));
        Integer shardingValue = preciseShardingValue.getValue();
        String shardingCollection = collection.toArray(new String[collection.size()])[getShardNum(shardingValue, collection.size())];
        logger.info("DbPreciseShardingAlgorithm[doSharding] result {}", shardingCollection);
        return shardingCollection;
    }

}
