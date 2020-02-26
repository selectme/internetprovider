package by.epam.learn.mudrahelau.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PasswordUtilTest {

    @Test
    public void testHashPassword() {

        String expectedHashedPassword = "f93fc10472a31bb3061aa0b45e228c5a";

        String actualHashedPassword = PasswordUtil.hashPassword("strongpassword");

        assertEquals(actualHashedPassword, expectedHashedPassword);

    }
}