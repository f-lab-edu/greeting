package com.project.greeting.controller;

import com.project.greeting.dto.PostDto;
import com.project.greeting.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;
    @RequestMapping("/list")
    public ModelAndView doPost() {

        ModelAndView mv = new ModelAndView("/postList");

        List<PostDto> list = postService.selectPostList();

        mv.addObject("list", list);

        return mv;
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
    //게시물 상세보기
    @GetMapping("/detail")
    public String doView(@RequestParam("id") Long id, Model model) {
        model.addAttribute("post",postService.selectDetail(id));
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

}
