package demo.service.impl;

import demo.domain.Project;
import demo.domain.Quote;
import demo.persistence.ProjectMapper;
import demo.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * TODO: 这里需要写注释
 */
@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private MongoOperations mongoOps;

    @Transactional(readOnly = true)
    public List<Project> findAllProjects() {
        return projectMapper.findAllProjects();
    }

    @Override
    public List<Quote> findQuotes() {
        return mongoOps.find(Query.query(where("tenantId").is("0001")), Quote.class);
    }
}
