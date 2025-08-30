package com.jora.encodedecode.common;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecryption {
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	private static final String KDF_ALGO = "PBKDF2WithHmacSHA256";
	private static final int KEY_SIZE_BITS = 128; 
	private static final int SALT_LENGTH = 16;
	private static final int IV_LENGTH = 16;
	private static final SecureRandom RNG = new SecureRandom();

	private static SecretKey deriveKey(byte[] salt) throws Exception {
		PBEKeySpec spec = new PBEKeySpec(CommonValues.getKeyCode().toCharArray(), salt, CommonValues.getIterations(),
				KEY_SIZE_BITS);
		SecretKeyFactory factory = SecretKeyFactory.getInstance(KDF_ALGO);
		byte[] keyBytes = factory.generateSecret(spec).getEncoded();
		return new SecretKeySpec(keyBytes, "AES");
	}

	public static String encrypt(String plainText) throws Exception {
		byte[] salt = new byte[SALT_LENGTH];
		byte[] iv = new byte[IV_LENGTH];
		RNG.nextBytes(salt);
		RNG.nextBytes(iv);
		SecretKey secretKey = deriveKey(salt);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
		byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		byte[] combined = new byte[SALT_LENGTH + IV_LENGTH + cipherText.length];
		System.arraycopy(salt, 0, combined, 0, SALT_LENGTH);
		System.arraycopy(iv, 0, combined, SALT_LENGTH, IV_LENGTH);
		System.arraycopy(cipherText, 0, combined, SALT_LENGTH + IV_LENGTH, cipherText.length);
		return Base64.getEncoder().encodeToString(combined);
	}

	public static String decrypt(String encryptedText) throws Exception {
		byte[] combined = Base64.getDecoder().decode(encryptedText);
		if (combined.length <= SALT_LENGTH + IV_LENGTH) {
			throw new IllegalArgumentException("Invalid payload: too short");
		}
		byte[] salt = Arrays.copyOfRange(combined, 0, SALT_LENGTH);
		byte[] iv = Arrays.copyOfRange(combined, SALT_LENGTH, SALT_LENGTH + IV_LENGTH);
		byte[] cipherBytes = Arrays.copyOfRange(combined, SALT_LENGTH + IV_LENGTH, combined.length);
		SecretKey secretKey = deriveKey(salt);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
		byte[] plain = cipher.doFinal(cipherBytes);
		return new String(plain, StandardCharsets.UTF_8);
	}
}
