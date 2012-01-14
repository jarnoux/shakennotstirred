package shaken.not.stirred;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cocktail implements Serializable{

	// Key is ingredient, value is quantity in an integer ratio
	private Map<String, Integer> ingredients;
	private final String name;
	private final boolean isCustom;
	private String imageId;

	public Cocktail(String name, Map<String, Integer> ingredients, boolean isCustom, String imageId) {
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

	public Map<String, Integer> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<String, Integer> ingredients) {
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
		Highball(0),
		Stem(0),
		Rocks(0),
		Cocktail(0),
		Zombie(0),
		Collins(0),
		Coffee(0),
		Shot(0);
		
		GlassType(int imageId){
			this.imageId = imageId;
		}
		
		int imageId;
	}
}
