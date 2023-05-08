package com.project.greeting.dto;

import lombok.Data;

@Data
public class PageDto {

    int page;               // 현재페이지
    int startPage = 1;      // 시작페이지
    int endPage;            // 끝 페이지
    int totalCount;         // 총 게시물 개수
    int rowCount = 2;           // 한 페이지 당 보여줄 게시물 개수
    int pageCount = 2;          // 한 블럭에 몇 개의 페이지 개수 (전체 페이지 범위의 개수)
    int totalPageCount;     // 총 페이지 개수

    boolean isPrev;         // 이전 페이지
    boolean isNext;         // 다음 페이지

    int offset;             // 얼만큼 끊어서 가져올 것인가


    public PageDto(int totalCount, int page) {

        // 총 페이지 개수 구하기
        setTotalPageCount(totalCount, this.rowCount);

        // 한 블럭의 첫 페이지 구하기
        setStartPage(this.startPage, page, this.pageCount);

        // 한 블럭의 끝 페이지 구하기
        setEndPage(this.startPage, this.pageCount, this.totalPageCount);

        // 이전 블록 버튼 유무 판별하기
        isPrev(page, this.pageCount);

        // 다음 블록 버튼 유무 판별하기
        isNext(this.endPage, this.totalPageCount);

        // offset 구하기
        setOffset(page, this.rowCount);
    }


    // 총 페이지 개수 구하기
    private void setTotalPageCount(int totalCount,  int rowCount) {
        this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
    }


    // 한 블럭의 첫 페이지 구하기
    private void setStartPage(int startPage, int page,  int pageCount) {
        this.startPage = startPage + (((page - startPage) / pageCount) * pageCount);
    }


    // 한 블럭의 끝 페이지 구하기
    private void setEndPage(int startPage, int pageCount, int totalPageCount) {
        this.endPage = ((startPage - 1) + pageCount) < totalPageCount ? (startPage - 1) + pageCount : totalPageCount;
    }


    // 이전 블럭으로 이동할 버튼 생성 유무
    private void isPrev(int page, int pageCount) {
        this.isPrev = 1 < ((page * 1.0) / pageCount);
    }


    // 다음 블럭으로 이동할 버튼 생성 유무
    private void isNext(int endPage, int totalPageCount) {
        this.isNext = endPage < totalPageCount;
    }


    // offset 구하기 // 쿼리 select 시 끊어서 가져오기
    private void setOffset(int page,int rowCount) {
        this.offset = (page - 1) * rowCount;
    }
}
