package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "blog_likes")
public class Like {
    @Id
    private UUID id;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Article article;
}
