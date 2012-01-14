package shaken.not.stirred;

import java.io.Serializable;

public class Ingredient implements Serializable{
	private String name;
	private int sweetness;
	private int herbalness;
	
	public Ingredient(String name, int sweetness, int herbalness) {
		super();
		this.name = name;
		this.sweetness = sweetness;
		this.herbalness = herbalness;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSweetness() {
		return sweetness;
	}
	public void setSweetness(int sweetness) {
		this.sweetness = sweetness;
	}
	public int getHerbalness() {
		return herbalness;
	}
	public void setHerbalness(int herbalness) {
		this.herbalness = herbalness;
	}
	

}
