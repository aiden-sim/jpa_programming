package chapter9.embeddedtype.relation;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Member {
	@Id @GeneratedValue
	private Long id;

	@Embedded Address address; //임베디드 타입 포함
	@Embedded PhoneNumber phoneNumber; //임베디드 타입 포함
}
