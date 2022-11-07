package com.example.contenttest.model;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String email;
    private String nickname;
}
