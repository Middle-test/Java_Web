package com.itheima;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest2 {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void getGender_NullIdCard_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender(null);
        });
    }

    @Test
    public void getGender_IdCardLengthNot18_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender("12345678901234567"); // 17位
        });
    }

    @Test
    public void getGender_ValidIdCardWithOdd17thDigit_ReturnsMale() {
        String idCard = "100000200010011011"; // 第17位是1，奇数
        String gender = userService.getGender(idCard);
        Assertions.assertEquals("男", gender);
    }

    @Test
    public void getGender_ValidIdCardWithEven17thDigit_ReturnsFemale() {
        String idCard = "100000200010011012"; // 第17位是1，奇数
        String gender = userService.getGender(idCard);
        Assertions.assertEquals("男", gender);
    }
}


