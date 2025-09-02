package com.jora.billing.query;

import org.springframework.stereotype.Component;

@Component
public class CommonQuery {

	public String getMaxNo() {
		StringBuilder sb = new StringBuilder();
		sb.append("update h set number=number+1  \n");
		sb.append("OUTPUT inserted.number AS maxnumber \n");
		sb.append("from DirectoryNumGenerate h \n");
		sb.append("where companytag=:companytag and companyflag=:companyflag and tables=:table \n");
		return sb.toString();
	}

}
