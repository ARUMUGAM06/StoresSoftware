package com.jora.billing.logic;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.jora.billing.service.HsnBasicService;

@Component
public class LogicHsnBasic {

	private final HsnBasicService hsnBasicService;

	public LogicHsnBasic(HsnBasicService hsnBasicService) {
		this.hsnBasicService = hsnBasicService;
	}

	public boolean saveHsnBasic(Map<String, Object> mapHsn) throws Exception {
		String chkHsnCode = hsnBasicService.chkHsnCode(mapHsn);

		if (!chkHsnCode.equalsIgnoreCase("")) {
			throw new Exception("This HSNCode " + String.valueOf(mapHsn.get("hsncode")) + " Already Saved...!");
		}

		return hsnBasicService.saveHsnBasic(mapHsn);
	}

}
