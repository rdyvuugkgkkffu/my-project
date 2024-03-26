package com.example.demo;

import com.example.demo.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo3ApplicationTests {

    @Test
    void contextLoads() {
        JwtHelper.createToken(123L, 2);
    }

}
