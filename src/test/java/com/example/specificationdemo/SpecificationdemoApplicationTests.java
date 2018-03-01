package com.example.specificationdemo;

import com.example.specificationdemo.bean.SpecificationFactory;
import com.example.specificationdemo.bean.User;
import com.example.specificationdemo.repository.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecificationdemoApplicationTests {

	private List<User> userList = new ArrayList<>();

	@Autowired
	private UserRepo userRepo;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setup() {
		userList.add(User.builder().name("Paul").age(30).build());
		userList.add(User.builder().name("Luna").age(29).build());
		userList.add(User.builder().name("Kate").age(10).build());
		userRepo.save(userList);
	}

	@Test
	public void test() {
		Specifications specifications = Specifications
				.where(SpecificationFactory.contains("name", "u"))
				.where(SpecificationFactory.greaterThanOrEqualTo("age", 30));
		List all = userRepo.findAll(specifications);
		all.forEach(System.out::println);
	}

    @Test
    public void test2() {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name", match -> match.contains());
        Example<User> userExample = Example.of(User.builder().name("u").build(), matcher);
	    userRepo.findAll(userExample).forEach(System.out::println);
    }

}
