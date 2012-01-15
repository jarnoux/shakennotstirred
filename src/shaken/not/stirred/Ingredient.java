package shaken.not.stirred;

import java.io.Serializable;
import java.lang.reflect.Field;

import shaken.not.stirred.R.drawable;
import android.util.Log;

public class Ingredient implements Serializable{
	private String name;
	private int sweetness;
	private int herbalness;
	private int sourness;
	private int imageID;
	
	public Ingredient(String name, int sweetness, int herbalness, int sourness, String imageID) {
		super();
		this.name = name;
		this.sweetness = sweetness;
		this.herbalness = herbalness;
		this.sourness = sourness;
		
		/*try {
		    Class res = R.drawable.class;
		    Field field = res.getField(imageID);
		    this.imageID = field.getInt(null);
		}
		catch (Exception e) {
		    Log.e("MyTag", "Failure to get drawable id.", e);
		}*/
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

	public int getImageID() {
		return imageID;
	}

	public enum GlassType {
		Highball(R.drawable.highball_glass),
		Stem(R.drawable.stem_glass),
		Rocks(R.drawable.rocks_glass),
		Cocktail(R.drawable.cocktail_glass),
		Zombie(R.drawable.zombie_glass),
		Collins(R.drawable.collins_glass),
		Coffee(R.drawable.coffee_glass),
		Shot(R.drawable.shot_glass);
		
		GlassType(int imageId){
			this.imageId = imageId;
		}
		
		int imageId;
	}
}
