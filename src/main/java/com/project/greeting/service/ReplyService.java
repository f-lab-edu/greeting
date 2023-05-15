package com.project.greeting.service;

import com.project.greeting.dao.ReplyDao;
import com.project.greeting.dto.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyDao replyDao;

    public List<ReplyVO> readReply(int postId) throws Exception{
        return replyDao.readReply(postId);
    }

    public void writeReply(ReplyVO vo) {
         replyDao.writeReply(vo);
    }
}
