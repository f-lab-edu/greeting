package com.project.greeting.dao;

import com.project.greeting.dto.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDAOImpl implements ReplyDao {
    @Autowired
    SqlSession session;
    private static final String NS = "replyMapper";
    private static final String READ = NS + ".readReply";
    private static final String CREATE = NS + ".writeReply";
    private static final String UPDATE = NS + ".updateReply";
    private static final String DELETE = NS + ".deleteReply";
    private static final String SELECTREPLY = NS + ".selectReply";

    // 댓글 조회 리스트
    @Override
    public List<ReplyVO> readReply(int id) throws Exception {

        return session.selectList(READ, id); //(String,Object)
    }
    // 댓글 작성
    @Override
    public void writeReply(ReplyVO replyVO) throws Exception {

        session.insert(CREATE, replyVO);
    }
    // 댓글 수정
    @Override
    public void replyUpdate(ReplyVO replyVO) {

        session.update(UPDATE,replyVO);
    }
    // 특정 댓글 조회
    @Override
    public ReplyVO selectReply(int replyId) {

        return session.selectOne(SELECTREPLY, replyId);
    }
    // 댓글 삭제
    @Override
    public void replyDelete(ReplyVO replyVO) throws Exception {

        session.delete(DELETE,replyVO);
    }
}
