package by.epam.learn.mudrahelau.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * PasswordUtil is required for password encryption.
 */
public class PasswordUtil {
    /**
     *
     * Encrypts password in MD5 format.
     *
     * @param password for encryption.
     * @return MD5 value in String format.
     */
    public static String hashPassword(String password){
        return DigestUtils.md5Hex(password);
    }
}
