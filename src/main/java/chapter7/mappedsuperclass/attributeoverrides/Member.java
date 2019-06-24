package chapter7.mappedsuperclass.attributeoverrides;

// 둘 이상 재정의
/*@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID")),
        @AttributeOverride(name = "name", column = @Column(name = "MEMBER_NAME"))
})*/
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
