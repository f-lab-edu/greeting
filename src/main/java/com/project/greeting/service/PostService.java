package com.project.greeting.service;

import com.project.greeting.dao.PostDao;
import com.project.greeting.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostDao postDao;
    public List<PostDto> selectPostList() {

        return postDao.selectPostList();
    }

    public void insertPost(PostDto post) {
        postDao.insertPost(post);
    }
}
