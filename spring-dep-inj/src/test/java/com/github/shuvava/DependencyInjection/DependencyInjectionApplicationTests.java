package com.github.shuvava.DependencyInjection;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DependencyInjectionApplicationTests {

	@Autowired
	List<SimpleService> simpleServices;

	@Test
	void contextLoads() {
		assertEquals(2, simpleServices.size());
	}

}
