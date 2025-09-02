package com.jora.billing.common;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

public class PasswordField extends JPasswordField implements KeyListener, FocusListener {

	private static final long serialVersionUID = 1L;

	private int maxLength = 0;

	public PasswordField() {
		initialize();
	}

	public PasswordField(int arg0) {
		super(arg0);
		initialize();
	}

	public PasswordField(String arg0) {
		super(arg0);
		initialize();
	}

	public PasswordField(String arg0, int arg1) {
		super(arg0, arg1);
		initialize();
	}

	public PasswordField(Document arg0, String arg1, int arg2) {
		super(arg0, arg1, arg2);
		initialize();
	}

	private void initialize() {
		addKeyListener(this);
		addFocusListener(this);
		((AbstractDocument) getDocument()).setDocumentFilter(new PasswordDocument(this));
		setBorder(BorderFactory.createEtchedBorder());
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		if (this.maxLength > 0 && (getPassword()).length >= this.maxLength && getSelectedText() == null) {
			e.consume();
			return;
		}
	}

	public void focusGained(FocusEvent arg0) {
		setSelectionStart(0);
		setSelectionEnd((getPassword()).length);
	}

	public void focusLost(FocusEvent arg0) {
		setSelectionStart(0);
		setSelectionEnd(0);
	}
}
