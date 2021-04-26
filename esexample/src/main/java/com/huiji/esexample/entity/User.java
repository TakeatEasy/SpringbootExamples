package com.huiji.esexample.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


@Document(indexName = "userIndex")
public class User implements Serializable{

    private static final long serialVersionUID = 1l;
//    编号
    private Long id;
//    姓名
    private String name;
//    年龄
    private Integer age;
//    描述信息
    private String description;
//    创建时间
    private String createTime;
}
