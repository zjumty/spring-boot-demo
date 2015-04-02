package demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

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


    /**
     * DataSource
     */
    @Bean(destroyMethod = "close", name = "dataSource")
    public HikariDataSource buildDataSource() {
        HikariConfig cfg = new HikariConfig();
        cfg.setUsername(user);
        cfg.setPassword(password);
        cfg.setJdbcUrl(dataSourceUrl);
        cfg.setDriverClassName(dataSourceClassName);
        return new HikariDataSource(cfg);
    }

    /**
     * TransactionManager
     */
    @Bean(name = "transactionManager")
    @Autowired
    protected PlatformTransactionManager createTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
