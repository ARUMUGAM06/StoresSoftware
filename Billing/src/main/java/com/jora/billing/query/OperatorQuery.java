package com.jora.billing.query;

import org.springframework.stereotype.Component;

@Component
public class OperatorQuery {

	public String getOperatorDetails() {
		StringBuilder builder = new StringBuilder();
		builder.append("select * from operator where active='Y' order by name;");
		return builder.toString();
	}

	public String checkOperator() {
		StringBuilder builder = new StringBuilder();
		builder.append("select * from operator where active='Y' and operatorcode=? ;");
		return builder.toString();
	}

}
