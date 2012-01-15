package shaken.not.stirred;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import android.app.Activity;

public class DataStore{

	
	private Map<String, Ingredient> ingredients = new HashMap<String, Ingredient>();

	private Map<String, Cocktail> recipes = new HashMap<String, Cocktail>();
	private Map<String, List<String>> components = new HashMap<String, List<String>>();
	private static final String ingredientsFile = "ingredients.csv";
	private static final String recipesFile = "recipes.csv";
	
	private static DataStore singleton;
	private Activity act;
	
	public DataStore(Activity act) {
		this.act = act;
		readIngredients(ingredientsFile);
		readRecipes(recipesFile);
	}
	
	/**
	 * 
	 * @param context used to get the resources files. Used only the first time
	 * this is called by the app.
	 * @return
	 */
	public static DataStore getInstance(Activity context){
		if(singleton == null){
			singleton = new DataStore(context);
		}
		return singleton;
	}
	
	private void readRecipes(String recipesFile) {
		
		try {
			Scanner in = new Scanner(new BufferedInputStream(this.act.getResources().openRawResource(R.raw.recipes)));
			// since it's a comma-separated file
			in.useDelimiter(Pattern.compile("[,\n\r]"));
			// skip the first header line
			in.nextLine();
			
			// read each line of the file one at a time
			String name;
			boolean isDrink;
			int parts = -1;
			Cocktail.GlassType glass;
			Cocktail crtCocktail = null;
			while (in.hasNext()) {
				name = in.next().toLowerCase();
				isDrink = false;
				if(in.nextInt() == 1){
					isDrink = true;
				}
				try{
					parts = in.nextInt();
				} catch (InputMismatchException e){
					in.next();
				}
				glass = Cocktail.GlassType.valueOf(in.next());
				
				if(isDrink){
					if(crtCocktail != null){
						recipes.put(crtCocktail.getName(), crtCocktail);
					}
					crtCocktail = new Cocktail(name, new HashMap<String, Integer>(), false, glass.imageId);
				} else {
					crtCocktail.addIngredient(name, parts);
					if(!components.containsKey(name)){
						components.put(name, new ArrayList<String>());
					}
					components.get(name).add(crtCocktail.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void readIngredients(String ingredientsFile) {
		
		try {
			Scanner in = new Scanner(new BufferedInputStream(this.act.getResources().openRawResource(R.raw.ingredients)));
			// since it's a comma-separated file
			in.useDelimiter(Pattern.compile("[,\n\r]"));
			// skip the first header line
			in.nextLine();
			
			// read each line of the file one at a time
			String name, imageID;
			int alcohol, sweetness, herbalness, sourness = 0;
			while (in.hasNext()) {
				name = in.next().toLowerCase();
				alcohol = in.nextInt();
				sweetness = in.nextInt();
				herbalness = in.nextInt();
				sourness = in.nextInt();
				imageID = in.next();
				
				System.out.println(imageID);
				
				Ingredient ingredient;
				if(alcohol == 0){
					ingredient = new Mixer(name, sweetness, herbalness, sourness, Ingredient.IngredientType.valueOf(imageID).imageId);
				} else {
					ingredient = new Alcohol(name, sweetness, herbalness, alcohol, Ingredient.IngredientType.valueOf(imageID).imageId);
				}
				this.ingredients.put(name, ingredient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Ingredient> getIngredientsSortedByAlcohol(){
		List<Ingredient> ingr = new ArrayList<Ingredient>(ingredients.values());
		Collections.sort(ingr, new Comparator<Ingredient>(){

			public int compare(Ingredient lhs, Ingredient rhs) {
				return ((rhs instanceof Alcohol)? ((Alcohol)rhs).getAlcoholPercentage() : 0) -
						((lhs instanceof Alcohol)? ((Alcohol)lhs).getAlcoholPercentage() : 0);
			}
			
		});
		return ingr;
	}

	public List<Ingredient> getIngredientsSortedBySweetness(){
		List<Ingredient> ingr = new ArrayList<Ingredient>(ingredients.values());
		Collections.sort(ingr, new Comparator<Ingredient>(){

			public int compare(Ingredient lhs, Ingredient rhs) {
				return rhs.getSweetness() - lhs.getSweetness();
			}
			
		});
		return ingr;
	}

	public List<Ingredient> getIngredientsSortedByHerbalness(){
		List<Ingredient> ingr = new ArrayList<Ingredient>(ingredients.values());
		Collections.sort(ingr, new Comparator<Ingredient>(){

			public int compare(Ingredient lhs, Ingredient rhs) {
				return rhs.getHerbalness() - lhs.getHerbalness();
			}
			
		});
		return ingr;
	}

	public List<Ingredient> getIngredientsSortedBySourness(){
		List<Ingredient> ingr = new ArrayList<Ingredient>(ingredients.values());
		Collections.sort(ingr, new Comparator<Ingredient>(){

			public int compare(Ingredient lhs, Ingredient rhs) {
				return ((rhs instanceof Mixer)? ((Mixer)rhs).getSourness() : 0) -
						((lhs instanceof Mixer)? ((Mixer)lhs).getSourness() : 0);
			}
			
		});
		return ingr;
	}

	public Map<String, Ingredient> getIngredients() {
		return ingredients;
	}

	public Map<String, Cocktail> getRecipes() {
		return recipes;
	}

	public void setRecipes(Map<String, Cocktail> recipes) {
		this.recipes = recipes;
	}

	public Map<String, List<String>> getComponents() {
		return components;
	}

	public void setComponents(Map<String, List<String>> components) {
		this.components = components;
	}

}
