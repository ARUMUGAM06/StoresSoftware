package com.jora.billing.common;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class ComboBoxSearch<E> extends PlainDocument {

	private static final long serialVersionUID = 1L;

	private JComboBox<E> thisCombo;

	private transient ComboBoxModel<E> thisModel;

	private JTextComponent txtEditor;

	private boolean selecting = false;

	public ComboBoxSearch(JComboBox<E> comboBox) {
		this.thisCombo = comboBox;
		this.thisModel = this.thisCombo.getModel();
		this.txtEditor = (JTextComponent) this.thisCombo.getEditor().getEditorComponent();
		comboBox.addActionListener(a -> {
			if (!this.selecting)
				highlightCompletedText(0);
		});
		this.txtEditor.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (ComboBoxSearch.this.thisCombo.isDisplayable())
					ComboBoxSearch.this.thisCombo.setPopupVisible(true);
				if (e.getKeyCode() == 27)
					ComboBoxSearch.this.thisCombo.setPopupVisible(false);
			}
		});
		this.txtEditor.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (ComboBoxSearch.this.thisCombo.isDisplayable())
					ComboBoxSearch.this.thisCombo.setPopupVisible(true);
			}

			public void focusLost(FocusEvent e) {
				ComboBoxSearch.this.thisCombo.setPopupVisible(false);
			}
		});
		Object selected = this.thisCombo.getSelectedItem();
		if (selected != null)
			setText(selected.toString());
		highlightCompletedText(0);
	}

	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		int offset = offs;
		if (this.selecting)
			return;
		super.insertString(offset, str, a);
		Object item = lookupItem(getText(0, getLength()));
		if (item != null) {
			setSelectedItem(item);
		} else {
			if (this.thisCombo.getItemCount() > 0)
				item = this.thisCombo.getSelectedItem();
			offset -= str.length();
			this.thisCombo.getToolkit().beep();
		}
		if (item != null && !"".equals(item))
			setText(item.toString());
		if (this.thisCombo.getItemCount() > 0)
			highlightCompletedText(offset + str.length());
	}

	private void setSelectedItem(Object item) {
		this.selecting = true;
		this.thisModel.setSelectedItem(item);
		this.selecting = false;
	}

	private void setText(String text) {
		try {
			remove(0, getLength());
			super.insertString(0, text, (AttributeSet) null);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	private void highlightCompletedText(int start) {
		if (this.txtEditor.getText().trim().length() <= 0) {
			this.thisModel = this.thisCombo.getModel();
			this.txtEditor = (JTextComponent) this.thisCombo.getEditor().getEditorComponent();
			Object object = this.thisCombo.getSelectedItem();
			if (object != null)
				setText(object.toString());
		}
		Object selected = this.thisCombo.getSelectedItem();
		String text = "";
		if (selected != null) {
			setText(selected.toString());
			text = selected.toString();
		}
		if (text.length() > 0) {
			this.txtEditor.setCaretPosition(text.length());
			this.txtEditor.moveCaretPosition(start);
		}
	}

	private Object lookupItem(String pattern) {
		Object selectedItem = this.thisModel.getSelectedItem();
		if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern))
			return selectedItem;
		for (int i = 0, n = this.thisModel.getSize(); i < n; i++) {
			Object currentItem = this.thisModel.getElementAt(i);
			if (startsWithIgnoreCase(currentItem.toString(), pattern))
				return currentItem;
		}
		return pattern;
	}

	private boolean startsWithIgnoreCase(String str1, String str2) {
		return str1.toUpperCase().startsWith(str2.toUpperCase());
	}
}
