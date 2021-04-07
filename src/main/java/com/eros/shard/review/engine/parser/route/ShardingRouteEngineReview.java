package com.eros.shard.review.engine.parser.route;

import org.apache.shardingsphere.core.rule.ShardingRule;
import org.apache.shardingsphere.sharding.route.engine.condition.ShardingConditions;
import org.apache.shardingsphere.sharding.route.engine.type.ShardingRouteEngine;
import org.apache.shardingsphere.sharding.route.engine.type.ShardingRouteEngineFactory;
import org.apache.shardingsphere.sql.parser.binder.statement.SQLStatementContext;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationProperties;
import org.apache.shardingsphere.underlying.common.metadata.ShardingSphereMetaData;
import org.apache.shardingsphere.underlying.route.context.RouteResult;

/**
 * @author xuwentao
 * @Date: 2021/4/7 17:25
 * @Description:
 *
 * @see ShardingRouteEngine
 *  调用具体的分片策略，生成路由节点
 *
 * @see ShardingRouteEngineFactory#newInstance(ShardingRule, ShardingSphereMetaData, SQLStatementContext, ShardingConditions, ConfigurationProperties)
 * 按照工厂去获取 对应的引擎
 *
 * 最终生成
 * {@link RouteResult}
 *      类参数
 *      originalDataNodes - 路由数据节点信息
 *      routeUnits - 路由单元Mapper
 */
public class ShardingRouteEngineReview {
}
