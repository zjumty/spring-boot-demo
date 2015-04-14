package demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;


@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {


    @Bean
    public FastJsonHttpMessageConverter customFastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter jsonConverter = new FastJsonHttpMessageConverter();
        jsonConverter.setCharset(Charset.forName("UTF-8"));
        jsonConverter.setFeatures(SerializerFeature.WriteDateUseDateFormat);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customFastJsonHttpMessageConverter());
        super.addDefaultHttpMessageConverters(converters);
    }


}
