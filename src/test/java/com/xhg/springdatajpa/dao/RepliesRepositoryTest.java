package com.xhg.springdatajpa.dao;

import com.xhg.springdatajpa.pojo.Informations;
import com.xhg.springdatajpa.pojo.Replies;
import com.xhg.springdatajpa.pojo.ReplyQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mybatis.xml")
public class RepliesRepositoryTest {
    @Autowired
    private RepliesRepository repliesRepository;

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private ReplyMapper replyMapper;

    /**
     * 一对多查询
     */
    @Test
    @Transactional
    public void test(){

        Optional<Replies> replies = repliesRepository.findById(1);
        Replies reply = replies.get();

        Informations informations = reply.getInfoId();

        // 一对多查询
        Informations informations1 = infoRepository.findById(1).get();
        Set<Replies> repliesSet = informations1.getReplies();

        List<Informations> informationsList = infoRepository.findAllByTitleInfo("大师");

        System.out.println("结果是:" + reply);
        System.out.println("informations:" + informations);
        System.out.println("1号信息：" + informations1.getReplies() + "\n有" + repliesSet.size() + "个回复。");
        System.out.println("查询结果：" + informationsList + "有" + informationsList.size() + "个回复");

    }


    @Test
    public void testReplies(){

        Replies replies = repliesRepository.findById(2).get();

        List<Replies> repliesList = repliesRepository.findAll();


        /*
         * 保存信息
         */
        Informations informations = infoRepository.findById(2).get();
        Replies replies1 = new Replies(64,"修改后",new Date(),informations);
        Replies saceReplies1 = repliesRepository.save(replies1);

        System.out.println("2号信息的一个回答：" + replies);
        System.out.println("所有的reply：" + repliesList);

        System.out.println("1号数量：" + repliesRepository.count());

        System.out.println("保存的回复：" + saceReplies1);

    }

    @Test
    public void testReply(){
        List<Replies> repliesList = repliesRepository.findAll();


//        for (Replies replies:repliesList){
//            System.out.println(replies);
//        }

//        删除
//        repliesRepository.delete(repliesRepository.getOne(67));

        List<Replies> repliesList1 = repliesRepository.findByContentLike("%好喝");
        List<Replies> repliesList2 = repliesRepository.findByInfoId(4);
        List<Replies> repliesList3 = repliesRepository.findByInfoId(infoRepository.getOne(2));
        int rows = repliesRepository.updateReplies("修改之后",63);

        for (Replies replies1: repliesList2){
            System.out.println(replies1);
        }

        System.out.println("修改行数：" + rows);
        System.out.println("查到" + repliesList2.size() + "个数据");

    }


    /**
     * spring data jpa 中使用mybatis
     */
    @Test
    public void testMyBatis(){
        List<Replies> replies = replyMapper.findAll();

        for (Replies reply : replies) {
            System.out.println(reply);
        }
    }

    /**
     * 分页测试
     */
    @Test
    public void testPage(){
        final ReplyQuery replyQuery = new ReplyQuery();
        replyQuery.setInfoId(infoRepository.getOne(2));
        replyQuery.setContent("");
        Calendar calendar = new GregorianCalendar(2018,10-1,10);
        replyQuery.setReplyTime(calendar.getTime());

        int pageNum = 2;  //当前页码
        int pageSize = 10; //每页记录数
        Pageable pageable = PageRequest.of(pageNum-1,pageSize, Sort.Direction.DESC,"replyTime");

        Page<Replies> repliesPage = repliesRepository.findAll(new Specification<Replies>() {
            @Override
            public Predicate toPredicate(Root<Replies> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.like(root.get("content"), "%" + replyQuery.getContent() + "%");
                /*大于*/
                Predicate p2 = criteriaBuilder.greaterThanOrEqualTo(root.get("replyTime").as(String.class), new SimpleDateFormat("yyyy-MM-dd").format(replyQuery.getReplyTime()));
                /*小于*/
                Predicate p3 = criteriaBuilder.lessThanOrEqualTo(root.get("replyTime").as(String.class), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                /*两段日期之间*/
                Predicate p4 = criteriaBuilder.between(root.get("replyTime"),replyQuery.getReplyTime(),new Date());
                Predicate p6 = criteriaBuilder.between(root.get("infoId").get("id"),2,4);
                Predicate p5 = criteriaBuilder.equal(root.get("infoId").get("id"), replyQuery.getInfoId().getId());
                query.where(criteriaBuilder.and(p6));
                return query.getRestriction();
            }
        }, pageable);

        System.out.println("2号信息共有" + repliesRepository.findByInfoId(2).size() + "个回复");
        System.out.println("开始时间：" + new SimpleDateFormat("yyyy-MM-dd").format(replyQuery.getReplyTime()));
        System.out.println("结束时间：" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        System.out.println("总记录数：" + repliesPage.getTotalElements());
        System.out.println("当前页面记录数：" + repliesPage.getNumberOfElements());

        System.out.println("总页数:" + repliesPage.getTotalPages());
        System.out.println("第" + (repliesPage.getNumber() + 1) + "页的所有元素：");
        List<Replies> repliesList = repliesPage.getContent();
        for (Replies replies1: repliesList) {
            System.out.println(replies1);
        }
    }

    @Test
    public void test2(){
        System.out.println(repliesRepository.findByContentLike("%回事%"));
    }


}