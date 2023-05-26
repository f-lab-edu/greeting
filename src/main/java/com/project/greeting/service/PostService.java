package com.project.greeting.service;

import com.project.greeting.dao.PostDao;
import com.project.greeting.dto.Criteria;
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
    public void insertPost(PostDto postDto) {
        postDao.insertPost(postDto);
    }
    public PostDto selectDetail(Long id) {

        return postDao.selectDetail(id);
    }
    public void deletePost(Long id) {
        postDao.deletePost(id);
    }
    public void updatePost(PostDto postDto) {
        postDao.updatePost(postDto);
    }
    public List<PostDto> getBoardsByCri(Criteria criteria) {

        int startRow = (criteria.getPageNum()-1)*criteria.getAmount();

        criteria.setStartRow(startRow);

        return postDao.getBoardsByCri(criteria);
    }
    public int getCountBoardsByCri(Criteria criteria) {

        return postDao.getCountBoardsByCri(criteria);
    }
}
