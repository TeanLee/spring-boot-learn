package com.example.demo;

import com.alibaba.druid.support.http.StatViewServlet;
import com.example.demo.Bean.User;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

	// 容器中有 jdbcTemplate，因此可以自动注入（测试 JDBC 数据连接）
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired // 通过 Bean 自动装配了，因此可以在这自动获取 dataSource
	DataSource dataSource;

	@Autowired
	UserMapper userMapper; // 测试 mybatis-plus 的 CURD

	@Autowired // 自动注入 redis 客户端
	StringRedisTemplate stringRedisTemplate;

	@Test
	void contextLoads() {
		// 第一个参数是 query 语句，第二个参数是将查询出来的结果封装成一个对象
//		jdbcTemplate.queryForList();

		// 比如下面的语句，第一个是查询所有的值，第二个参数是将查询的结果（数字）封装成一个 Long 对象
		Long aLong = jdbcTemplate.queryForObject("SELECT count(*) FROM `first-database`.account;", Long.class);
		log.info("记录总数：{}",aLong);

		log.info("数据源：{}", dataSource.getClass());
	}

	// 测试 mybatis-plus 的增删改查，只需要 Mapper 继承 BaseMapper 就可以实现 CURD
	@Test
	void testUserMapper() {
		User user = userMapper.selectById(1);
		log.info("用户信息：{}", user);
	}


	// 测试 redis 的连接
	@Test
	void testRedis() {
		ValueOperations<String, String> operation = stringRedisTemplate.opsForValue(); // opsForValue 方法表示操作字符串，还有其他方法能操作 hash、list、set 等（https://www.codenong.com/cs107117774/）

		operation.set("hello", "world");

		String hello = operation.get("hello");
		log.info(hello);
	}

}
