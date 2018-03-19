package user_management;

import user_management.security.UserAuthenticationFailedException;
import user_management.validation.EmailNotAvailableException;
import user_management.validation.InvalidEmailException;
import user_management.validation.PasswordTooSimpleException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCollection extends ArrayList<User> {

    public User findById(int id) {

        for (User user : this) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User findByEmail(String email) {

        for (User user : this) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public User attemptLogin(String email, String password) throws UserAuthenticationFailedException {

        if (findByEmail(email).getPassword().matches(password)) {
            return findByEmail(email);
        } else {
            throw new UserAuthenticationFailedException();
        }
    }

    public int createUser(String name, String email, String password) throws EmailNotAvailableException, InvalidEmailException, PasswordTooSimpleException {

        if (findByEmail(email) != null) {
            throw new EmailNotAvailableException();
        }

        if (!validateEmail(email)) {
            throw new InvalidEmailException();
        }

        if (!passwordIsComplex(password) || password.length() < 8) {
            throw new PasswordTooSimpleException();
        }

        int nextId = getCurrentHighestId() + 1;

        User created = new User(nextId, name, email, password);
        this.add(created);

        return nextId;
    }


    private boolean validateEmail(String email) {
        if (findName(email) == null) {
            return false;
        }

        if (findDomain(email) == null) {
            return false;
        }

        if (findTld(email) == null) {
            return false;
        }

        return true;
    }

    private boolean passwordIsComplex(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*(_|[^\\w])).+$");
        Matcher matcher = pattern.matcher(password);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private String findName(String email) {
        Pattern pattern = Pattern.compile("^[^\\W]+(?=[@])");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            if (matcher.group().length() > 0) {
                return matcher.group();
            } else {
                return null;
            }
        }
        return null;
    }

    private String findDomain(String email) {
        Pattern pattern = Pattern.compile("(?<=(@))[^\\W]+(?=\\.)");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            if (matcher.group().length() > 0) {
                return matcher.group();
            } else {
                return null;
            }
        }
        return null;
    }

    private String findTld(String email) {
        Pattern pattern = Pattern.compile("(?<=(\\.)).+");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            if (matcher.group().length() > 0 && matcher.group().length() < 10) {
                return matcher.group();
            } else {
                return null;
            }
        }
        return null;
    }

    private int getCurrentHighestId() {
        int highestId = 0;

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId() > highestId) {
                highestId = this.get(i).getId();
            }
        }
        return highestId;
    }


}
