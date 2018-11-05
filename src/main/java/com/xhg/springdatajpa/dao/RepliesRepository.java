package com.xhg.springdatajpa.dao;

import com.xhg.springdatajpa.pojo.Informations;
import com.xhg.springdatajpa.pojo.Replies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author 16033
 */
public interface RepliesRepository extends JpaRepository<Replies,Integer>,JpaSpecificationExecutor<Replies> {

    /**模糊查询*/
    List<Replies> findByContentLike(@Param("content") String content);

    /**自定义SQL语句*/
    @Query(value = "select id,content,replyTime,infoId from replies where infoId = ?1",nativeQuery = true)
    List<Replies> findByInfoId(Integer infoId);

    List<Replies> findByInfoId(Informations infoId);

    /**
     * 修改数据   （还可以直接使用save()方法，它会把主键重复的覆盖(即被修改)）
     * @param content
     * @param id
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Replies set content = ?1 where id = ?2")
    int updateReplies(String content,Integer id);


}
