package chapter7.attributeoverride;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

// 하나 재정의
/*@Entity
@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID"))*/
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
