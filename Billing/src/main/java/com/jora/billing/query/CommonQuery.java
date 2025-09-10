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

	public String loadFlagDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append("select Flag,Description from FlagDetails where Active='Y' and  Type=? order by Description \n");
		return sb.toString();
	}

	public String loadCatgeoryHsnDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append("select H.hsncode,H.Cgst,H.Sgst,H.Igst,C.SaleType from Category as C  \n");
		sb.append("inner join HSNBasics as H on H.hsncode=C.Hsncode \n");
		sb.append("where C.Active='Y' and H.Active='Y' and C.Catno=? \n");
		return sb.toString();
	}

}
