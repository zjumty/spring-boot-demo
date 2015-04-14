package demo;

import demo.domain.Quote;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * TODO: 这里需要写注释
 */
public class RestServiceTests extends BaseRestServiceTests {
    @Test
    public void getQuote() {
        RestTemplate rest = getRestTemplate();
        ResponseEntity<Quote> entity = rest.getForEntity(baseUrl + "/foo/quotes?param1={param1}&param2={param2}", Quote.class, "123", "abc");
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Quote quote = entity.getBody();

        assertNotNull(quote);
        assertNotNull(quote.getId());
        assertNotNull("ABCDEF", quote.getSymbol());
        assertNotNull("0001", quote.getTenantId());
    }
}
