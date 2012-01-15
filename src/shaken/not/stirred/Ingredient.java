package shaken.not.stirred;

import java.io.Serializable;

public class Ingredient implements Serializable{
	private String name;
	private int sweetness;
	private int herbalness;
	private int sourness;
	
	public Ingredient(String name, int sweetness, int herbalness, int sourness) {
		super();
		this.name = name;
		this.sweetness = sweetness;
		this.herbalness = herbalness;
		this.sourness = sourness;
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
	public int getSourness() {
		return sourness;
	}
	public void setSourness(int sourness) {
		this.sourness = sourness;
	}
	

}
