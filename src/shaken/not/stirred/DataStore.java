package shaken.not.stirred;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataStore{

	
	private Map<String, Ingredient> ingredients = new HashMap<String, Ingredient>();

	private Map<String, Cocktail> recipes = new HashMap<String, Cocktail>();
	private Map<String, List<String>> components = new HashMap<String, List<String>>();
	
	public DataStore(String ingredientsFile, String recipesFile) {
		readIngredients(ingredientsFile);
		readRecipes(recipesFile);
	}
	
	private void readRecipes(String recipesFile) {
		
		try {
			Scanner in = new Scanner(new File(recipesFile));
			// since it's a comma-separated file
			in.useDelimiter(",");
			// skip the first header line
			in.nextLine();
			
			// read each line of the file one at a time
			String name;
			boolean isDrink;
			int parts = -1;
			Cocktail.GlassType glass;
			Cocktail crtCocktail = null;
			while (in.hasNext()) {
				name = in.next();
				isDrink = (in.nextInt() == 1);
				try{
					parts = in.nextInt();
				} catch (InputMismatchException e){}
				glass = Cocktail.GlassType.valueOf(in.next());
				
				if(isDrink){
					if(crtCocktail != null){
						recipes.put(crtCocktail.getName(), crtCocktail);
					}
					crtCocktail = new Cocktail(name, new HashMap<String, Integer>(), false, "cocktailIcon");
				} else {
					crtCocktail.addIngredient(name, parts);
					if(!components.containsKey(name)){
						components.put(name, new ArrayList<String>());
					}
					components.get(name).add(name);
				}
				
				in.nextLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void readIngredients(String ingredientsFile) {
		
		try {
			Scanner in = new Scanner(new File(ingredientsFile));
			// since it's a comma-separated file
			in.useDelimiter(",");
			// skip the first header line
			in.nextLine();
			
			// read each line of the file one at a time
			String name;
			int alcohol, sweetness, herbalness, sourness = 0;
			while (in.hasNext()) {
				name = in.next();
				alcohol = in.nextInt();
				sweetness = in.nextInt();
				herbalness = in.nextInt();
				sourness = in.nextInt();
				Ingredient ingredient;
				if(alcohol != 0){
					ingredient = new Mixer(name, sweetness, herbalness, sourness);
				} else {
					ingredient = new Alcohol(name, sweetness, herbalness, alcohol);
				}
				this.ingredients.put(name, ingredient);
				in.nextLine();
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
