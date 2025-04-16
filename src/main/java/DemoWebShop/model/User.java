package DemoWebShop.model;

public class User { //object
    private String name;
    private String lastName;
    private String email;
    private String password;


    public User setEmail(String email) {
        this.email = email;  // назначает емаил
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }  // выдает пароль

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}