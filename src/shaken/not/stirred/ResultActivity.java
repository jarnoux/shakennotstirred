package shaken.not.stirred;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultActivity extends Activity {
	
	List<Suggestion> suggestedCocktails = new ArrayList<Suggestion>();
	
	Cocktail createdCocktail;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
		ListView suggestionListView = (ListView) findViewById(R.id.suggestionsListView);
		
		suggestionListView.setAdapter(new SuggestionArrayAdapter(this, R.layout.suggestion_item, suggestedCocktails));
		final Activity self = this;
		suggestionListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView parent, View v, int position, long id) {
				Intent i = new Intent(self, RecipeActivity.class);
				i.putExtra("cocktail", DataStore.getInstance(self).getRecipes().get(suggestedCocktails.get(position).getName()));
				startActivity(i);
			}
		});
		
		((RelativeLayout) findViewById(R.id.resultHeaderView)).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(self, RecipeActivity.class);
				i.putExtra("cocktail", DataStore.getInstance(self).getRecipes().get(createdCocktail.getName()));
				startActivity(i);
			}
		});

	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		Bundle extras = getIntent().getExtras();
//		Set<String> givenIngredients = (Set<String>) extras.get("ingredients");
		//TODO be called by the first activity
		Set<String> givenIngredients = new HashSet<String>();
		givenIngredients.add("rum");
		givenIngredients.add("coke");
		
		Map<String, Integer> quantities = new HashMap<String, Integer>();
		for(String givenIngredient : givenIngredients){
			quantities.put(givenIngredient, 1);
		}
		List<String> relatedCocktails = new ArrayList<String>();
		
		createdCocktail = null;
		suggestedCocktails.clear();
		
		for(Cocktail nextCocktail : DataStore.getInstance(this).getRecipes().values()){
			boolean subset = false;
			for(String nextIngredient : givenIngredients){
				if(DataStore.getInstance(this).getRecipes().get(nextCocktail.getName()).getIngredients().keySet().contains(nextIngredient)){
					if(nextCocktail.getIngredients().keySet().equals(givenIngredients)){
						createdCocktail = nextCocktail;
					} else {
						relatedCocktails.add(nextCocktail.getName());
					}
					break;
				}
			}
			
		}
		
		if(createdCocktail == null){
			// you made that up, didn't you...
			createdCocktail = new Cocktail("Unknown Cocktail", quantities, true, R.drawable.cocktail_2);
		} 

		if(!relatedCocktails.isEmpty()){
			Cocktail crtCocktail;
			for(String nextSuggestedCocktail : relatedCocktails){
				crtCocktail = DataStore.getInstance(this).getRecipes().get(nextSuggestedCocktail);
				suggestedCocktails.add(new Suggestion(crtCocktail, createdCocktail));
			}
			
		} 
		
		((TextView)findViewById(R.id.cocktailNameView)).setText(createdCocktail.getName());
		((ImageView)findViewById(R.id.cocktailIconView)).setImageResource(createdCocktail.getImageId());

	}
	
	private class SuggestionArrayAdapter extends ArrayAdapter<Suggestion>{
		
		private List<Suggestion> suggestions;

		public SuggestionArrayAdapter(Context context, int textViewResourceId,
				List<Suggestion> objects) {
			super(context, textViewResourceId, objects);
			this.suggestions = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent){

	        View row = convertView;
	        if (row == null) {
	            // ROW INFLATION
	            LayoutInflater inflater = (LayoutInflater) this.getContext()
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            row = inflater.inflate(R.layout.suggestion_item, parent, false);
	        }
	        
	        Suggestion suggestion = this.suggestions.get(position);
	        ImageView cocktailIcon = (ImageView) row.findViewById(R.id.cocktailIcon);
	        TextView cocktailName = (TextView) row.findViewById(R.id.cocktailName);
	        TextView cocktailAdditions = (TextView) row.findViewById(R.id.cocktailAdditions);

	        cocktailIcon.setImageResource(suggestion.getImageId());
	        cocktailName.setText(suggestion.getName());
	        cocktailAdditions.setText(suggestion.getAdditions());
			return row;
		}
		
	}

}
