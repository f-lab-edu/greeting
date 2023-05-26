package com.project.greeting.dao;

import com.project.greeting.dto.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface ReplyDao {

   public List<ReplyVO> readReply(int id) throws Exception;
   public void writeReply(ReplyVO replyVO) throws Exception;
   public void replyUpdate(ReplyVO replyVO);
   public ReplyVO selectReply(int replyId);
   public void replyDelete(ReplyVO replyVO) throws Exception ;
}
