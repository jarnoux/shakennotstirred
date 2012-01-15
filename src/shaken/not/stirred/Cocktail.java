package shaken.not.stirred;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cocktail implements Serializable{

	// Key is ingredient, value is quantity in an integer ratio
	private Map<String, Integer> ingredients;
	private final String name;
	private final boolean isCustom;
	private int imageId;

	public Cocktail(String name, Map<String, Integer> ingredients, boolean isCustom, int imageId) {
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

	public int getImageId() {
		return imageId;
	}

	public void setImage(int imageId) {
		this.imageId = imageId;
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
