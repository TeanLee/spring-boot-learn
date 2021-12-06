package com.example.demo.Bean;

public class Account {
    private Long id;
    private String userId;
    private Integer money;

    public Account() {
    }

    public Account(Long id, String userId, Integer money) {
        this.id = id;
        this.userId = userId;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
