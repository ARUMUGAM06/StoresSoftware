package com.jora.billing.common;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PasswordDocument extends DocumentFilter {

	private PasswordField passwordField;

	PasswordDocument(PasswordField textField) {
		this.passwordField = textField;
	}

	public void insertString(DocumentFilter.FilterBypass arg0, int arg1, String arg2, AttributeSet arg3)
			throws BadLocationException {
		super.insertString(arg0, arg1, arg2, arg3);
	}

	public void remove(DocumentFilter.FilterBypass arg0, int arg1, int arg2) throws BadLocationException {
		super.remove(arg0, arg1, arg2);
	}

	public void replace(DocumentFilter.FilterBypass arg0, int offs, int length, String text, AttributeSet arg4)
			throws BadLocationException {
		int offset = offs;
		String reqText = text;
		if (this.passwordField.getMaxLength() > 0)
			if (offset >= this.passwordField.getMaxLength()) {
				reqText = "";
			} else if (offset < this.passwordField.getMaxLength()) {
				int intReqChar = this.passwordField.getMaxLength() - offset;
				if (intReqChar < reqText.length())
					reqText = reqText.substring(0, intReqChar);
			}
		super.replace(arg0, offset, length, reqText, arg4);
	}
}
