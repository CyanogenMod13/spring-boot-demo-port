package com.example.demo.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateCommand implements Command {
    private UUID authorId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
