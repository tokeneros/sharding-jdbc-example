package com.eros.shard.review.engine.parser.sql;

import org.apache.shardingsphere.sql.parser.core.parser.SQLParserExecutor;

/**
 * @author xuwentao
 * @Date: 2021/4/7 14:36
 * @Description:
 *
 * @see SQLParserExecutor
 * sql解析执行器
 *
 * 类属性
 * databaseTypeName - 数据库类型名称
 * sql - 执行语句
 *
 * 类方法
 * {@link SQLParserExecutor#execute()}
 *      1. 根据数据库类型获取对应的解析器
 *      2. 获取抽象语法树（AST） todo 这东西知识量比较庞大，可以带来很多信息
 *
 *
 */
public class SQLParserExecutorReview {
}
