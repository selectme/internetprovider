package by.epam.learn.mudrahelau.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtil {

    public static String hashPassword(String password){
        return DigestUtils.md5Hex(password);
    }
}
