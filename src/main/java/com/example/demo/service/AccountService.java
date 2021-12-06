package com.example.demo.service;

import com.example.demo.Bean.Account;
import com.example.demo.mapper.AccountMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 处理与数据库的交互逻辑
@Service
public class AccountService {
    @Autowired
    AccountMaper accountMaper;

    public Account getAccByid(Long id) {
        return accountMaper.getAcct(id);
    }
}
