package demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String dataSourceClassName;


    @Bean(destroyMethod = "close", name = "dataSource")
    public HikariDataSource buildDataSource() {
        HikariConfig cfg = new HikariConfig();
        cfg.setUsername(user);
        cfg.setPassword(password);
        cfg.setJdbcUrl(dataSourceUrl);
        cfg.setDriverClassName(dataSourceClassName);
        return new HikariDataSource(cfg);
    }
}
