package shaken.not.stirred;

public class Mixer extends Ingredient {
	private int sourness;

	public int getSourness() {
		return sourness;
	}

	public Mixer(String name, int sweetness, int herbalness, int sourness, String imageID) {
		super(name, sweetness, herbalness, sourness, imageID);
	}
}
