package com.example.demo.Bean;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * mybatis-plus 要求类名和 数据库 table 名一样，如果不一样，需要用注解 @TableName("table_name") 进行标注
 */
public class User {
    /**
     * 要使用 mybatis-plus 的自动 CURD 功能，需要所有属性都在数据库中，如果不存在数据库中，需要用 @TableField(exist = false) 标注
     */
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String password;

    private Long id;
    private String name;
    private Integer age;
    private String email;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
