package shaken.not.stirred;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

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
		Set<Ingredient> givenIngredients = (HashSet) extras.get("ingredients");
		for(Ingredient nextIngredient : givenIngredients){
			
		}
		createdCocktail = new Cocktail("cool cocktail", new HashMap<String, Integer>(), false, (ImageView) findViewById(R.id.cocktailIconView));

        //TODO: populate w/ database combinations
        suggestedCocktails.add( new Suggestion("Cool cocktail", (ImageView) findViewById(R.id.cocktailIconView), "gin", true, createdCocktail));
        suggestedCocktails.add( new Suggestion("Nice cocktail", (ImageView) findViewById(R.id.cocktailIconView), "vodka", false, createdCocktail));
        suggestedCocktails.add( new Suggestion("Genius cocktail", (ImageView) findViewById(R.id.cocktailIconView), "wiskey", true, createdCocktail));
        suggestedCocktails.add( new Suggestion("Amazing cocktail", (ImageView) findViewById(R.id.cocktailIconView), "tequila", true, createdCocktail));
        
	}

}
