package com.example.demo.config;

import com.example.demo.command.handler.ArticleCreateHandler;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.AuthorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {
    @Bean
    public ArticleCreateHandler articleCreateHandler(
            ArticleRepository articleRepository,
            AuthorRepository authorRepository
    ) {
        return new ArticleCreateHandler(authorRepository, articleRepository);
    }
}
