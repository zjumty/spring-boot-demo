package demo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.net.UnknownHostException;

/**
 * TODO: 这里需要写注释
 */
@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoDBConfiguration {
    @Autowired
    private MongoProperties properties;

    @Autowired(required = false)
    private MongoClientOptions options;

    private Mongo mongo;

    @PreDestroy
    public void close() {
        if (this.mongo != null) {
            this.mongo.close();
        }
    }

    @Bean
    public Mongo mongo() throws UnknownHostException {
        this.mongo = this.properties.createMongoClient(this.options);
        return this.mongo;
    }

}
