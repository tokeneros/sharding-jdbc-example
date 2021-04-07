package com.eros.shard.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author eros
 */
@SpringBootApplication(scanBasePackages = "com.eros.shard.config")
public class JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

}
