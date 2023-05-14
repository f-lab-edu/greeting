package com.project.greeting.service;

import com.project.greeting.dao.PostDao;
import com.project.greeting.dto.Criteria;
import com.project.greeting.dto.PageDto;
import com.project.greeting.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public PostDto selectDetail(Long id) {

        return postDao.selectDetail(id);
    }

    public void deletePost(Long id) {
        postDao.deletePost(id);
    }


    public void updatePost(PostDto post) {
        postDao.updatePost(post);
    }


    public List<PostDto> getBoardsByCri(Criteria cri) {

        int startRow = (cri.getPageNum()-1)*cri.getAmount();

        cri.setStartRow(startRow);

        return postDao.getBoardsByCri(cri);
    }

    public int getCountBoardsByCri(Criteria cri) {

        return postDao.getCountBoardsByCri(cri);
    }
}
