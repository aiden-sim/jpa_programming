package chapter4;

import javax.persistence.*;

@Entity
@TableGenerator(
		name = "BOARD_SEQ_GENERATOR",
		table = "MY_SEQUENCES",
		pkColumnValue = "BOARD_SEQ", // SEQUEST_NAME의 Value가 된다.
		allocationSize = 1
)
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BOARD_SEQ_GENERATOR")
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
