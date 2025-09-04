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

	public String view() {
		StringBuilder sb = new StringBuilder();
		sb.append("select catno, catname, h.hsncode,h.hsnname, \n");
		sb.append("(case when saletype='B' then 'Both' when saletype = 'R' then 'Rate' \n");
		sb.append("when saletype= 'W' then 'Weight' else '' end)saletypename,saletype, \n");
		sb.append("c.active, \n");
		sb.append("(case when c.active<>'Y' then 'NO' else 'YES' end) \n");
		sb.append("activestatus, o.name \n");
		sb.append("from category as c with(readpast) \n");
		sb.append("left join hsnbasics as h with(readpast) on h.hsncode=c.hsncode \n");
		sb.append("left join operator as o with(readpast) on o.OperatorCode=c.CreatedBy \n");
		sb.append("where h.active='Y'");
		return sb.toString();
	}

	public String update() {
		StringBuilder sb = new StringBuilder();
		sb.append("update c set catname=:catname , hsncode=:hsncode, \n");
		sb.append("saletype=:saletype, active=:active, ModifyBy=:modifyby, \n");
		sb.append("ModifiedDate=getdate(),ModifiedTime=getdate() from \n");
		sb.append("category c where catno = :catno");
		return sb.toString();
	}
}
