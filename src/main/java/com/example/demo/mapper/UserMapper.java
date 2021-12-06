package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Bean.User;

// 使用 mybatis-plus，只要我们的 Mapper 继承 BaseMapper 就可以拥有 CURD 能力，不需要再手动写配置文件了
public interface UserMapper extends BaseMapper<User> {
}
