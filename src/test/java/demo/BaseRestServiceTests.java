package demo;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import demo.vo.AuthParam;
import demo.vo.AuthResult;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 这里需要写注释
 */
public class BaseRestServiceTests {
    private static final Logger LOG = LoggerFactory.getLogger(BaseRestServiceTests.class);

    protected RestTemplate restTemplate;

    protected String baseUrl = "http://localhost:8080";

    protected String authUrl = baseUrl + "/auth";

    protected String apiKey;

    protected String authString;

    @Before
    public void initRestTemplate() {
        HttpClient client = HttpClientBuilder.create().addInterceptorFirst(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                if (apiKey != null) {
                    httpRequest.addHeader("X-ApiKey", apiKey);
                }
                if (authString != null) {
                    httpRequest.addHeader("X-AuthString", authString);
                }
            }
        }).build();
        ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        restTemplate = new RestTemplate(factory);
        List<HttpMessageConverter<?>> converters = new ArrayList<>();

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setCharset(Charset.forName("utf-8"));
        converters.add(converter);

        restTemplate.setMessageConverters(converters);

        authenticate(getTenantId(), getUserId(), getPassword(), getExpired());
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public String getTenantId() {
        return "0001";
    }

    public String getUserId() {
        return "1";
    }

    public String getPassword() {
        return "123456";
    }

    public long getExpired() {
        return Instant.now().plus(1, ChronoUnit.DAYS).getEpochSecond();
    }

    protected void authenticate(String tenantId, String userId, String password, long expired) {
        AuthParam param = new AuthParam();
        param.setExpired(expired);
        param.setPassword(password);
        param.setTenantId(tenantId);
        param.setUserId(userId);

        ResponseEntity<AuthResult> result = restTemplate.postForEntity(authUrl, param, AuthResult.class);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        apiKey = result.getBody().getApiKey();
        authString = tenantId + "," + userId + "," + expired;

        LOG.debug("authenticated with apikey : {}, authString : {}", apiKey, authString);
    }
}
