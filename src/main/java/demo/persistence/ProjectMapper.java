package demo.persistence;

import demo.domain.Project;

import java.util.List;

/**
 * TODO: 这里需要写注释
 */
public interface ProjectMapper {
    List<Project> findAllProjects();
}
