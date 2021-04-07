package com.eros.shard.review.engine.parser.route;

import org.apache.shardingsphere.underlying.route.DataNodeRouter;
import org.apache.shardingsphere.underlying.route.context.RouteContext;

import java.util.List;

/**
 * @author xuwentao
 * @Date: 2021/4/7 16:41
 * @Description:
 *
 * @see DataNodeRouter
 * 数据节点路由器
 *
 * 类属性
 * ShardingSphereMetaData - 分片数据源的数据元、表的数据元
 * ConfigurationProperties - 全局配置、私人配置
 * SQLParserEngine - SQL解释器引擎
 * Map<BaseRule, RouteDecorator> - key : 分片规则 value : 路由装饰器
 * SPIRoutingHook - 钩子
 * 
 * 类方法
 * {@link DataNodeRouter#executeRoute (String sql, List<Object> parameters, boolean useCache)} - 执行节点路由
 *      1. 调用SQL解释器引擎解析对应数据, 生成{@link RouteContext}, 这时路由结果为空
 *      2. 调用现有的路由装饰器，{@link ShardingRouteDecoratorReview}
 *
 */
public class DataNodeRouterReview {
}
