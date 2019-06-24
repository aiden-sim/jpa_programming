package chapter7.복합키와식별관계매핑.compositekey.식별.일대일;

import javax.persistence.*;

//부모
//@Entity
public class Board {
	@Id @GeneratedValue
	@Column(name = " BOARD_ID")
	private Long id;

	private String title;

	@OneToOne(mappedBy = "board")
	private BoardDetail boardDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BoardDetail getBoardDetail() {
		return boardDetail;
	}

	public void setBoardDetail(BoardDetail boardDetail) {
		this.boardDetail = boardDetail;
	}
}
