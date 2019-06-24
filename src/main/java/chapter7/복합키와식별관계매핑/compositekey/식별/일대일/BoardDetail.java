package chapter7.복합키와식별관계매핑.compositekey.식별.일대일;

import javax.persistence.*;

//자식
//@Entity
public class BoardDetail {
	@Id
	private Long boardId;

	@MapsId //BoardDetail.boardId 매핑
	@OneToOne
	@JoinColumn(name = "BOARD_ID")
	private Board board;

	private String content;

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
