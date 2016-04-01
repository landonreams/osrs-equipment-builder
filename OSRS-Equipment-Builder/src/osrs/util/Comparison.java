package osrs.util;

public enum Comparison {
	EQUAL("="),
	GREATER(">"),
	GREATER_EQUAL(">="),
	LESS("<"),
	LESS_EQUAL("<="),
	NOT_EQUAL("!=");

	private String token;

	private Comparison(String token) {
		this.token = token;
	}

	@Override
	public String toString(){
		return token;
	}
}
