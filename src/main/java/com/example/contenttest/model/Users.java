package com.example.contenttest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Users {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(nullable = false, updatable = false, length = 36, unique = true)
    private String id ;

    @NotNull
    @Column(unique=true)
    private String username ;

    @NotNull
    @Column
    private String password ;

    @NotNull
    @Column
    private String nickname ;

    @NotNull
    @Column(unique=true)
    private String email ;
}
