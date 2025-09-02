package com.jora.billing.common;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

public class TextField extends JTextField implements KeyListener, FocusListener {
	private static final long serialVersionUID = 1L;

	private int maxLength = 0;

	private int precision = 0;

	private int intMaxLength = 0;

	private int afterDecimal = 0;

	private JTextFieldEnum.TextInputType textInputType = JTextFieldEnum.TextInputType.SYSTEM;

	private JTextFieldEnum.TextInputCase textInputCase = JTextFieldEnum.TextInputCase.SYSTEM;

	private JTextFieldEnum.TextSpaceReq textSpaceReq = JTextFieldEnum.TextSpaceReq.REQUIRED;

	private JTextFieldEnum.NumericDigits numericDigits = JTextFieldEnum.NumericDigits.NONE;

	private boolean selectionOnFoucs = true;

	private DecimalFormat format = new DecimalFormat();

	private Color focusColor = Color.decode("#D6D6D6");

	private Color lostFocusColor;

	private boolean assigned = false;

	public TextField() {
			initialize();
		}

	public TextField(int arg0) {
			super(arg0);
			initialize();
		}

	public TextField(String arg0) {
			super(arg0);
			initialize();
		}

	public TextField(String arg0, int arg1) {
			super(arg0, arg1);
			initialize();
		}

	public TextField(Document arg0, String arg1, int arg2) {
			super(arg0, arg1, arg2);
			initialize();
		}

	private void initialize() {
		addKeyListener(this);
		addFocusListener(this);
		((AbstractDocument) getDocument()).setDocumentFilter(new JDocumentFilter(this));
		setBorder(BorderFactory.createEtchedBorder());
		this.lostFocusColor = getBackground();
	}

	public void setNumericLength(int maxLength, int precision) {
		this.maxLength = maxLength;
		this.precision = precision;
		if (getNumericDigits() == null || getNumericDigits() == JTextFieldEnum.NumericDigits.NONE)
			if (precision > 0) {
				JTextFieldEnum.NumericDigits digits = JTextFieldEnum.NumericDigits.MAXNUMBER;
				digits.setMaxLength(precision);
				setNumericDigits(digits);
			} else {
				setNumericDigits(JTextFieldEnum.NumericDigits.TWO);
			}
	}

	public void setMaxLength(int mMaxLength) {
		if (this.textInputType != null)
			switch (this.textInputType) {
			case NUMBER:
				this.intMaxLength = 9;
				break;
			}
		this.intMaxLength = mMaxLength;
	}

	public int getMaxLength() {
		return this.intMaxLength;
	}

	public void setNumericDigits(JTextFieldEnum.NumericDigits numericDigits) {
		this.numericDigits = numericDigits;
		this.afterDecimal = numericDigits.getMaxLength();
	}

	public JTextFieldEnum.NumericDigits getNumericDigits() {
		return this.numericDigits;
	}

	public void setTextCase(JTextFieldEnum.TextInputCase textInputCase) {
		this.textInputCase = textInputCase;
	}

	public void setTextType(JTextFieldEnum.TextInputType textInputType) {
		this.textInputType = textInputType;
	}

	public JTextFieldEnum.TextInputCase getTextCase() {
		return this.textInputCase;
	}

	public JTextFieldEnum.TextInputType getTextType() {
		return this.textInputType;
	}

	public void setTextSpaceReq(JTextFieldEnum.TextSpaceReq textSpaceReq) {
		this.textSpaceReq = textSpaceReq;
	}

	public JTextFieldEnum.TextSpaceReq getTextSpaceReq() {
		return this.textSpaceReq;
	}

	public Color getFocusColor() {
		return this.focusColor;
	}

	public void setFocusColor(Color focusColor) {
		this.focusColor = focusColor;
	}

	protected boolean isAssigned() {
		return this.assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public boolean isSelectionOnFoucs() {
		return this.selectionOnFoucs;
	}

	public void setSelectionOnFoucs(boolean selectionOnFoucs) {
		this.selectionOnFoucs = selectionOnFoucs;
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
		boolean blnKeyDot;
		boolean isNotNumber;
		if (this.intMaxLength > 0 && getText().length() >= this.intMaxLength && getSelectedText() == null
				&& this.maxLength + this.precision > 0 && getText().length() > this.maxLength + this.precision) {
			arg0.consume();
			return;
		}
		if (arg0.getKeyChar() == ' ' && isSapceReq(arg0.getKeyChar()))
			return;
		switch (this.textInputType) {
		case ALPHA:
			if (!isLowerCaseChar(arg0.getKeyChar()) && !isUpperCaseChar(arg0.getKeyChar()))
				arg0.consume();
			break;
		case ALPHANUMERIC:
			if (!isLowerCaseChar(arg0.getKeyChar()) && !isUpperCaseChar(arg0.getKeyChar())
					&& !isNumbers(arg0.getKeyChar()) && arg0.getKeyChar() != '.')
				arg0.consume();
			break;
		case ALPHANUMBER:
			if (!isLowerCaseChar(arg0.getKeyChar()) && !isUpperCaseChar(arg0.getKeyChar())
					&& !isNumbers(arg0.getKeyChar()))
				arg0.consume();
			break;
		case NUMBER:
			if (!isNumbers(arg0.getKeyChar())) {
				arg0.consume();
				return;
			}
			break;
		case NUMERIC:
		case NUMERIC_SPECIAL:
			if (arg0.getKeyChar() == '.' && getText().length() >= this.intMaxLength && getSelectedText() != null
					&& getSelectedText().length() > 0)
				return;
			blnKeyDot = checkAfterDeciaml(arg0.getKeyChar());
			if (!blnKeyDot) {
				arg0.consume();
				return;
			}
			isNotNumber = (!isNumbers(arg0.getKeyChar()) && arg0.getKeyChar() != '.');
			if (this.textInputType == JTextFieldEnum.TextInputType.NUMERIC_SPECIAL && isNotNumber)
				isNotNumber = (arg0.getKeyChar() != '-');
			if (isNotNumber) {
				arg0.consume();
				return;
			}
			if (this.maxLength > 0 && getText().length() >= this.maxLength + 1 && arg0.getKeyChar() != '.'
					&& getSelectedText() == null) {
				arg0.consume();
				return;
			}
			if (this.maxLength > 0 && getText().length() >= this.maxLength - this.precision) {
				int decimalIndex = getText().lastIndexOf(".");
				String beforeDecimalText = getText().substring(decimalIndex + 1);
				if (beforeDecimalText.length() >= this.maxLength - this.precision && getSelectedText() == null) {
					if (arg0.getKeyChar() == '.')
						return;
					arg0.consume();
					return;
				}
			}
			break;
		case SYSTEM:
			if (!isSapceReq(arg0.getKeyChar())) {
				arg0.consume();
				return;
			}
			break;
		}
		if (this.textInputCase == JTextFieldEnum.TextInputCase.LOWER) {
			String strC = String.valueOf(arg0.getKeyChar());
			strC = strC.toLowerCase();
			char c = strC.charAt(0);
			arg0.setKeyChar(c);
		} else if (this.textInputCase == JTextFieldEnum.TextInputCase.UPPER) {
			String strC = String.valueOf(arg0.getKeyChar());
			strC = strC.toUpperCase();
			char c = strC.charAt(0);
			arg0.setKeyChar(c);
		}
	}

	private boolean checkAfterDeciaml(char value) {
		int currentPosition = getCaretPosition();
		int decimalIndex = getText().lastIndexOf(".");
		if (decimalIndex > -1 && value == '.')
			return false;
		if (this.afterDecimal == 0)
			return true;
		if (decimalIndex < 0)
			return true;
		if (currentPosition <= decimalIndex)
			return true;
		String afterDecimalText = getText().substring(decimalIndex + 1);
		if (afterDecimalText.length() >= this.afterDecimal && getSelectedText() == null)
			return false;
		return true;
	}

	private boolean isNumbers(char value) {
		return (value >= '0' && value <= '9');
	}

	private boolean isLowerCaseChar(char value) {
		return (value >= 'a' && value <= 'z');
	}

	private boolean isUpperCaseChar(char value) {
		return (value >= 'A' && value <= 'Z');
	}

	private boolean isSapceReq(char value) {
		boolean status = false;
		if (value == ' ') {
			if (this.textSpaceReq == JTextFieldEnum.TextSpaceReq.REQUIRED)
				status = true;
		} else {
			status = true;
		}
		return status;
	}

	public void focusGained(FocusEvent arg0) {
		if (this.selectionOnFoucs) {
			setSelectionStart(0);
			setSelectionEnd(getText().length());
		} else {
			setCaretPosition(getText().length());
		}
		this.lostFocusColor = getBackground();
		setBackground(this.focusColor);
	}

	public void focusLost(FocusEvent arg0) {
		try {
			if (this.textInputType == JTextFieldEnum.TextInputType.NUMERIC) {
				String strValue = getText().trim();
				if (strValue.isEmpty())
					strValue = "0";
				if (this.afterDecimal == 0) {
					if (strValue.contains(".")) {
						String[] numericValue = strValue.split("\\.");
						if (numericValue.length > 1) {
							if (Double.parseDouble(String.valueOf(numericValue[1])) == 0.0D) {
								this.format.applyLocalizedPattern("#0");
								setText(this.format.format(Double.parseDouble(strValue)));
							}
							return;
						}
					}
					this.format.applyLocalizedPattern("#0");
					setText(this.format.format(Double.parseDouble(strValue)));
				} else {
					this.format.applyLocalizedPattern("#0.".concat("0".repeat(this.afterDecimal)));
					setText(this.format.format(Double.parseDouble(strValue)));
				}
			} else if (this.textInputType == JTextFieldEnum.TextInputType.NUMERIC_SPECIAL) {
				String strValue = getText().trim();
				if (strValue.isEmpty())
					strValue = "0";
				if (this.afterDecimal == 0) {
					if (strValue.contains(".")) {
						setFormattedText(strValue, "\\.");
					} else if (strValue.contains("-")) {
						setFormattedText(strValue, "\\-");
					} else {
						this.format.applyLocalizedPattern("#0");
						setText(this.format.format(Double.parseDouble(strValue)));
					}
				} else if (strValue.contains("-")) {
					setFormattedText(strValue, "\\-");
				} else {
					this.format.applyLocalizedPattern("#0.".concat("0".repeat(this.afterDecimal)));
					setText(this.format.format(Double.parseDouble(strValue)));
				}
			}
			setSelectionStart(0);
			setSelectionEnd(0);
			setBackground(this.lostFocusColor);
		} catch (NumberFormatException numberFormatException) {
		}
	}

	private void setFormattedText(String strValue, String seperator) {
		try {
			if (strValue == null || strValue.trim().isEmpty())
				return;
			String[] numericValue = strValue.split(seperator);
			if (numericValue.length > 1) {
				if (numericValue[1].contains("-"))
					return;
				if (Double.parseDouble(String.valueOf(numericValue[1])) == 0.0D) {
					this.format.applyLocalizedPattern("#0");
					setText(this.format.format(Double.parseDouble(strValue)));
				}
			}
		} catch (NumberFormatException numberFormatException) {
		}
	}
}
