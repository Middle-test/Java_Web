package com.itheima;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("用户信息测试类")
public class UserServiceTest {
    @Test
    public void testGetAge() {
        UserService userService = new UserService();
        Integer age = userService.getAge("100000200010011011");

    }

    @Test
    public void testGetGender() {
        UserService userService = new UserService();
        String gender = userService.getGender("100000200010011011");
        System.out.println(gender);
        //断言操作
        Assertions.assertEquals("男", gender);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender(null);
        });
    }


//    @BeforeAll //在所有单元测试方法运行之前，执行一次
//    public static void beforeAll(){
//        System.out.println("BeforeAll");
//    }
//
//    @AfterAll //在所有单元测试方法运行之后，执行一次
//    public static void afterAll(){
//        System.out.println("AfterAll");
//    }
//
//    @BeforeEach //在每个单元测试方法运行之前，执行一次
//    public void beforeEach(){
//        System.out.println("BeforeEach");
//    }
//
//    @AfterEach //在每个单元测试方法运行之后，执行一次
//    public void afterEach(){
//        System.out.println("AfterEach");
//    }

    /**
     * 参数化测试
     */
    @DisplayName("测试用户性别")
    @ParameterizedTest
    @ValueSource(strings = {"100000200010011011", "100000200010011012"})
    public void testGetGender2(String idCard) {
        UserService userService = new UserService();
        String gender = userService.getGender(idCard);
        //断言操作
        Assertions.assertEquals("男", gender);
    }
}
