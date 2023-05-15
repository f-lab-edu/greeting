package com.project.greeting.dao;

import com.project.greeting.dto.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface ReplyDao {

    List<ReplyVO> readReply(int postId) throws Exception;
}
