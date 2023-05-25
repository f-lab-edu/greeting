package com.project.greeting.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyVO {

    private int replyId;             // 댓글 번호
    private int id;         // 게시글 번호
    private String userId;
    private String userPw;
    private String contents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}
