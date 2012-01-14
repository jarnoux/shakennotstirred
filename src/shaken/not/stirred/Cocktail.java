package shaken.not.stirred;

import java.io.Serializable;
import java.util.HashMap;

import android.widget.ImageView;
import android.widget.TextView;

public class Cocktail implements Serializable{

	// Key is ingredient, value is quantity in an integer ratio
	private HashMap<String, Integer> ingredients;
	private final String name;
	private final boolean isCustom;
	private String imageId;

	public Cocktail(String name, HashMap<String, Integer> ingredients, boolean isCustom, String imageId) {
		this.name = name;
		this.setIngredients(ingredients);
		this.isCustom = isCustom;
		this.setImage(imageId);
		
	}
	
	public void addIngredient(String ingredient, int parts){
		this.ingredients.put(ingredient, parts);
	}
	

	public String getName() {
		return name;
	}

	public HashMap<String, Integer> getIngredients() {
		return ingredients;
	}

	public void setIngredients(HashMap<String, Integer> ingredients) {
		this.ingredients = ingredients;
	}

	public boolean isCustom() {
		return isCustom;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImage(String imageId) {
		this.imageId = imageId;
	}
	
	public enum GlassType {
		Highball,
		Stem,
		Rocks,
		Cocktail,
		Zombie,
		Collins,
		Coffee,
		Shot
	}
}
