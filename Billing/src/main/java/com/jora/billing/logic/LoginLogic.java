package com.jora.billing.logic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jora.billing.common.ApplicationCommon;
import com.jora.billing.common.ComboBox;
import com.jora.billing.common.ListItem;
import com.jora.billing.service.LoginService;
import com.jora.encodedecode.common.EncryptionDecryption;

@Component
public class LoginLogic {

	private final com.jora.billing.service.LoginService loginService;

	public LoginLogic(LoginService loginService) {
		this.loginService = loginService;
	}

	public boolean loadOperator(ComboBox<String> cmbOperator) throws Exception {
		try {
			List<Map<String, Object>> lstOperator = loginService.getOperator();

			if (lstOperator.size() == 0 || lstOperator.isEmpty()) {
				throw new Exception("Operator Details not Found!..");
			}
			cmbOperator.removeAllItems();
			for (int i = 0; i < lstOperator.size(); i++) {
				cmbOperator.addListItem(
						new ListItem(lstOperator.get(i).get("name"), lstOperator.get(i).get("operatorcode")));
			}

			cmbOperator.setSelectedIndex(0);
			return true;

		} catch (Exception e) {
			throw e;
		}
	}

	public boolean checkOperator(int operatorCode, String passWord) throws Exception {
		try {
			Map<String, Object> mapOperator = loginService.checkOperator(operatorCode);

			if (!passWord.equals(EncryptionDecryption.decrypt(String.valueOf(mapOperator.get("password"))))) {
				return false;
//				throw new Exception("Password Mismatched...!");
			}

			ApplicationCommon.setMapOperDetails(mapOperator);

			return true;
		} catch (Exception e) {
			throw e;
		}
	}

}
