package com.xhg.springdatajpa.dao;

import com.xhg.springdatajpa.pojo.Replies;

import java.util.List;

/**
 * @author 16033
 */
public interface ReplyMapper {
    List<Replies> findAll();
}
