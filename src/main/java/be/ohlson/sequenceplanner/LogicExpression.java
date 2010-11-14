package be.ohlson.sequenceplanner;

public class LogicExpression {
	
	private LogicNode root;
	
	public LogicExpression() {
	}

	public LogicNode createEndGetRoot(LogicNode.Operation operation) {
		setRoot(new LogicNode(operation, false));
		return getRoot();
	}
	
	public void setRoot(LogicNode root) {
		this.root = root;
	}
	
	public LogicNode getRoot() {
		return root;
	}
	
}
