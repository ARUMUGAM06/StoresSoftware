package com.jora.encodedecode.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jora.encodedecode.common.EncryptionDecryption;

@RestController
@RequestMapping("/")
public class EncryptDecryptController {
	
	@PostMapping("encrypt")
	public ResponseEntity<String> encrypt(@RequestBody String plainText) {
		try {
			return ResponseEntity.ok(EncryptionDecryption.encrypt(plainText));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@PostMapping("decrypt")
	public ResponseEntity<String> decrypt(@RequestBody String encodedText) {
		try {
			return ResponseEntity.ok(EncryptionDecryption.decrypt(encodedText));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}
