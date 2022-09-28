package com.example.contenttest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
