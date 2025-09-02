package com.jora.billing.common;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class JDocumentFilter extends DocumentFilter {

	private TextField jtextField;

	JDocumentFilter(TextField textField) {
		this.jtextField = textField;
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
		int position = offs;
		String inputText = text;
		if (this.jtextField.getMaxLength() > 0)
			if (this.jtextField.isAssigned()) {
				if (this.jtextField.getMaxLength() < inputText.length())
					inputText = inputText.substring(0, this.jtextField.getMaxLength());
				this.jtextField.setAssigned(false);
			} else if (position == 0 && length == 0) {
				if (this.jtextField.getText().length() >= this.jtextField.getMaxLength()) {
					inputText = "";
				} else {
					int requiredLength = this.jtextField.getMaxLength() - this.jtextField.getText().length();
					if (requiredLength < inputText.length())
						inputText = inputText.substring(0, requiredLength);
				}
			} else if (position == 0 && length > 0) {
				int requiredLength = this.jtextField.getMaxLength() - this.jtextField.getText().length() - length;
				if (requiredLength < inputText.length())
					inputText = inputText.substring(0, requiredLength);
			} else if (position > 0 && length == 0) {
				if (this.jtextField.getText().length() >= this.jtextField.getMaxLength()) {
					inputText = "";
				} else {
					int requiredLength = this.jtextField.getMaxLength() - this.jtextField.getText().length();
					if (requiredLength < inputText.length())
						inputText = inputText.substring(0, requiredLength);
				}
			} else if (position > 0 && length > 0) {
				int requiredLength = this.jtextField.getMaxLength() - this.jtextField.getText().length() - length;
				if (requiredLength < inputText.length())
					inputText = inputText.substring(0, requiredLength);
			}
		if (this.jtextField.getTextCase() == JTextFieldEnum.TextInputCase.LOWER) {
			inputText = inputText.toLowerCase();
		} else if (this.jtextField.getTextCase() == JTextFieldEnum.TextInputCase.UPPER) {
			inputText = inputText.toUpperCase();
		}
		super.replace(arg0, position, length, inputText, arg4);
	}
}
