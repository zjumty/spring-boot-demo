package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO: 这里需要写注释
 */
@Controller
@RequestMapping("/foo")
public class FooController {

    @RequestMapping("/projects")
    public String listProjects(Model model) {
        return "projects";
    }
}
