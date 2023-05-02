package com.project.greeting.controller;

import com.project.greeting.dto.PostDto;
import com.project.greeting.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
