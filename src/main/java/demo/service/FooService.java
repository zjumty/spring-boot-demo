package demo.service;

import demo.domain.Project;
import demo.domain.Quote;

import java.util.List;

/**
 * TODO: 这里需要写注释
 */
public interface FooService {
    List<Project> findAllProjects();

    List<Quote> findQuotes();
}
