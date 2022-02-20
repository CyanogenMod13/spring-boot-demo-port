package com.example.demo.command.handler;

import com.example.demo.command.ArticleCreateCommand;
import com.example.demo.model.Article;
import com.example.demo.model.Author;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.AuthorRepository;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class ArticleCreateHandler implements Handler<ArticleCreateCommand, Article> {
    private final AuthorRepository authorRepository;
    private final ArticleRepository articleRepository;

    @Override
    public Article handle(ArticleCreateCommand command) {
        Author author = authorRepository.findById(command.getAuthorId()).orElseThrow();
        Article article = new Article(UUID.randomUUID(), command.getTitle(), command.getContent(), author);
        articleRepository.saveAndFlush(article);
        return article;
    }
}
