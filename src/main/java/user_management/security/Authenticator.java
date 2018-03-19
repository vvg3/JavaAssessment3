package user_management.security;

import user_management.User;

public class Authenticator {
    public static boolean authenticate(User user, String passwordToCheck) {
        if (user.getPassword().matches(passwordToCheck)) {
            return true;
        } else {
            return false;
        }
    }
}

