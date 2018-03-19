package user_management;

import user_management.security.Password;

public class User {

    private String email;
    private int id;
    private String name;
    private Password password;

    public User(int id, String aName, String anEmail, Password aPassword) {
        this.email = anEmail;
        this.id = id;
        this.name = aName;
        this.password = aPassword;
    }

    public User(int id, String aName, String anEmail, String aPassword) {
        this.email = anEmail;
        this.id = id;
        this.name = aName;
        this.password = new Password(aPassword);
    }

    public User() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = new Password(password);
    }

    @Override
    public String toString() {
        return this.name + " - " + this.email;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof User) {
            User otherUser = (User) obj;
            return this.name.equalsIgnoreCase(otherUser.getName())
                    && this.email.equalsIgnoreCase(otherUser.getEmail())
                    && this.id == otherUser.getId();
        } else {
            return false;
        }
    }
}
