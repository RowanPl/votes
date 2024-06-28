package org.example.votes.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Vote{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;
    @Getter
    @Setter
    @Column(unique = true)
    private String username;
    @Getter
    @Setter
    private String partij;

    public Vote() {
    }
}

