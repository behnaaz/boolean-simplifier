package be.ohlson.sequenceplanner;

import java.util.LinkedList;
import java.util.List;

public class LogicNode {

	private Operation operation;
	private boolean negation;
	
	private List<LogicalVariable> variables;
	
	private List<LogicNode> nodes;
	
	enum Operation { AND, OR, EMPTY };
	
	public LogicNode(Operation operation, boolean negation) {
		this.operation = operation;
		this.negation = negation;
		
		this.variables = new LinkedList<LogicalVariable>();
		this.nodes = new LinkedList<LogicNode>();
	}

	
	public boolean hasLogicalNodes() {
		return nodes.size() == 0;
	}


	public Operation getOperation() {
		return operation;
	}


	public void setOperation(Operation operation) {
		this.operation = operation;
	}


	public boolean isNegation() {
		return negation;
	}


	public void setNot(boolean not) {
		this.negation = not;
	}


	public List<LogicalVariable> getVariables() {
		return variables;
	}


	public void setVariables(List<LogicalVariable> variables) {
		this.variables = variables;
	}


	public List<LogicNode> getNodes() {
		return nodes;
	}


	public void setNodes(List<LogicNode> nodes) {
		this.nodes = nodes;
	}
	
	
	
}
