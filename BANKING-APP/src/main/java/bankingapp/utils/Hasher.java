package bankingapp.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Hasher {

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return  BCrypt.hashpw(password, salt);
    }

    public static  boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
