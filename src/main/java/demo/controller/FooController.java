package demo.controller;

import demo.domain.Project;
import demo.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * TODO: 这里需要写注释
 */
@Controller
@RequestMapping("/foo")
public class FooController {
    @Autowired
    private FooService fooService;

    @RequestMapping("/projects")
    public String listProjects(Model model) {
        List<Project> projects = fooService.findAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("quotes", fooService.findQuotes());
        model.addAttribute("name", "Tom");
        return "/foo/projects";
    }
}
