package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Bean.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class TableController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/basic_table", "/basic_table.html"})
    public String basic_table() {
        return "table/basic_table";
    }

    @ResponseBody
    @GetMapping(value = {"/dynamic_table", "/dynamic_table.html"})
    public Map<String, Object> dynamic_table(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {

        //表格内容的遍历
//        List<User> users = Arrays.asList(new User("zhangsan", "123456"),
//                new User("lisi", "123444"),
//                new User("haha", "aaaaa"),
//                new User("hehe ", "aaddd"));
//        model.addAttribute("users",users);



        // 从数据库中查出 user 表中的用户进行展示
        // userService 方法里面由于在 UserServiceImple 中 extends ServiceImpl<UserMapper, User> implements UserService。增删改查都是现有的调接口
        List<User> list = userService.list();  // list 方法查询表中的所有数据


        // 注意：分页查询需要在 config 的 MyBatisConfig 中添加分页查询插件才能真正使用分页查询，否则查询的内容有误
        // 分页查询数据
        Page userPage =  new Page<User>(1, 2);
        // 分页查询的结果
        Page<User> page = userService.page(userPage, null);

        Long total = page.getTotal(); // 总记录数
        Long current = page.getCurrent();
        Long pages = page.getPages();

        log.info("总记录数为{}，当前记录为第{}页，一共有{}页", total, current, pages);

        Map map = new HashMap();
        map.put("users", list);

        map.put("page", page);
        return map;
    }

    @ResponseBody
    @DeleteMapping("/dynamic_table/{id}")
    public String deleteDynamic(@PathVariable("id") int id) {
        Boolean bool = userService.removeById(id);
        return bool ? "删除成功！" : "删除失败";
    }

    @GetMapping(value = {"/editable_table", "/editable_table.html"})
    public String editable_table() {
        return "table/editable_table";
    }

    @GetMapping(value = {"/pricing_table", "/pricing_table.html"})
    public String pricing_table() {
        return "table/pricing_table";
    }
}
