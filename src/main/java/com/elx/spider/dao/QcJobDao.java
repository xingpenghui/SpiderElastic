package com.elx.spider.dao;

import com.elx.spider.entity.QcJob;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: SpriderEs
 * @description:
 * @author: Feri
 * @create: 2020-03-05 11:04
 */
public interface QcJobDao extends JpaRepository<QcJob,Integer> {
}
