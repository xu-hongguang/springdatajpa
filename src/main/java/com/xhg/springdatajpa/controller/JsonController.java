package com.xhg.springdatajpa.controller;

import com.alibaba.fastjson.JSON;
import com.xhg.springdatajpa.pojo.ReplyQuery;
import com.xhg.springdatajpa.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 16033
 */
@Controller
public class JsonController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/jsonCommit",method = RequestMethod.POST)
    @ResponseBody
    public String json(@RequestBody ReplyQuery replyQuery){

        System.out.println("结果：" +replyQuery);

        return JSON.toJSONString(replyQuery);
    }

}
