package com.jora.billing.common;

public class JTextFieldEnum {
	public enum TextInputCase {
		SYSTEM, LOWER, UPPER;
	}

	public enum TextInputType {
		SYSTEM, ALPHA, ALPHANUMERIC, ALPHANUMBER, NUMBER, NUMERIC, NUMERIC_SPECIAL;
	}

	public enum TextSpaceReq {
		REQUIRED, NOTREQUIRED;
	}

	public enum NumericDigits {
		MAXNUMBER, NONE(0), ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);

		private int maxLength = 5;

		NumericDigits(int maxLength) {
			this.maxLength = maxLength;
		}

		NumericDigits() {

		}

		public int getMaxLength() {
			return this.maxLength;
		}

		public void setMaxLength(int maxLength) {
			this.maxLength = maxLength;
		}
	}
}
