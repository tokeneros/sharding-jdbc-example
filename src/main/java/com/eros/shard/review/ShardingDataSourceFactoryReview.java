package com.eros.shard.review;

import com.eros.shard.review.config.ShardingRuleConfigurationReview;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationPropertyKey;

import java.util.Map;
import java.util.Properties;

/**
 * @author xuwentao
 * @Date: 2021/3/31 12:07
 * @Description:
 *
 * @see ShardingDataSourceFactory
 *
 * 数据源 分片工厂， 主要适用于生产出 全局使用的DataSource
 *
 * @see ShardingDataSourceFactory#createDataSource(Map, ShardingRuleConfiguration, Properties)
 * 参数
 * Map<String, DataSource> dataSourceMap
 *      数据源
 *          key : 别名（数据节点中的库名）
 *          value 数据源（这里可以使用第三方数据源，如：HikariDataSource 等继承 DataSource的连接池 ）
 * ShardingRuleConfiguration shardingRuleConfig
 *      分片规则配置
 * @see ShardingRuleConfigurationReview
 * Properties props
 *      系统级全局配置 (https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/configuration/props/)
 *          官网描述更加详细，但我觉得以下几个配置项必备(这边存在问题，java中需要改-为. {@link ConfigurationPropertyKey})
 *          sql-show  默认为false	是否在日志中打印 SQL。日志内容包含：逻辑 SQL，真实 SQL 和 SQL 解析结果。日志级别是 INFO。
 *              a. 例如 Actual SQL: ds1 ::: INSERT INTO qmai_order_0 (`id`, `user_id`, `order_id`, `order_msg`) VALUES (1001, 2, 1, '订单2')
 *          sql-simple 	默认为false	是否在日志中打印简单风格的 SQL。
 *              a. 例如 Actual SQL(simple): [ds1] ::: 1
 *          max-connections-size-per-query 	默认为1	一次查询请求在每个数据库实例中所能使用的最大连接数。
 *              这里会决定后面执行引擎策略，决定为内存限制模式（速度快，消耗资源大）还是连接限制模式（速度慢，但是不会出现特大峰值）
 *          check-table-metadata-enabled 默认为false	是否在程序启动和更新时检查分片元数据的结构一致性。
 *              a. 多字段不会报警
 *              b. varchar 改为 char 也没报警
 *              c. todo 也没看出哪里检查了，没有相关语句打印
 */
public class ShardingDataSourceFactoryReview {



}
