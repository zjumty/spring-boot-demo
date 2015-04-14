package demo.controller;

import demo.domain.Project;
import demo.domain.Quote;
import demo.service.FooService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/quotes")
    public @ResponseBody Quote getQuote(@RequestParam("param1") String param1, @RequestParam("param2") String param2){
        Quote quote = new Quote();
        quote.setId(new ObjectId());
        quote.setSymbol("ABCDEF");
        quote.setTenantId("0001");
        return quote;
    }
}
