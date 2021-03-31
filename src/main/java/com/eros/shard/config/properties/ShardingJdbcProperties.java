package com.eros.shard.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xuwentao
 * @Date: 2021/3/29 11:11
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "sharding-jdbc", ignoreInvalidFields = true)
public class ShardingJdbcProperties {

    @Value("data-source")
    private List<DataSourceProperties> dataSources;

    public List<DataSourceProperties> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSourceProperties> dataSources) {
        this.dataSources = dataSources;
    }
}
