package com.project.greeting.controller;

import com.project.greeting.dto.Criteria;
import com.project.greeting.dto.PageDto;
import com.project.greeting.dto.PostDto;
import com.project.greeting.dto.ReplyVO;
import com.project.greeting.service.PostService;
import com.project.greeting.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    ReplyService replyService;


    // 게시물 리스트,페이징 및 검색
    @GetMapping("/list")
    public String postList(Criteria cri, Model model) {

        List<PostDto> postList= postService.getBoardsByCri(cri);

        int totalCount = postService.getCountBoardsByCri(cri);

        PageDto pageDto = new PageDto(cri,totalCount);

        model.addAttribute("list" , postList);
        model.addAttribute("pageMaker", pageDto);


        return "/postList";
    }


    //게시물 작성 페이지이동
    @GetMapping("/post_ins")
    public String doInsert() {

        return "/postWrite";
    }
    //게시물 작성 Post
    @PostMapping("/post_ins")
    public String doInsertPost(@ModelAttribute PostDto post) {
        postService.insertPost(post);
        return "redirect:/list";
    }
    //게시물 상세보기 & 댓글 리스트
    @GetMapping("/detail")
    public String doView(PostDto postDto,
                         @RequestParam("id") Long id,
                         @RequestParam(required = false, defaultValue = "1") int pageNum,
                         Criteria cri,
                         Model model) throws Exception{
        model.addAttribute("post",postService.selectDetail(id));
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("cri",cri);

        // 댓글 관련 추가 부분
        List<ReplyVO> replyList = replyService.readReply(postDto.getId());
        model.addAttribute("replyList", replyList);

        return "/postDetail";
    }
    //게시물 삭제
    @GetMapping("/delete")
    public String doDelete(@RequestParam("id") Long id) {
        postService.deletePost(id);
        return "redirect:/list";
    }
    // 게시물 수정 페이지이동
    @GetMapping("/update")
    public String doUpdate(@RequestParam("id") Long id, Model model) {
        model.addAttribute("board",postService.selectDetail(id));
        return  "/postUpdate";

    }
    // 게시물 수정후 Post
    @PostMapping("/update")
    public String doUpdatePost(@ModelAttribute PostDto post) {
        postService.updatePost(post);
        return "redirect:/list";
    }

    // 댓글 작성
    @PostMapping("/replyWrite")
    public String replyWrite(ReplyVO vo, Criteria cri, RedirectAttributes rttr) {

        replyService.writeReply(vo);

        rttr.addAttribute("post_id", vo.getPostId());
        rttr.addAttribute("pageNum",cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());

        return "redirect:/detail";
    }


}
