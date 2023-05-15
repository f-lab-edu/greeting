package com.project.greeting.dao;

import com.project.greeting.dto.Criteria;
import com.project.greeting.dto.PageDto;
import com.project.greeting.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostDao {
    // 게시물 리스트 조회
    List<PostDto> selectPostList();
    // 게시물 작성
    void insertPost(PostDto post);
    // 게시물 조회
    PostDto selectDetail(Long id);
    // 게시물 삭제
    void deletePost(Long id);
    // 게시물 수정
    void updatePost(PostDto post);

    // 게시물 검색
    List<PostDto> getBoardsByCri(Criteria cri);
    int getCountBoardsByCri(Criteria cri);
}
