package com.jora.billing.query;

import org.springframework.stereotype.Component;

@Component
public class CategoryQuery {

	public String save() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO category (catno, catname, hsncode, saletype, active, createdby) ");
		sb.append("values (:catno, :catname,:hsncode,:saletype,:active,:createdby) ");
		return sb.toString();
	}

}
