package demo.service.impl;

import demo.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

/**
 * TODO: 这里需要写注释
 */
@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcOperations jdbcOperations;


}
