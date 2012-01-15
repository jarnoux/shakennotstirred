package shaken.not.stirred;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

public class Cocktail implements Serializable{

	// Key is ingredient, value is quantity in an integer ratio
	private Map<String, Integer> ingredients;
	private final String name;
	private final boolean isCustom;
	private int imageId;
	private int numIngredients;

	public Cocktail(String name, Map<String, Integer> ingredients, boolean isCustom, int imageId) {
		this.name = name;
		this.setIngredients(ingredients);
		this.isCustom = isCustom;
		this.setImage(imageId);
		numIngredients = ingredients.size();
		
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
	
	public float getSweetness(Activity a) {
		int totalSweetness = 0;
		int totalParts = 0;
		
		for(String s: ingredients.keySet()) {
			int part = ingredients.get(s);
			if(part >0) {
			totalSweetness += (DataStore.getInstance(a).getIngredients().get(s).getSweetness()*part);
			totalParts += part;
			}
		}
		
		return ((float)totalSweetness/(float) totalParts);
	}
	
	public float getSourness(Activity a) {
		int totalSourness = 0;
		int totalParts = 0;
		
		for(String s: ingredients.keySet()) {
			int part = ingredients.get(s);
			if(part >0) {
			totalSourness += (DataStore.getInstance(a).getIngredients().get(s).getSourness()*part);
			totalParts += part;
			}
		}
		return ((float)totalSourness/(float) totalParts);
	}
	
	public float getHerbalness(Activity a) {
		int totalHerbalness = 0;
		int totalParts = 0;
		
		for(String s: ingredients.keySet()) {
			int part = ingredients.get(s);
			if(part >0) {
			totalHerbalness += (DataStore.getInstance(a).getIngredients().get(s).getHerbalness()*part);
			totalParts += part;
			}
		}
		return ((float)totalHerbalness/(float) totalParts);
	}
	
	public boolean updatePart(String ingredient, int part) {
		if(ingredients.get(ingredient) != null) {
			ingredients.put(ingredient, part);
			return true;
		}
		return false;

	}
}
