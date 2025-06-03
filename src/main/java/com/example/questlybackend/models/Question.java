package com.example.questlybackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Question extends BaseModel{
    private String content;
    private String title;

    @ManyToMany
    @JoinTable(
            name = "question_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
