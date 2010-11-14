package be.ohlson.sequenceplanner;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SimpleLogicalExpressionParserTest {

	private LogicalExpressionParser parser;

	@Before
	public void setup() {
		parser = new LogicalExpressionParser();
	}

	@Test
	public void testSimpleVariableWithoutBeingNegated() {
		String expression = "var1";

		LogicExpression result = parser.parse(expression);
		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.EMPTY, topNode.getOperation());
		assertEquals(0, topNode.getNodes().size());
		assertEquals(1, topNode.getVariables().size());
		assertHasVariable(topNode, new LogicalVariable("var1", false));
	}

	@Test
	public void testSimpleNegatedVariable() {
		String expression = "^var1";

		LogicExpression result = parser.parse(expression);
		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.EMPTY, topNode.getOperation());
		assertEquals(0, topNode.getNodes().size());
		assertEquals(1, topNode.getVariables().size());
		assertHasVariable(topNode, new LogicalVariable("var1", true));
	}

	@Test
	public void testSimpleAndWithoutNegationAndOneLetterVariables() {
		String expression = "a*b";

		LogicExpression result = parser.parse(expression);

		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		// Check topnode
		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.AND, topNode.getOperation());

		// Has no childnodes
		assertEquals(0, topNode.getNodes().size());

		// Check variables
		List<LogicalVariable> variables = topNode.getVariables();
		assertEquals(2, variables.size());
		assertHasVariable(topNode, new LogicalVariable("a", false));
		assertHasVariable(topNode, new LogicalVariable("b", false));
	}

	@Test
	public void testSimpleOrWithoutAnyNegation() {
		String expression = "a+b";

		LogicExpression result = parser.parse(expression);

		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		// Check topnode
		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.OR, topNode.getOperation());

		// Has no childnodes
		assertEquals(0, topNode.getNodes().size());

		// Check variables
		List<LogicalVariable> variables = topNode.getVariables();
		assertEquals(2, variables.size());
		assertHasVariable(topNode, new LogicalVariable("a", false));
		assertHasVariable(topNode, new LogicalVariable("b", false));
	}

	@Test
	public void testSimpleOrWithoutAnyNegationAndLongVariableNames() {
		String expression = "variable1+variable2";

		LogicExpression result = parser.parse(expression);

		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		// Check topnode
		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.OR, topNode.getOperation());

		// Has no childnodes
		assertEquals(0, topNode.getNodes().size());

		// Check variables
		List<LogicalVariable> variables = topNode.getVariables();
		assertEquals(2, variables.size());
		assertHasVariable(topNode, new LogicalVariable("variable1", false));
		assertHasVariable(topNode, new LogicalVariable("variable2", false));
	}

	@Test
	public void testSimpleAndWithTwoBothVariableNegated() {
		String expression = "^a+^b";

		LogicExpression result = parser.parse(expression);

		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		// Check topnode
		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.AND, topNode.getOperation());

		// Has no childnodes
		assertEquals(0, topNode.getNodes().size());

		// Check variables
		List<LogicalVariable> variables = topNode.getVariables();
		assertEquals(2, variables.size());
		assertHasVariable(topNode, new LogicalVariable("a", true));
		assertHasVariable(topNode, new LogicalVariable("b", true));
	}

	@Test
	public void testSimpleAndWithOneVariableNegated() {
		String expression = "a+^b";

		LogicExpression result = parser.parse(expression);

		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		// Check topnode
		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.AND, topNode.getOperation());

		// Has no childnodes
		assertEquals(0, topNode.getNodes().size());

		// Check variables
		List<LogicalVariable> variables = topNode.getVariables();
		assertEquals(2, variables.size());
		assertHasVariable(topNode, new LogicalVariable("a", false));
		assertHasVariable(topNode, new LogicalVariable("b", true));
	}

	@Test
	public void testSimpleOrWithWholeExpressionNegated() {
		String expression = "^(a+b)";

		LogicExpression result = parser.parse(expression);

		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		// Check topnode
		assertTrue(topNode.isNegation());
		assertEquals(LogicNode.Operation.OR, topNode.getOperation());

		// Has no childnodes
		assertEquals(0, topNode.getNodes().size());

		// Check variables
		List<LogicalVariable> variables = topNode.getVariables();
		assertEquals(2, variables.size());
		assertHasVariable(topNode, new LogicalVariable("a", false));
		assertHasVariable(topNode, new LogicalVariable("b", false));
	}

	@Test
	public void testSimpleAndWithMultipleVariablesAndNoVariableNegated() {
		String expression = "a*b*c*d*e";

		LogicExpression result = parser.parse(expression);

		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		// Check topnode
		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.AND, topNode.getOperation());

		// Has no childnodes
		assertEquals(0, topNode.getNodes().size());

		// Check variables
		List<LogicalVariable> variables = topNode.getVariables();
		assertEquals(5, variables.size());
		assertHasVariable(topNode, new LogicalVariable("a", false));
		assertHasVariable(topNode, new LogicalVariable("b", false));
		assertHasVariable(topNode, new LogicalVariable("c", false));
		assertHasVariable(topNode, new LogicalVariable("d", false));
		assertHasVariable(topNode, new LogicalVariable("e", false));
	}

	@Test
	public void testSimpleOrWithMultipleVariablesAndTwoVariablesNegated() {
		String expression = "a+b+^c+^d+e";

		LogicExpression result = parser.parse(expression);

		LogicNode topNode = result.getRoot();
		assertNotNull(topNode);

		// Check topnode
		assertFalse(topNode.isNegation());
		assertEquals(LogicNode.Operation.OR, topNode.getOperation());

		// Has no childnodes
		assertEquals(0, topNode.getNodes().size());

		// Check variables
		List<LogicalVariable> variables = topNode.getVariables();
		assertEquals(5, variables.size());
		assertHasVariable(topNode, new LogicalVariable("a", false));
		assertHasVariable(topNode, new LogicalVariable("b", false));
		assertHasVariable(topNode, new LogicalVariable("c", true));
		assertHasVariable(topNode, new LogicalVariable("d", true));
		assertHasVariable(topNode, new LogicalVariable("e", false));
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
