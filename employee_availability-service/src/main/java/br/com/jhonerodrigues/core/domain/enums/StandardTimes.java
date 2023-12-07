package br.com.jhonerodrigues.core.domain.enums;

public enum StandardTimes {

	T09_11(0), T11_13(1), T14_16(2), T16_18(3), T18_20(4);

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
