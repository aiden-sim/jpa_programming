package chapter1;

class Member {
	String id;       // MEMBER_ID 컬럼 사용
	//Long teamId;     // TEAM_ID FK 컬럼 사용
	Team team;       // 참조로 연관관계를 맺는다.
	String username; // USERNAME 컬럼 사용
}

class Team {
	Long id;        // TEAM_ID PK 사용
	String name;    // NAME 컬럼 사용
}