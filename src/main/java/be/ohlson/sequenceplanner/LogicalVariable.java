package be.ohlson.sequenceplanner;

public class LogicalVariable {

	private String name;
	private boolean negation;

	public LogicalVariable(String name, boolean negation) {
		this.name = name;
		this.negation = negation;
	}

	public String getName() {
		return name;
	}

	public boolean isNegation() {
		return negation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNot(boolean not) {
		this.negation = not;
	}
}
