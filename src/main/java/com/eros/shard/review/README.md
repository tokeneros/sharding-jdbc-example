#逻辑整理入口

ShardingDataSourceFactoryReview - 创建分片DataSource工厂，配置分片信息
详细解析入口


配置信息如何被调用，内部如何拦截，调用，核心顺序？？？


数据分片主要流程
SQL 解析 => 执行器优化 => SQL 路由 => SQL 改写 => SQL 执行 => 结果归并

解析引擎
SQLParserEngineFactory 是使用入口
根据不同的db, 调用不同的SQLParserEngine
之后通过不同的db，调用不同的SQL解析器执行器
最后得到SQLStatement(抽象语法树)

路由引擎
ShardingRouteDecorator(分片路由装饰器)
hint 会通过 hintManager 获取分片值
其他 会通过 解析引擎获取对应的分片值
最终还是 调用具体的分片策略，也就是我们配置的那些内容

改写引擎？？？
SQLRewriteEngine
执行引擎？？？
归并引擎？？？

基本准备引擎
BasePrepareEngine