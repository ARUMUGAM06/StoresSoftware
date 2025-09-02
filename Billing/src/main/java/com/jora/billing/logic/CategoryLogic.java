package com.jora.billing.logic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jora.billing.common.ComboBox;
import com.jora.billing.common.ListItem;
import com.jora.billing.service.CategoryService;
import com.jora.billing.service.HsnBasicService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryLogic {

	private final CategoryService categoryService;

	private final HsnBasicService hsnBasicService;

	public void loadHsnBasics(ComboBox<ListItem> cmbHsnCode) throws Exception {
		cmbHsnCode.removeAllItems();
		List<Map<String, Object>> lstHsnCode = hsnBasicService.getActiveHsnBasics();

		if (lstHsnCode.isEmpty()) {
			throw new Exception("No Active HsnBasics found!..");
		}

		for (Map<String, Object> map : lstHsnCode) {
			cmbHsnCode.addListItem(new ListItem(map.get("hsnname").toString() + " ~ " + map.get("hsncode").toString(),
					map.get("hsncode").toString()));
		}

	}

	public void loadSaletype(ComboBox<ListItem> cmbSaleType) throws Exception {
		cmbSaleType.removeAllItems();
		cmbSaleType.addListItem(new ListItem("Both", "B"));
		cmbSaleType.addListItem(new ListItem("Rate", "R"));
		cmbSaleType.addListItem(new ListItem("Weight", "W"));
	}

	public boolean saveCategory(Map<String, Object> saveMap) throws Exception {
		return categoryService.saveCategory(saveMap);
	}

}
