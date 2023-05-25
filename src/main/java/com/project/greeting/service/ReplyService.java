package com.project.greeting.service;

import com.project.greeting.dao.ReplyDao;
import com.project.greeting.dto.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;
    public List<ReplyVO> readReply(int id) throws Exception{
        return replyDao.readReply(id);
    }
    public void writeReply(ReplyVO replyVO) throws Exception {
         replyDao.writeReply(replyVO);
    }
    public void replyUpdate(ReplyVO replyVO) {
        replyDao.replyUpdate(replyVO);
    }
    public ReplyVO selectReply(int replyId) {
        return replyDao.selectReply(replyId);
    }
    public void replyDelete(ReplyVO replyVO) throws Exception {
        replyDao.replyDelete(replyVO);
    }
}
