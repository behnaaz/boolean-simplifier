package be.ohlson.sequenceplanner;

import java.util.Arrays;

import be.ohlson.sequenceplanner.LogicNode.Operation;

public class LogicalExpressionParser {

	
	public LogicalExpressionParser() {
	}
	
	public LogicExpression parse(String booleanExpression) {
		LogicExpression result = new LogicExpression();
		
		LogicNode node = new LogicNode(Operation.EMPTY, false);
		
		
		
		LogicalVariable var1 = null;
		if (booleanExpression.substring(0, 1).equals("^")) {
			var1 = new LogicalVariable(booleanExpression.substring(1), true);
		} else {
			var1 = new LogicalVariable(booleanExpression, false);
		}
		
		node.setVariables(Arrays.asList(var1));
		result.setRoot(node);
		
		return result;
	}
	
	
	
}
