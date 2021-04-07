package com.eros.shard.jdbc.shardingRule.engine.sql;

import com.alibaba.fastjson.JSON;
import org.apache.shardingsphere.sql.parser.hook.ParsingHook;
import org.apache.shardingsphere.sql.parser.sql.statement.SQLStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuwentao
 * @Date: 2021/4/7 14:09
 * @Description:
 */
public class DateParsingHookTest implements ParsingHook {

    private final static Logger logger = LoggerFactory.getLogger(DateParsingHookTest.class);

    @Override
    public void start(String sql) {
        logger.info("DateParsingHookTest[start] sql: {}", sql);;
    }

    @Override
    public void finishSuccess(SQLStatement sqlStatement) {
        logger.info("DateParsingHookTest[finishSuccess] sqlStatement: {}", JSON.toJSONString(sqlStatement));
    }

    @Override
    public void finishFailure(Exception cause) {
        logger.error(cause.getLocalizedMessage());
    }

}
