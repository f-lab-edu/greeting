package com.project.greeting.dao;

import com.project.greeting.dto.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDAOImpl implements ReplyDao  {
    @Autowired
    SqlSession session;

    private static final String NS = "replyMapper";
    private static final String READ = NS + ".readReply";
    private static final String CREATE = NS + ".writeReply";

    // 댓글 조회 리스트
    @Override
    public List<ReplyVO> readReply(int postId)  throws Exception{
        return session.selectList(READ, postId); //(String,Object)
    }

    // 댓글 작성
    @Override
    public void writeReply(ReplyVO vo) {
        session.insert(CREATE, vo);
    }
}
