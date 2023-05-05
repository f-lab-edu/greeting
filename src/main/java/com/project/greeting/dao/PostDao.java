package com.project.greeting.dao;

import com.project.greeting.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostDao {

    List<PostDto> selectPostList();

    void insertPost(PostDto post);

    PostDto selectDetail(Long id);

    void deletePost(Long id);

    void updatePost(PostDto post);
}
