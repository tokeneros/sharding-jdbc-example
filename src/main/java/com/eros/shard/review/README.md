#逻辑整理入口

ShardingDataSourceFactoryReview - 创建分片DataSource工厂，配置分片信息
详细解析入口


配置信息如何被调用，内部如何拦截，调用，核心顺序？？？


数据分片主要流程
SQL 解析 => 执行器优化 => SQL 路由 => SQL 改写 => SQL 执行 => 结果归并

解析引擎？？？
SQLParserEngine



路由引擎？？？
改写引擎？？？
执行引擎？？？
归并引擎？？？
