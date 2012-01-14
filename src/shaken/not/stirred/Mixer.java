package shaken.not.stirred;

public class Mixer extends Ingredient {
	private int sourness;

	public int getSourness() {
		return sourness;
	}

	public Mixer(String name, int sweetness, int herbalness, int sourness) {
		super(name, sweetness, herbalness);
		this.sourness = sourness;
	}

	public void setSourness(int sourness) {
		this.sourness = sourness;
	}
}
