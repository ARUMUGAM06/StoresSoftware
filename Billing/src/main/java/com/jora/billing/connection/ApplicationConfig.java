package com.jora.billing.connection;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.jora.encodedecode.common.EncryptionDecryption;

public class ApplicationConfig {
	public static String serverName, companyTag, portNo, userName, password, companyFlag;
	public static int dbYear;

	public static void fileRead() throws Exception {
		File file = new File(System.getProperty("user.dir") + "\\Appconfig.ini");
		if (!file.exists()) {

			throw new Exception("Appconfig.ini File not found!...");
		}

		List<String> lstServerDetails = new ArrayList<String>();
		lstServerDetails = Files.readAllLines(file.toPath());
		int lineCount = 1;
		for (String s : lstServerDetails) {

			switch (lineCount) {
			case 1:
				companyTag = EncryptionDecryption.decrypt(s.trim());
				break;
			case 2:
				serverName = EncryptionDecryption.decrypt(s.trim());
				break;
			case 3:
				portNo = EncryptionDecryption.decrypt(s.trim());
				break;
			case 4:
				userName = EncryptionDecryption.decrypt(s.trim());
				break;
			case 5:
				password = EncryptionDecryption.decrypt(s.trim());
				break;
			default:
				break;
			}

			lineCount += 1;
		}

	}

}
