package chapter9.immutable;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String city;

	protected Address() {
	} // JPA에서 기본 생성자는 필수

	// 생성자로 초기 값을 설정한다.
	public Address(String city) {
		this.city = city;
	}

	// 접근자(Getter)는 노출한다.
	public String getCity() {
		return city;
	}
}
