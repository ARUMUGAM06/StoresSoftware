package com.jora.billing.query;

import org.springframework.stereotype.Component;

@Component
public class HsnBasicQuery {

	public String saveHsnBasic() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				" insert into HSNBasics(hsncode,hsnname,CompanyTag,CompanyFlag,Sgst,Cgst,Igst,Active,Createdby) \n");
		builder.append(" values (:hsncode,:hsnname,:companytag,:companyflag,:sgst,:cgst,:igst,:active,:createdby) \n");
		return builder.toString();
	}

	public String chkHsnCode() {
		StringBuilder builder = new StringBuilder();
		builder.append(" select hsncode from HSNBasics where hsncode=:hsncode \n");
		return builder.toString();
	}

	public String getActiveHsnBasics() {
		StringBuilder sb = new StringBuilder();
		sb.append("select hsncode,hsnname,hsngroup,companytag,companyflag \n");
		sb.append("from HSNBasics with(nolock) \n");
		sb.append("where isnull(active,'')='Y' \n");
		return sb.toString();
	}

}
