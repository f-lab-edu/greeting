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

    @GetMapping("/post_ins")
    public String doInsert() {
        return "/postWrite";
    }

    @PostMapping("/post_ins")
    public String doInsertPost(@ModelAttribute PostDto post) {
        postService.insertPost(post);
        return "redirect:/list";
    }
    @GetMapping("/detail")
    public String doView(@RequestParam("id") Long id, Model model) {
        model.addAttribute("post",postService.selectDetail(id));
        return "/postDetail";
    }


}
