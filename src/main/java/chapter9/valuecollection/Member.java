package chapter9.valuecollection;

import javax.persistence.*;
import java.util.*;

@Entity
public class Member {
	@Id @GeneratedValue
	private Long id;

	@Embedded
	private Address homeAddress;

	@ElementCollection
	@CollectionTable(name = "FAVORITE_FOODS",
			joinColumns = @JoinColumn(name = "MEMBER_ID"))
	@Column(name = "FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<String>();

	@ElementCollection
	@CollectionTable(name = "ADDRESS",
			joinColumns = @JoinColumn(name = "MEMBER_ID"))
	private List<Address> addressesHistory = new ArrayList<Address>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Set<String> getFavoriteFoods() {
		return favoriteFoods;
	}

	public void setFavoriteFoods(Set<String> favoriteFoods) {
		this.favoriteFoods = favoriteFoods;
	}

	public List<Address> getAddressesHistory() {
		return addressesHistory;
	}

	public void setAddressesHistory(List<Address> addressesHistory) {
		this.addressesHistory = addressesHistory;
	}
}
