package be.ohlson.sequenceplanner;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class LogicalEvaluatorTest {	
		
	private LogicalEvaluator evaluator;
	
	@Before
	public void setup() {
		evaluator = new LogicalEvaluatorImpl();
	}
	
	@Test
	public void testSimpleExpressionStaysTheSame() {
		Set<Entry<String, String>> simpleExpressions = getSimpleExpressions();
		
		for (Entry<String, String> key : simpleExpressions) {
			
			String result = evaluator.simplifyExpression(key.getKey());
			assertEquals(key.getValue(), result);
		}
	}
	
	
	private Set<Entry<String, String>> getSimpleExpressions() {
		HashMap<String, String> simpleExpressions = new HashMap<String, String>();
		
		simpleExpressions.put("(a+b)*(d+e)", "(a+b)*(d+e)");
		simpleExpressions.put("(a+b)*(d+e)", "(a+b)*(d+e)");
		
		return simpleExpressions.entrySet();
	}
	

}
