package com.leon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testSiemValidaiton {

	public static final String [] SIEM_CONNECTION_VARS =  { ":%b %_2d %T", 
															"applianceHostName", 
															"version",
															"applianceIP",
															"timestamp",
															"connectionID",
															"sourceIP",
															"destinationIP",
															"transportType",
															"reason",
															"spfResult"};

	public static final String [] SIEM_MESSAGE_VARS = { ":%b %_2d %T",
														"applianceHostName",
														"version",
														"applianceIP",
														"timestamp",
														"connectionID",
														"messageId",
														"sender",
														"recipient",
														"subject",
														"messageSize",
														"tsip",
														"from",
														"to",
														"cc",
														"x_mailer"};
	
	public static final String [] SIEM_POLICY_VARS  =   {":%b %_2d %T", 
														"action",
														"applianceHostName",
														"applianceIP",
														"direction",
														"exceptionReason",
														"fileResult",
														"hybridSpamScore",
														"localSpamScore",
														"messageId",
														"messageSize",
														"policyName",
														"reason",
														"recipient",
														"replyToAddress",
														"ruleName",
														"sender",
														"spamEngineName",
														"timestamp",
														"urlDetail",
														"version",
														"virusName"};
	
	public static final String [] SIEM_DELIVERY_VARS  = {":%b %_2d %T",
														 "applianceHostName",
														 "timestamp",
														 "connectionID",
														 "messageId",
														 "recipient",
														 "sourceIP",
														 "destinationIP",
														 "encryptedDelivery",
														 "deliveryCode",
														 "deliveryCodeInfo",
														 "transportType",
														 "action"};
	
	public static final String [] SIEM_HYBRID_VARS  = {":%b %_2d %T",
													   "applianceHostName",
													   "version",
													   "applianceIP",
													   "timestamp",
													   "messageId",
													   "sender",
													   "recipient",
													   "subject",
													   "messageSize",
													   "sourceIP",
													   "action",
													   "reason",
													   "spamScore"};

	public static final String [] SIEM_AUDIT_VARS  = {":%b %_2d %T",
													  "applianceHostName",
													  "version",
													  "timestamp",
													  "applianceIP",
													  "clientIP",
													  "user",
													  "role",
													  "page",
													  "element",
													  "action",
													  "details"};

	public static final String [] SIEM_COMMON_VARS = {"\\n", "\\r", "\\t", "\\0"};
	public static final String [] SIEM_MODIFIER ={"=", "|", "$" };
	
	public static final String CONNECTION_FORMAT = "ConnectionFormat";
	public static final String MESSAGE_FORMAT = "MessageFormat";
	public static final String POLICY_FORMAT = "PolicyFormat";
	public static final String DELIVERY_FORMAT = "DeliveryFormat";
	public static final String HYBRID_FORMAT = "HybridFormat";
	public static final String AUDIT_FORMAT = "AuditFormat";
	
	
	public static void main(String[] args) {
		//Pattern p=Pattern.compile("charset[\\s]*=[\\s\"']*([^\\s\"';]*)");
		//Pattern p=Pattern.compile("charset[\\s]*=[\\s\"']*([^\\s\"';]*)");
		//String format = "&lt;13&gt;%&lt;:%b %_2d %T&gt; %&lt;applianceHostName&gt; CEF:0|Forcepoint|Email Security|%&lt;version&gt;|Connection|Connection|5| dvc=%&lt;applianceIP&gt; dvchost=%&lt;=applianceHostName&gt; rt=%&lt;timestamp&gt; externalId=%&lt;connectionID&gt; src=%&lt;sourceIP&gt; dst=%&lt;destinationIP&gt;%&lt;\\n app=%&lt;transportType&gt; reason=%&lt;reason&gt; spfResult=%&lt;spfResult&gt; %&lt;\\n&gt;";
		String format = "&lt;13&gt;%&lt;:%b %_2d %T&gt; %&lt;applianceHostName&gt; CEF:0|Forcepoint|Email Security|%&lt;&gt;|Connection|Connection|5| dvc=%&lt;applianceIP&gt; dvchost=%&lt;=applianceHostName&gt; rt=%&lt;timestamp&gt; externalId=%&lt;connectionID&gt; src=%&lt;sourceIP&gt; dst=%&lt;destinationIP&gt;%&lt;\\n app=%&lt;transportType&gt; reason=%&lt;reason&gt; spfResult=%&lt;spfResult&gt; %&lt;\\n&gt;";
		String result = isValidSiemFormat(format,CONNECTION_FORMAT) ;
		System.out.println("---:"+ result );
	}

	public static String isValidSiemFormat(String format, String type) {
		Pattern p=Pattern.compile("%&lt;(.*?)&gt;"); // .*?  lazy mode.
		Matcher m = p.matcher(format);
		String [] list;
		if(type.equals(CONNECTION_FORMAT)) 
			list = SIEM_CONNECTION_VARS;
		else if(type.equals(MESSAGE_FORMAT)) 
			list = SIEM_MESSAGE_VARS;
		else if(type.equals(POLICY_FORMAT)) 
			list = SIEM_POLICY_VARS;
		else if(type.equals(DELIVERY_FORMAT)) 
			list = SIEM_DELIVERY_VARS;
		else if(type.equals(HYBRID_FORMAT)) 
			list = SIEM_HYBRID_VARS;
		else if(type.equals(AUDIT_FORMAT)) 
			list = SIEM_AUDIT_VARS;
		else 
			list = null;
		while(m.find()) {
			String var =  m.group(1);
			if(var.startsWith(":")) {
				if(!isValidTimeFormat(var))
					return var;
				else 
					continue;
			}
			if(startWithModifier(var)) {
				if(var.length()<=1) { // just a modifier 
					return var; 
				} else {
					String var1 = var.substring(1); 
					if( !isValidVariable(var1, SIEM_COMMON_VARS)  &&   !isValidVariable(var1, list)) {
						return var;
					}
				}
			} else {
				if( !isValidVariable(var, SIEM_COMMON_VARS)  &&   !isValidVariable(var, list)) {
					return var;
				}
			}
		} 
		return null;
	}
	/*
	 * check if the format is valid time format 
	 */
	public static boolean isValidTimeFormat(String format) {
		// To-do: 
		// right now, we assume that all variables which start with : are time variable and valid. 
		return true;
	}
	
	public static boolean startWithModifier(String var) {
		for(String one: SIEM_MODIFIER) {
			if(var.startsWith(one)) { 
				return true;
			}
		}
		return false;
		
	}
	public static boolean isValidVariable(String var, String [] list) {
		if(list == null || list.length < 1)
			return false;
	
		for(String one: list) {
			if(one.equals(var)) {
				return true;
			}
		}
		return false;
	}
}

