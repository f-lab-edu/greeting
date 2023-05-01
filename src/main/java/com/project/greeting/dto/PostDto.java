package com.project.greeting.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private int id;
    private String userId;
    private String userPw;
    private String title;
    private String contents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}
