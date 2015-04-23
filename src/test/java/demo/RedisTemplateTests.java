package demo;

import com.alibaba.fastjson.JSON;
import demo.domain.Project;
import org.junit.Test;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

/**
 * TODO: 这里需要写注释
 */
public class RedisTemplateTests {

    @Test
    public void testSetAndGet() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName("localhost");
        factory.setPort(6379);
        factory.afterPropertiesSet();

        RedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();

        template.delete("list1");
        template.boundListOps("list1").leftPush("1");
        template.boundListOps("list1").leftPush("2");

        template.delete("foo:1:1");
        BoundHashOperations<String, String, String> ops = template.boundHashOps("foo:1:1");
        ops.put("1", JSON.toJSONString(new Project(1, "a", "b", new Date(), new Date())));
        ops.put("2", JSON.toJSONString(new Project(2, "a", "b", new Date(), new Date())));
        ops.put("3", JSON.toJSONString(new Project(3, "a", "b", new Date(), new Date())));
    }
}
