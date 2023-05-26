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
    public String list(Criteria criteria, Model model) {

        List<PostDto> list= postService.getBoardsByCri(criteria);

        int totalCount = postService.getCountBoardsByCri(criteria);

        PageDto pageDto = new PageDto(criteria,totalCount);

        model.addAttribute("list" , list);
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
    public String doInsert(@ModelAttribute PostDto postDto) {
        postService.insertPost(postDto);
        return "redirect:/list";
    }
    //게시물 상세보기 & 댓글 리스트
    @GetMapping("/detail")
    public String doView(PostDto postDto,
                         @RequestParam(value = "id" , required = false) Long id,
                         @RequestParam(required = false, defaultValue = "1") int pageNum,
                         @ModelAttribute Criteria criteria,
                         Model model) throws Exception{

        PostDto post = postService.selectDetail(id);

        model.addAttribute("post",post);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("cri",criteria);

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
    public String doUpdate(@ModelAttribute PostDto postDto) {

        postService.updatePost(postDto);

        return "redirect:/list";
    }
    // 댓글 작성
    @PostMapping("/replyWrite")
    public String replyWrite(@ModelAttribute ReplyVO replyVO,
                             @ModelAttribute PostDto postDto,
                             Criteria criteria,
                             RedirectAttributes rttr) throws Exception {

        replyService.writeReply(replyVO);

        rttr.addAttribute("posting", replyVO.getId());
        rttr.addAttribute("pageNum",criteria.getPageNum());
        rttr.addAttribute("amount", criteria.getAmount());

        return "redirect:/detail?id="+postDto.getId();
    }
    // 댓글 수정 GET
    @GetMapping("/replyUpdate")
    public String relyUpdate(@ModelAttribute ReplyVO replyVO,
                             Criteria criteria,
                             Model model) {

        ReplyVO reply = replyService.selectReply(replyVO.getReplyId());

        model.addAttribute("replyUpdate",reply);
        model.addAttribute("cri",criteria);

        return "/commentUpdate";
    }
    // 댓글 수정 POST
    @PostMapping("/replyUpdate")
    public String replyUpdate(@ModelAttribute ReplyVO replyVO,
                                  @ModelAttribute PostDto postDto,
                                  Criteria criteria,
                                  RedirectAttributes rttr) {

        replyService.replyUpdate(replyVO);

        rttr.addAttribute("pageNum",criteria.getPageNum());
        rttr.addAttribute("amount", criteria.getAmount());

        return "redirect:/detail?id="+postDto.getId();
    }
    // 댓글 삭제 GET
    @GetMapping("/replyDelete")
    public String replyDelete(@ModelAttribute ReplyVO replyVO,
                              Model model) {

        ReplyVO reply = replyService.selectReply(replyVO.getReplyId());

        model.addAttribute("replyDelete",reply);

        return "/commentDelete";
    }
    // 댓글 삭제 POST
    @PostMapping("/replyDelete")
    public String replyDelete(@ModelAttribute ReplyVO replyVO,
                              @ModelAttribute PostDto postDto,
                              Criteria criteria,
                              RedirectAttributes rttr) throws Exception {

        replyService.replyDelete(replyVO);

        ReplyVO reply = replyService.selectReply(replyVO.getReplyId());

        rttr.addAttribute("replyDelete",reply);
        rttr.addAttribute("pageNum",criteria.getPageNum());
        rttr.addAttribute("amount", criteria.getAmount());

        return "redirect:/detail?id="+postDto.getId();
    }
}
