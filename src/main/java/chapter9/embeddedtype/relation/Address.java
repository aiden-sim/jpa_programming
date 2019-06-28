package chapter9.embeddedtype.relation;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

//@Embeddable
public class Address {
	String street;
	String city;
	String state;
	@Embedded Zipcode zipcode; //임베디드 타입 포함
}
