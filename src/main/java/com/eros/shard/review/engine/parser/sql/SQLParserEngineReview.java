package com.eros.shard.review.engine.parser.sql;

import org.apache.shardingsphere.sql.parser.SQLParserEngine;
import org.apache.shardingsphere.sql.parser.core.parser.SQLParserExecutor;
import org.apache.shardingsphere.sql.parser.hook.ParsingHook;
import org.apache.shardingsphere.sql.parser.hook.SPIParsingHook;

/**
 * @author xuwentao
 * @Date: 2021/4/1 11:26
 * @Description:
 *
 * @see SQLParserEngine
 * @see org.apache.shardingsphere.sql.parser.SQLParserEngineFactory
 * sql解析引擎
 *
 * 类属性
 * databaseTypeName - 数据库类型名称
 * cache - SQL解析结果缓存
 *
 * 类方法
 * @see SQLParserEngine#parse(String sql, boolean useCache)
 *      这里主要通过定义一个全局的解析钩子接口{@link ParsingHook}，其实现类{@link SPIParsingHook}提供客户自定义解析的额外操作
 *      {@link SPIParsingHook} 通过静态方法注册所有的实现类，之后实例化实现类，并完成钩子操作。
 *      抄笔记 {@link java.util.ServiceLoader#load(Class)} 的使用，提供META-INF/services中的文件，文件名需是对应接口相对路径，内容为实现类相对路径
 *
 *      除了钩子以外，还调用了{@link SQLParserEngine#parse0(String sql, boolean useCache)}
 *      1. 缓存数据
 *      2. {@link SQLParserExecutorReview} sql解析执行，获取解析树
 *      3. 通过解析树工厂解析得到 {@link SQLStatementReview}
 *
 */
public class SQLParserEngineReview {
}
