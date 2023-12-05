package br.com.jhonerodrigues.core.domain.enums;

public enum StandardTimes {

	T09_11(1), T11_13(2), T14_16(3), T16_18(4), T18_20(5);

	private int code;

	private StandardTimes  (int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static StandardTimes valueOf(int code) {
		for (StandardTimes value : StandardTimes.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}

}
