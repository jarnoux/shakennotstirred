package shaken.not.stirred;

import java.util.HashMap;

import android.widget.ImageView;

public class Cocktail {

	// Key is ingredient, value is quantity in an integer ratio
	private HashMap<String, Integer> ingredients;
	private final String name;
	private final boolean isCustom;
	private ImageView image;

	public Cocktail(String name, HashMap<String, Integer> ingredients, boolean isCustom, ImageView image) {
		this.name = name;
		this.setIngredients(ingredients);
		this.isCustom = isCustom;
		this.setImage(image);
		
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

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}
}
