package com.eros.shard.jdbc.shardingRule;

import com.eros.shard.jdbc.ShardJdbcDataSource;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.resultset.ShardingResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xuwentao
 * @Date: 2021/3/31 16:06
 * @Description:
 */
public abstract class ShardingRuleConfigTestImpl extends ShardJdbcDataSource implements ShardingRuleConfigTest {

    private final static Logger logger = LoggerFactory.getLogger(ShardingRuleConfigTestImpl.class);

    @Override
    public void insert(DataSource dataSource, Connection connection) throws SQLException {
        String sql = "INSERT INTO qmai_order (`id`, `user_id`, `order_id`, `order_msg`) VALUES (1001, 2, 1, '订单2')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        if(i > 0) {
            logger.info("本次执行成功, 新增条数: {}", i);
        } else {
            logger.info("本次执行失败");
        }
    }

    @Override
    public void select(DataSource dataSource, Connection connection) throws SQLException {
        String sql = "SELECT `id`, `user_id`, `order_id`, `order_msg` FROM qmai_order WHERE user_id = 634";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
        // todo 最后返回的 ShardingResultSet 并不是最终结果，其中包含ResultSets，没有归并最终集合吗
        ShardingResultSet resultSet = (ShardingResultSet)preparedStatement.getResultSet();
        List<ResultSet> resultSets = resultSet.getResultSets();
        for (ResultSet set : resultSets) {
            // 将光标移动到最后一行
            if(set.last()) {
                int rowCount = set.getRow(); // 得到当前行号，即结果集记录数
                logger.info("本次执行成功, 库: {} 表: {} 查询条数: {}", set.getMetaData().getCatalogName(1), set.getMetaData().getTableName(1), rowCount);
            } else {
                logger.info("本次执行失败, 查询条数为空");
            }
        }
    }

    @Override
    public void delete(DataSource dataSource, Connection connection) throws SQLException {
        String sql = "DELETE FROM qmai_order WHERE id = 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        if(i > 0) {
            logger.info("本次执行成功, 删除条数: {}", i);
        } else {
            logger.info("本次执行失败");
        }
    }

    @Override
    public void update(DataSource dataSource, Connection connection) throws SQLException {
        String sql = "UPDATE qmai_order SET `order_msg` = '订单1' WHERE user_id = 2";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        if(i > 0) {
            logger.info("本次执行成功, 修改条数: {}", i);
        } else {
            logger.info("本次执行失败");
        }
    }

}
