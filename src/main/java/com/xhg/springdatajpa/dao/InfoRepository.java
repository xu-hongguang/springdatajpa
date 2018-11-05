package com.xhg.springdatajpa.dao;

import com.xhg.springdatajpa.pojo.Informations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 16033
 */
public interface InfoRepository extends JpaRepository<Informations,Integer>,JpaSpecificationExecutor<Informations> {

    @Query(value = "select title from Informations where title like %:title%")
    List<Informations> findAllByTitleInfo(@Param("title") String title);

}
