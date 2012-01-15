package shaken.not.stirred;

import java.util.HashSet;
import java.util.Set;


public class Suggestion extends Cocktail {
	
	
	private Cocktail original;
	
	public Suggestion(Cocktail suggestion, Cocktail original) {
		super(suggestion.getName(), suggestion.getIngredients(), false, suggestion.getImageId());
		
		this.original = original;
	}
	

	public Cocktail getOriginal() {
		return original;
	}
	public void setOriginal(Cocktail original) {
		this.original = original;
	}
	
	public String getAdditions(){
		StringBuilder builder = new StringBuilder();
		
		Set<String> superset = new HashSet<String>(this.getIngredients().keySet());
		superset.removeAll(this.original.getIngredients().keySet());
		Set<String> subset = new HashSet<String>(this.original.getIngredients().keySet());
		subset.removeAll(this.getIngredients().keySet());
		
		for(String nextPlus : superset){
			builder.append(" (+").append(nextPlus).append(")");
		}

		for(String nextMinus : subset){
			builder.append(" (-").append(nextMinus).append(")");
		}
		return builder.toString();
		
	}



}
