package chapter14;

import javax.persistence.Convert;
import javax.persistence.Id;

//@Entity
public class Member {
    @Id
    private String id;
    private String username;

    @Convert(converter = BolleanToYNConverter.class)
    private boolean vip;
}
