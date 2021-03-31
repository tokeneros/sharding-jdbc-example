package com.eros.shard.jdbc.shardingRule.utils;

/**
 * @author xuwentao
 * @Date: 2021/3/31 16:41
 * @Description:
 */
public class ShardingUtils {

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
    public final static Integer getShardNum(Integer shardingValue, Integer collectionSize) {
        return shardingValue % (baseShardNum * collectionSize) / baseShardNum ;
    }

}
