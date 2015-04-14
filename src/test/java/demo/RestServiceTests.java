package demo;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import demo.domain.Quote;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * TODO: 这里需要写注释
 */
public class RestServiceTests {
    @Test
    public void getQuote() {
        HttpClient client = HttpClientBuilder.create().build();
        ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        RestTemplate rest = new RestTemplate(factory);
        List<HttpMessageConverter<?>> converters = new ArrayList<>();

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setCharset(Charset.forName("utf-8"));
        converters.add(converter);

        rest.setMessageConverters(converters);
        ResponseEntity<Quote> entity = rest.getForEntity("http://localhost:8080/foo/quotes?param1={param1}&param2={param2}", Quote.class, "123", "abc");
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Quote quote = entity.getBody();

        assertNotNull(quote);
        assertNotNull(quote.getId());
        assertNotNull("ABCDEF", quote.getSymbol());
        assertNotNull("0001", quote.getTenantId());
    }
}
