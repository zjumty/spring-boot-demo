package demo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * TODO: 这里需要写注释
 */
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoProperties extends org.springframework.boot.autoconfigure.mongo.MongoProperties{

    private static final int DEFAULT_PORT = 27017;

    /**
     * Mongo server host.
     */
    private String host;

    /**
     * Mongo server port.
     */
    private Integer port = null;

    /**
     * Mongo database URI. When set, host and port are ignored.
     */
    private String uri = "mongodb://localhost/test";

    /**
     * Database name.
     */
    private String database;

    /**
     * Authentication database name.
     */
    private String authenticationDatabase;

    /**
     * GridFS database name.
     */
    private String gridFsDatabase;

    /**
     * Login user of the mongo server.
     */
    private String username;

    /**
     * Login password of the mongo server.
     */
    private char[] password;

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getAuthenticationDatabase() {
        return this.authenticationDatabase;
    }

    public void setAuthenticationDatabase(String authenticationDatabase) {
        this.authenticationDatabase = authenticationDatabase;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void clearPassword() {
        if (this.password == null) {
            return;
        }
        for (int i = 0; i < this.password.length; i++) {
            this.password[i] = 0;
        }
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getGridFsDatabase() {
        return this.gridFsDatabase;
    }

    public void setGridFsDatabase(String gridFsDatabase) {
        this.gridFsDatabase = gridFsDatabase;
    }

    public String getMongoClientDatabase() {
        if (this.database != null) {
            return this.database;
        }
        return new MongoClientURI(this.uri).getDatabase();
    }

    public MongoClient createMongoClient(MongoClientOptions options)
            throws UnknownHostException {
        try {
            if (hasCustomAddress() || hasCustomCredentials()) {
                if (options == null) {
                    options = MongoClientOptions.builder().build();
                }
                List<MongoCredential> credentials = null;
                if (hasCustomCredentials()) {
                    String database = this.authenticationDatabase == null ? getMongoClientDatabase()
                            : this.authenticationDatabase;
                    credentials = Arrays.asList(MongoCredential.createScramSha1Credential(
                            this.username, database, this.password));
                }
                String host = this.host == null ? "localhost" : this.host;
                int port = this.port == null ? DEFAULT_PORT : this.port;
                return new MongoClient(Arrays.asList(new ServerAddress(host, port)),
                        credentials, options);
            }
            // The options and credentials are in the URI
            return new MongoClient(new MongoClientURI(this.uri, builder(options)));
        }
        finally {
            clearPassword();
        }
    }

    private boolean hasCustomAddress() {
        return this.host != null || this.port != null;
    }

    private boolean hasCustomCredentials() {
        return this.username != null && this.password != null;
    }

    private MongoClientOptions.Builder builder(MongoClientOptions options) {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        if (options != null) {
            builder.alwaysUseMBeans(options.isAlwaysUseMBeans());
            builder.connectionsPerHost(options.getConnectionsPerHost());
            builder.connectTimeout(options.getConnectTimeout());
            builder.cursorFinalizerEnabled(options.isCursorFinalizerEnabled());
            builder.dbDecoderFactory(options.getDbDecoderFactory());
            builder.dbEncoderFactory(options.getDbEncoderFactory());
            builder.description(options.getDescription());
            builder.maxWaitTime(options.getMaxWaitTime());
            builder.readPreference(options.getReadPreference());
            builder.socketFactory(options.getSocketFactory());
            builder.socketKeepAlive(options.isSocketKeepAlive());
            builder.socketTimeout(options.getSocketTimeout());
            builder.threadsAllowedToBlockForConnectionMultiplier(options
                    .getThreadsAllowedToBlockForConnectionMultiplier());
            builder.writeConcern(options.getWriteConcern());
        }
        return builder;
    }

}