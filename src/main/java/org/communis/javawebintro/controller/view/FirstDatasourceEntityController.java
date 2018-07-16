package org.communis.javawebintro.controller.view;

import org.communis.javawebintro.first.entity.FirstDataSourceEntity;
import org.communis.javawebintro.first.repository.FirstDatabaseEntityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FirstDatasourceEntityController {
    private final FirstDatabaseEntityRepository repository;

    public FirstDatasourceEntityController(FirstDatabaseEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping(
            "/firstDatasource"
    )
    public String dataList(Model model) {
        model.addAttribute("entities", repository.findAll());
        model.addAttribute("entitytype", "first");
        return "entities";
    }

    @GetMapping(
            "/first/{id}"
    )
    public String data(Model model, @PathVariable("id") Long id) {
        model.addAttribute("entity", repository.findOne(id));
        model.addAttribute("entitytype", "first");
        model.addAttribute("method", "POST");
        return "entity";
    }

    @PostMapping(
            value = "/first")
    public String save(@ModelAttribute("entity") FirstDataSourceEntity entity, Model model) {
        repository.save(entity);
        return dataList(model);
    }

    @GetMapping(
            "/first/delete/{id}"
    )
    public String delete(@PathVariable("id") Long id, Model model) {
        repository.delete(id);
        return dataList(model);
    }

    @GetMapping(
            "/first/new"
    )
    public String form(Model model) {
        model.addAttribute("entity", new FirstDataSourceEntity());
        model.addAttribute("entitytype", "first");
        model.addAttribute("method", "POST");
        return "entity";
    }

    @GetMapping(
            "/first/find"
    )
    public String find(@RequestParam("query") String query, Model model) {
        model.addAttribute("entities", repository.findAllByNameContainsOrderByName(query));
        model.addAttribute("entitytype", "first");
        return "entities";
    }
}
