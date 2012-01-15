package shaken.not.stirred;

public class Alcohol extends Ingredient {

	private int alcoholPercentage;

	public Alcohol(String name, int sweetness, int herbalness,
			int alcoholPercentage) {
		super(name, sweetness, herbalness, 0);
		this.alcoholPercentage = alcoholPercentage;
	}

	public int getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(int alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

}
