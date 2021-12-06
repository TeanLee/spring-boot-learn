package com.example.demo.controller;

import com.example.demo.Bean.Account;
import com.example.demo.Bean.User;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class indexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountService accountService;

    @Autowired
    StringRedisTemplate stringRedisTemplate; // 自动装配

    @ResponseBody
    /**
     * @responsebody这个注解表示你的返回值将存在responsebody中返回到前端，也就是将return返回值作为请求返回值。
     * return的数据不会解析成返回跳转路径，将java对象转为json格式的数据，前端接收后会显示将数据到页面。
     * 如果不加的话 返回值将会作为url的一部分，页面会跳转到这个url，也就是跳转到你返回的这个路径。
     */
    // controller 只是调用 service 中的内容
    @GetMapping("/acct/{id}")
    public Account getById(@PathVariable("id") Long id) {
        return accountService.getAccByid(id);
    }

    @ResponseBody
    @GetMapping("/sql")
    public String queryFromDb() {
        Long aLong = jdbcTemplate.queryForObject("SELECT count(*) FROM `first-database`.account;", Long.class);
        return aLong.toString();
    }

    /**
     * 登录页。两个路径跳转到登录页面（login.html）
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {

        // 在表单提交的时候，会传递 username 和 password，对应 User 的两个似有属性，会作为 User 对象传递过来
        // 在这里我们只进行简单的判断，如果传递的内容不为空，我们就认定为登录成功
        if ("root".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            // 把登录成功的用户保存起来
            session.setAttribute("loginUser", user);
            // 登录成功重定向到 index.html，重定向防止表单的重复提交
            return "redirect:/index.html";
        } else {
            model.addAttribute("msg", "账号或密码错误");
            return "login";
        }
    }

    /**
     * 为了解决在刷新 index 页面反复提交的问题，设置了重定向页面
     * @return
     */
    @GetMapping("index.html")
    public String mainPage(HttpSession session, Model model) {

        // 访问需要登录的页面，需要用 拦截器 或者 过滤器 进行处理。在这里我们先简单的去 session 中取值
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null) {
            return "index";
        } else {
            model.addAttribute("msg", "请重新登录");
            return "login";
        }
    }

    @ResponseBody
    @GetMapping("/main")
    public String mainTestRedis() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        String dynamicTable = valueOperations.get("/dynamic_table");

        return dynamicTable;
    }
}
