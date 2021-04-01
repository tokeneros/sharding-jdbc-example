package com.eros.shard.review.config;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;

/**
 * @author xuwentao
 * @Date: 2021/3/31 14:37
 * @Description:
 *
 * @see TableRuleConfiguration
 * 表规则设置
 *
 * @see TableRuleConfiguration#TableRuleConfiguration(String logicTable, String actualDataNodes) () - 常用
 * 构造方法 -
 * logicTable: 表名称 - 逻辑表
 * actualDataNodes: 数据节点集合，支持Groovy语法
 *      ${begin..end} 表示范围区间
 *      ${[unit1, unit2, unit_x]} 表示枚举值
 *      例如: ds${0..1}.table_${0..1} <--> ${[ds0, ds1]}.table_${0..1} <--> ${[ds0, ds1]}.${table_0, table_1}
 * 说明 -
 * 这里很重要，logicTable在后面的解析起到至关重要的角色，会根据sql解析识别到的table名称，来决定分片规则，当此处表规则中有分片规则，
 * 则采用此方法的，否则使用全局默认分片规则，分片规则返回的数值，会组装相应的数据节点，如: ds0 .table_0, 去替换sql中的table名称
 * 猜测是 改写引擎和路由引擎来使用这一块的数据
 *
 * @see TableRuleConfiguration#setTableShardingStrategyConfig(ShardingStrategyConfiguration)
 * {@link ShardingStrategyConfigurationReview}
 * 逻辑表 的表分片策略
 *      a. 如需特别处理，比如由于业务量过大，和其他表分片数量不一致
 *
 * @see TableRuleConfiguration#setDatabaseShardingStrategyConfig(ShardingStrategyConfiguration)
 * {@link ShardingStrategyConfigurationReview}
 * 逻辑表 的库分片策略
 *
 * @see TableRuleConfiguration#setKeyGeneratorConfig(KeyGeneratorConfiguration)
 * 逻辑表 的主键生成策略
 *      a. 常规来说，我们需要在业务中操作主键，不可能等到数据插入后获取
 *
 */
public class TableRuleConfigurationReview {
}
