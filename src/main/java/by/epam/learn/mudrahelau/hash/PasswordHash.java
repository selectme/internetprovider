package by.epam.learn.mudrahelau.hash;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHash {

    public static String hashPassword(String password){
        return DigestUtils.md5Hex(password);
    }
}
