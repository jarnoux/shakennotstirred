package shaken.not.stirred;

import java.util.HashMap;

import android.widget.ImageView;


public class Suggestion extends Cocktail {
	
	private String difference;
	
	// if is subset, then the suggestion is the original minus the different ingredient,
	// else it is the original plus the difference.
	private boolean isSubsetOfOriginal;
	private Cocktail original;
	
	
	
	public Suggestion(String name, String imageId, String difference,
			boolean isSubsetOfOriginal, Cocktail original) {
		super(name, original.getIngredients(), false, imageId);
		this.getIngredients().remove(difference);
		
		this.difference = difference;
		this.isSubsetOfOriginal = isSubsetOfOriginal;
		this.original = original;
	}
	
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
	public boolean isSubsetOfOriginal() {
		return isSubsetOfOriginal;
	}
	public void setSubsetOfOriginal(boolean isSubsetOfOriginal) {
		this.isSubsetOfOriginal = isSubsetOfOriginal;
	}
	public Cocktail getOriginal() {
		return original;
	}
	public void setOriginal(Cocktail original) {
		this.original = original;
	}
	
	public String toString(){
		return this.getName() + " ( "+ (isSubsetOfOriginal?"- ":"+ ") + this.getDifference() + ")";
	}
	

}
