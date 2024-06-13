package com.example.taskmanager.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean(name = "hikariConfigSql")
    @ConfigurationProperties(prefix = "spring.datasource.sql-server")
    HikariConfig hikariConfigSql() {
        System.setProperty("java.library.path", "C:\\sqljdbc");
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
	
    @Bean
    @DependsOn("hikariConfigSql")
    DataSource dsSqlServer(HikariConfig hikariConfig) {
		return new HikariDataSource(hikariConfig);
	}
    
}
