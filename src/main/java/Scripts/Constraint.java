//package Scripts;
//
//import BusinessRules.AttributeRules;
//
//public class Constraint extends AttributeRules {
//	private String expression;
//
//	public Constraint() {
//		super();
//	}
//
//	public String getExpression() {
//		return expression;
//	}
//
//	public void setExpression(String expression) {
//		this.expression = expression;
//	}
//
//	@Override
//	public String generateScript(String triggerName, String operator, String exception) {
//		String constraint = "ALTER TABLE "+tables.get(0)+" ADD CONSTRAINT "+triggerName+" CHECK "+ "("+expression+")"+";";
//		return constraint;
//	}
//
//}
