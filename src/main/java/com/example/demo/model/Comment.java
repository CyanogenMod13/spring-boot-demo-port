package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "blog_comments")
public class Comment {
    @Id
    private UUID id;
    private String content;
    private LocalDateTime publishedAt;
    @ManyToOne
    private Author author;

    @ManyToOne
    private Article article;
    @ManyToOne
    private Comment parent;
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL})
    private Set<Comment> children = new HashSet<>();

    private Long numberLikes;

    public Comment(UUID id, String content, Author author) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.publishedAt = LocalDateTime.now();
    }

    public void like() {
        numberLikes++; //concurrency ?
    }

    public Comment reply(String content, Author author) {
        Comment replied = new Comment(UUID.randomUUID(), content, author);
        replied.setParent(this);
        children.add(replied);
        return replied;
    }

    public Optional<Article> getArticle() {
        return Optional.of(article);
    }

    public Optional<Comment> getParent() {
        return Optional.of(parent);
    }

    public Set<? extends Comment> getChildren() {
        return children;
    }
}
