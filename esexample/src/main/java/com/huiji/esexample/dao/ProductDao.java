package com.huiji.esexample.dao;


import com.huiji.esexample.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends ElasticsearchRepository<Product, Long> {

    //根据Title域中的关键词查找数据
    List<Product> findByTitle(String str);
}
