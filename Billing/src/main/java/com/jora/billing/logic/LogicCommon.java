package com.jora.billing.logic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jora.billing.common.ComboBox;
import com.jora.billing.common.ListItem;
import com.jora.billing.service.CommonService;

@Component
public class LogicCommon {

	private final CommonService commonService;

	public LogicCommon(CommonService commonService) {
		this.commonService = commonService;

	}

	public boolean loadCategory(ComboBox<Object> cmbBox, boolean needAll) throws Exception {
		List<Map<String, Object>> lstCategory = commonService.loadCategory();

		cmbBox.removeAllItems();
		if (lstCategory.isEmpty()) {
			throw new Exception("Category Details not Found...!");
		}

		if (needAll) {
			cmbBox.addListItem(new ListItem("ALL", 0));
		}

		for (Map<String, Object> map : lstCategory) {
			cmbBox.addListItem(new ListItem(map.get("CatName"), map.get("Catno")));
		}

		cmbBox.setSelectedIndex(0);
		return true;

	}

	public boolean loadSPType(ComboBox<Object> cmbBox, boolean needAll) throws Exception {
		List<Map<String, Object>> lstCategory = commonService.loadFlagDetails("SPType");

		cmbBox.removeAllItems();
		if (lstCategory.isEmpty()) {
			throw new Exception("Sales / Purchase Type Details not Found...!");
		}

		if (needAll) {
			cmbBox.addListItem(new ListItem("ALL", 0));
		}

		for (Map<String, Object> map : lstCategory) {
			cmbBox.addListItem(new ListItem(map.get("Description"), map.get("Flag")));
		}

		cmbBox.setSelectedIndex(0);
		return true;

	}

}
