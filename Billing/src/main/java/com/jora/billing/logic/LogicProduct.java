package com.jora.billing.logic;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.jora.billing.common.ComboBox;
import com.jora.billing.common.ListItem;
import com.jora.billing.common.TextField;
import com.jora.billing.service.CommonService;
import com.jora.billing.service.ProductService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LogicProduct {
	private final CommonService commonService;
	private final ProductService productService;

	public boolean enterFromCatDesc(TextField txtHsnCode, ComboBox<Object> cmbSaleType, TextField txtSgst,
			TextField txtCgst, TextField txtIgst, int catNo) throws Exception {

		Map<String, Object> hsnDetails = commonService.loadCategoryHsnDetails(catNo);

		if (hsnDetails.isEmpty()) {
			throw new Exception("HSN Details Not Found for this Category...!");
		}

		txtHsnCode.setText(String.valueOf(hsnDetails.get("hsncode")));
		txtCgst.setText(String.valueOf(hsnDetails.get("Cgst")));
		txtSgst.setText(String.valueOf(hsnDetails.get("Sgst")));
		txtIgst.setText(String.valueOf(hsnDetails.get("Igst")));
		cmbSaleType.setSelectedItemValue(hsnDetails.get("SaleType"));

		return true;

	}

	public boolean loadTaxBasedOn(ComboBox<Object> cmbBox) {
		cmbBox.removeAllItems();

		cmbBox.addListItem(new ListItem("Category", "C"));
		cmbBox.addListItem(new ListItem("Product", "P"));
		cmbBox.setSelectedIndex(0);

		return true;

	}

	public String saveProduct(Map<String, Object> mapProduct) throws Exception {

		return productService.saveProduct(mapProduct);
	}

}
