package com.project.greeting.dto;

import lombok.Data;

@Data
public class Criteria {
    private int pageNum; // 페이지 번호
    private int amount; // 한 페이지당 글 개수
    private int startRow; // 가져올 글의 시작 행번호
    private String type;
    private String keyword;

    public Criteria(){
        this(1,5);
    }
    public Criteria(int pageNum, int amount) {
        super();
        this.pageNum = pageNum;
        this.amount= amount;
    }
}
