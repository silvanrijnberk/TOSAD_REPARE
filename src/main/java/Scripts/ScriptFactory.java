package Scripts;

import BusinessRules.*;

public class ScriptFactory {
	ARNGScript ArngScript = new ARNGScript();
	ACMPScript AcmpScript = new ACMPScript();
	ALISScript AlisScript = new ALISScript();
	ICMPScript IcmpScript = new ICMPScript();
	AOTHScript AothScript = new AOTHScript();
	EOTHScript EothScript = new EOTHScript();
	MODIScript ModiScript = new MODIScript();
	TCMPScript TcmpScript = new TCMPScript();
	TOTHScript TothScript = new TOTHScript();

	public String createScript(BusinessRule BRule) {
		//ARNG
		if (BRule.getRuleType().equalsIgnoreCase("ARNG")) {
			Attributerangerule rule = (Attributerangerule) BRule;
			return ArngScript.generateScript(rule);
		}

		//ACMP
		if (BRule.getRuleType().equalsIgnoreCase("ACMP")) {
			Attributecomparerule rule = (Attributecomparerule) BRule;
			return AcmpScript.generateScript(rule);
		}

		//ALIS
		if (BRule.getRuleType().equalsIgnoreCase("ALIS")) {
			Attributelistrule rule = (Attributelistrule) BRule;
			return AlisScript.generateScript(rule);
		}

		//ICMP--
		if (BRule.getRuleType().equalsIgnoreCase("ICMP")) {
			Interentitycomparerule rule = (Interentitycomparerule) BRule;
			return IcmpScript.generateScript(rule);
		}

		//AOTH
		if (BRule.getRuleType().equalsIgnoreCase("AOTH")) {
			Attributeotherrule rule = (Attributeotherrule) BRule;
			return AothScript.generateScript(rule);
		}

		//EOTH
		if (BRule.getRuleType().equalsIgnoreCase("EOTH")) {
			Entityotherrule rule = (Entityotherrule) BRule;
			return EothScript.generateScript(rule);
		}

		//MODI
		if (BRule.getRuleType().equalsIgnoreCase("MODI")) {
			Modifyrule rule = (Modifyrule) BRule;
			return ModiScript.generateScript(rule);
		}

		//TCMP
		if (BRule.getRuleType().equalsIgnoreCase("TCMP")) {
			Tuplecomparerule rule = (Tuplecomparerule) BRule;
			return TcmpScript.generateScript(rule);
		}

		//TOTH
		if (BRule.getRuleType().equalsIgnoreCase("TOTH")) {
			Tupleotherrule rule = (Tupleotherrule) BRule;
			return TothScript.generateScript(rule);
		}
		return "ongeldige rule";
	}
}
