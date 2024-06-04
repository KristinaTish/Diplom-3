package apimethods;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

public class UserReg {

    // это для тела JSON
    private String email;
    private String password;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserReg(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static UserReg someUserData(){
        return new UserReg("krovli-sarai2211567@mail.ru", "12345qwerty", "Oleg");
    }

}
