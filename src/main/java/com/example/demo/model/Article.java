package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "blog_articles")
public class Article {
    @Id
    private UUID id;
    private String title;
    private String content;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Author author;

    private LocalDateTime publishedAt = LocalDateTime.now();
    private LocalDateTime editedAt;

    @OneToMany(mappedBy = "article", cascade = {CascadeType.ALL})
    private Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "article", cascade = {CascadeType.ALL})
    private Set<Like> likes = new HashSet<>();

    public Article(UUID id, String title, String content, Author author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Comment addComment(String content, Author author) {
        Comment comment = new Comment(UUID.randomUUID(), content, author);
        comment.setArticle(this);
        comments.add(comment);
        return comment;
    }

    public Like like(Author author) {
        Like like = new Like(UUID.randomUUID(), author, this);
        likes.add(like);
        return like;
    }

    public void edit(String title, String content) {
        this.title = title;
        this.content = content;
        editedAt = LocalDateTime.now();
    }
}
