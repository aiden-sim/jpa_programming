package chapter7.mappedsuperclass.base;

//@Entity
public class Member extends BaseEntity {
    //ID 상속
    //NAME 상속
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
