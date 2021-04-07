package com.eros.shard.jdbc.shardingRule.engine.sql;

import com.alibaba.fastjson.JSON;
import org.apache.shardingsphere.sql.parser.hook.ParsingHook;
import org.apache.shardingsphere.sql.parser.sql.statement.SQLStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuwentao
 * @Date: 2021/4/7 11:41
 * @Description:
 */
public class SqlParsingHookTest implements ParsingHook {

    private final static Logger logger = LoggerFactory.getLogger(SqlParsingHookTest.class);

    @Override
    public void start(String sql) {
        logger.info("SqlParsingHookTest[start] sql: {}", sql);;
    }

    @Override
    public void finishSuccess(SQLStatement sqlStatement) {
        logger.info("SqlParsingHookTest[finishSuccess] sqlStatement: {}", JSON.toJSONString(sqlStatement));
    }

    @Override
    public void finishFailure(Exception cause) {
        logger.error(cause.getLocalizedMessage());
    }

}
