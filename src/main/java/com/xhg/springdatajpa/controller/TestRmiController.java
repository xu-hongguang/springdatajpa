package com.xhg.springdatajpa.controller;

import com.alibaba.fastjson.JSON;
import com.xhg.springdatajpa.dao.InfoRepository;
import com.xhg.springdatajpa.dao.RepliesRepository;
import com.xhg.springdatajpa.pojo.Informations;
import com.xhg.springdatajpa.pojo.Replies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 16033
 */
@Controller
public class TestRmiController {

    @Autowired
    private RepliesRepository repliesRepository;

    @Autowired
    private InfoRepository infoRepository;

    @RequestMapping(value = "/rmi")
    @ResponseBody
    public String getReplies() {
        return JSON.toJSONString(repliesRepository.findById(2).get());
    }

}
