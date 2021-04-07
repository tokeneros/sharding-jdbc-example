package com.eros.shard.review.engine.parser.route;

import org.apache.shardingsphere.core.rule.ShardingRule;
import org.apache.shardingsphere.sharding.route.engine.ShardingRouteDecorator;
import org.apache.shardingsphere.underlying.common.config.properties.ConfigurationProperties;
import org.apache.shardingsphere.underlying.common.metadata.ShardingSphereMetaData;
import org.apache.shardingsphere.underlying.route.context.RouteContext;

/**
 * @author xuwentao
 * @Date: 2021/4/7 15:08
 * @Description:
 *
 * @see ShardingRouteDecorator
 * 分片路由装饰器
 *
 * 类方法
 * @see ShardingRouteDecorator#decorate(RouteContext, ShardingSphereMetaData, ShardingRule, ConfigurationProperties)
 *      参数
 *      RouteContext - 路由上下文
 *      ShardingSphereMetaData - 分片数据源的数据元、表的数据元
 *      ShardingRule - 路由规则
 *      ConfigurationProperties - 全局配置、私人配置
 *
 *      调用具体的路由引擎去执行
 *
 */
public class ShardingRouteDecoratorReview {
}
