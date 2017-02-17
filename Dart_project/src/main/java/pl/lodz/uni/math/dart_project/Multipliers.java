package pl.lodz.uni.math.dart_project;

public enum Multipliers {
	SINGLE(1), DOUBLE(2), TRIPLE(3);

	private int value;

	private Multipliers(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
