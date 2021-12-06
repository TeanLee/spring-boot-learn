package com.example.demo.mapper;

import com.example.demo.Bean.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * 写在 mapper 的接口不实现具体功能，仅当声明使用。具体实现在 xml 文件中
 */
@Mapper // 需要对 mapper 进行标注，说明这是一个 map，里面的内容才会生效
public interface AccountMaper {
    public Account getAcct(Long id);
}
