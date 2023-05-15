package com.project.greeting.dao;

import com.project.greeting.controller.Inject;
import com.project.greeting.dto.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDAOImpl implements ReplyDao  {
    @Inject
    SqlSession session;

    private static final String NS = "replyMapper";
    private static final String READ = NS + ".readReply";

    @Override
    public List<ReplyVO> readReply(int postId)  throws Exception{
        return session.selectList("Read", postId);
    }
}
