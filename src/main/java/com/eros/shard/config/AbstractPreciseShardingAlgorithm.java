package com.eros.shard.config;

import com.alibaba.fastjson.JSON;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author xuwentao
 * @Date: 2021/3/29 13:07
 * @Description:
 */
public class AbstractPreciseShardingAlgorithm implements PreciseShardingAlgorithm {

    private final static Logger logger = LoggerFactory.getLogger(AbstractPreciseShardingAlgorithm.class);

    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        logger.info("collection:" + JSON.toJSONString(collection) + ",preciseShardingValue:" + JSON.toJSONString(preciseShardingValue));
        for (Object name : collection) {
            String dbName =  name.toString();
            int i = ((Integer)preciseShardingValue.getValue()) % collection.size();
            if (dbName.endsWith(i + "")) {
                logger.info("match name: {}" + dbName);
                return dbName;
            }
        }
        return null;
    }

}
