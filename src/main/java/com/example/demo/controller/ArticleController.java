package com.example.demo.controller;

import com.example.demo.command.ArticleCreateCommand;
import com.example.demo.command.handler.ArticleCreateHandler;
import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping("/")
    public Map<String, Object> getAll() {
        Map<String, Object> map = new LinkedHashMap<>();
        for (Article article : articleRepository.findAll()) {
            map.put("id", article.getId());
            map.put("title", article.getTitle());
            map.put("publishedAt", article.getPublishedAt());
            map.put("author", article.getAuthor());
        }
        return map;
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable UUID id) {
        return articleRepository.findById(id).orElseThrow();
    }

    private final ArticleCreateHandler handler;

    @PostMapping("/publish")
    public Map<String, Object> publish(@Valid @RequestBody ArticleCreateCommand command) {
        Map<String, Object> map = new LinkedHashMap<>();
        Article article = handler.handle(command);
        map.put("id", article.getId());
        return map;
    }
}
