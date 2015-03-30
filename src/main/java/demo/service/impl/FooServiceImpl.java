package demo.service.impl;

import demo.domain.Project;
import demo.persistence.ProjectMapper;
import demo.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO: 这里需要写注释
 */
@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> findAllProjects() {
        return projectMapper.findAllProjects();
    }
}
