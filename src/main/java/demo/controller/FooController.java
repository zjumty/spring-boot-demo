package demo.controller;

import demo.domain.Project;
import demo.domain.Quote;
import demo.service.FooService;
import demo.vo.AuthParam;
import demo.vo.AuthResult;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * TODO: 这里需要写注释
 */
@Controller
public class FooController {
    private static final Logger LOG = LoggerFactory.getLogger(FooController.class);

    @Autowired
    private FooService fooService;

    @RequestMapping("/foo/projects")
    public String listProjects(Model model) {
        List<Project> projects = fooService.findAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("quotes", fooService.findQuotes());
        model.addAttribute("name", "Tom");
        return "/foo/projects";
    }

    @RequestMapping("/foo/quotes")
    public @ResponseBody Quote getQuote(@RequestParam("param1") String param1, @RequestParam("param2") String param2) {
        LOG.debug("param1:{}, param2:{}", param1, param2);
        Quote quote = new Quote();
        quote.setId(new ObjectId());
        quote.setSymbol("ABCDEF");
        quote.setTenantId("0001");
        return quote;
    }

    @RequestMapping("/auth")
    public @ResponseBody AuthResult auth(@RequestBody AuthParam param) {
        LOG.debug("param:{}", param);

        AuthResult result = new AuthResult();
        result.setTenantId(param.getTenantId());
        result.setUserId(param.getUserId());
        result.setExpired(param.getExpired());
        result.setApiKey("abc1231231231afsdfa4242");

        return result;
    }
}
