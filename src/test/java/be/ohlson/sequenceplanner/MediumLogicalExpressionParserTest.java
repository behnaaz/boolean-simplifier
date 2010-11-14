package be.ohlson.sequenceplanner;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class MediumLogicalExpressionParserTest {

	private LogicalExpressionParser parser;

	@Before
	public void setup() {
		parser = new LogicalExpressionParser();
	}
	
	@Test
	public void testMixedOperatorsWithoutParanAndNotNegatedVariables() {
		String expression = "a+b*c";

		LogicExpression result = parser.parse(expression);

		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);
		
		// Check topnode
		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.OR, topNode.getOperation());

		// Check variables
		assertEquals(1, topNode.getVariables().size());
		assertHasVariable(topNode, new LogicalVariable("a", false));
		
		// Has no childnodes
		List<LogicNode> childs = topNode.getNodes();
		assertEquals(1, childs.size());
		
		LogicNode andNode = childs.get(0);
		assertEquals(0, andNode.getNodes().size());
		assertEquals(LogicNode.Operation.AND, andNode.getOperation());
		assertHasVariable(topNode, new LogicalVariable("b", false));
		assertHasVariable(topNode, new LogicalVariable("c", false));
	}
	
	public void assertHasVariable(LogicNode parent,
			LogicalVariable expectedVariable) {
		List<LogicalVariable> variables = parent.getVariables();

		for (LogicalVariable var : variables) {
			if (expectedVariable.isNegation() == var.isNegation()
					&& expectedVariable.getName().equals(var.getName())) {
				return;
			}
		}

		fail("Variable " + expectedVariable + " where not child of parent node");
	}
}
