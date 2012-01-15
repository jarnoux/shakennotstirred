package shaken.not.stirred;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends Activity {
	
	List<Suggestion> suggestedCocktails = new ArrayList<Suggestion>();
	
	Cocktail createdCocktail;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
		ListView suggestionListView = (ListView) findViewById(R.id.suggestionsListView);
		
		suggestionListView.setAdapter(new ArrayAdapter<Suggestion>(this, R.layout.suggestion_item, suggestedCocktails));

	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		//TODO reset in onStart() each time we get a new set of ingredients and look into the DB
		Bundle extras = getIntent().getExtras();
//		Set<String> givenIngredients = (Set<String>) extras.get("ingredients");
		Set<String> givenIngredients = new HashSet<String>();
		givenIngredients.add("rum");
		givenIngredients.add("coke");
		Map<String, Integer> quantities = new HashMap<String, Integer>();
		List<String> supersetCocktails = new ArrayList<String>();
		List<String> subsetCocktails = new ArrayList<String>();
		
		for(Cocktail nextCocktail : DataStore.getInstance(this).getRecipes().values()){
			if(nextCocktail.getIngredients().keySet().containsAll(givenIngredients)){
				supersetCocktails.add(nextCocktail.getName());
			}
			boolean subset = false;
			for(String nextIngredient : givenIngredients){
				if(DataStore.getInstance(this).getRecipes().get(nextCocktail).getIngredients().keySet().contains(nextIngredient)){
					subsetCocktails.add(nextCocktail.getName());
					break;
				}
			}
			
		}
		
		if(!supersetCocktails.isEmpty()){
			// take the first possible cocktail
			createdCocktail = DataStore.getInstance(this).getRecipes().get(supersetCocktails.get(0));
		} else {
			// you made that up, didn't you...
			createdCocktail = new Cocktail("<new cocktail>", quantities, true, "defaultCocktailImageId");
		}
		
		((TextView)findViewById(R.id.cocktailNameView)).setText(createdCocktail.getName());
		((ImageView)findViewById(R.id.cocktailIconView)).setImageResource(R.drawable.bourbon_raw);

        //TODO: populate w/ database combinations
        suggestedCocktails.add( new Suggestion("Cool cocktail", "cocktailIcon", "gin", true, createdCocktail));
        suggestedCocktails.add( new Suggestion("Nice cocktail", "cocktailIcon", "vodka", false, createdCocktail));
        suggestedCocktails.add( new Suggestion("Genius cocktail", "cocktailIcon", "wiskey", true, createdCocktail));
        suggestedCocktails.add( new Suggestion("Amazing cocktail", "cocktailIcon", "tequila", true, createdCocktail));
        
	}

}
