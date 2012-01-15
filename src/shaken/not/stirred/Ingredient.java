package shaken.not.stirred;

import java.io.Serializable;
import java.lang.reflect.Field;

import shaken.not.stirred.R.drawable;
import android.util.Log;

public class Ingredient implements Serializable{
	private String name;
	private int sweetness;
	private int herbalness;
	private int sourness;
	private int imageID;
	
	public Ingredient(String name, int sweetness, int herbalness, int sourness, int imageID) {
		super();
		this.name = name;
		this.sweetness = sweetness;
		this.herbalness = herbalness;
		this.sourness = sourness;
		this.imageID = imageID;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSweetness() {
		return sweetness;
	}
	public void setSweetness(int sweetness) {
		this.sweetness = sweetness;
	}
	public int getHerbalness() {
		return herbalness;
	}
	public void setHerbalness(int herbalness) {
		this.herbalness = herbalness;
	}
	public int getSourness() {
		return sourness;
	}
	public void setSourness(int sourness) {
		this.sourness = sourness;
	}

	public int getImageID() {
		return imageID;
	}

	public enum IngredientType {
		Amaretto(R.drawable.amaretto),
		Apple_Shnapps(R.drawable.apple_schnapps),
		Blue_Curacao(R.drawable.blue_curacao),
		Butterscotch_Schnapps(R.drawable.butterscotch_schnapps),
		Campari(R.drawable.campari),
		Chambord(R.drawable.chambord),
		Creme_de_almond(R.drawable.creme_de_almond),
		Creme_de_cacao_dark(R.drawable.creme_de_almond_dark),
		Creme_de_cacao_light(R.drawable.creme_de_almond_light),
		Creme_de_menthe_green(R.drawable.creme_de_menthe_green),
		Creme_de_menther_light(R.drawable.creme_de_menthe_light),
		Drambuie(R.drawable.drambuie),
		Dry_vermouth(R.drawable.dry_vermouth),
		Dubonnet(R.drawable.dubonnet),
		Frangelico(R.drawable.frangelico),
		Galliano(R.drawable.galliano),
		Grand_Marnier(R.drawable.grand_marnier),
		Irish_cream(R.drawable.irish_cream),
		Jaegermeister(R.drawable.jaegermeister),
		Kahlua(R.drawable.kahlua),
		Lillet(R.drawable.lillet),
		Melon_liquor(R.drawable.melon_liquor),
		Peach_schnapps(R.drawable.peach_schnapps),
		Sambuca(R.drawable.sambuca),
		Sloe_gin(R.drawable.sloe_gin),
		Southern_comfort(R.drawable.southern_comfort),
		Sweet_vermouth(R.drawable.sweet_vermouth),
		Triple_sec(R.drawable.triple_sec),
		Bourbon(R.drawable.bourbon),
		Brandy(R.drawable.brandy),
		Gin(R.drawable.gin),
		Rum(R.drawable.rum),
		Rum_151(R.drawable.rum),
		Rum_dark(R.drawable.rum),
		Rum_light(R.drawable.rum),
		Scotch(R.drawable.scotch),
		Vodka(R.drawable.vodka),
		Tequila(R.drawable.tequila),
		Club_soda(R.drawable.club_soda),
		SevenUp(R.drawable.seven_up),
		Coke(R.drawable.coke),
		Tonic(R.drawable.tonic),
		Ginger_ale(R.drawable.ginger_ale),
		Pineapple_juice(R.drawable.pineapple_juice),
		Cranberry_juice(R.drawable.cranberry_juice),
		Orange_juice(R.drawable.orange_juice),
		Grapefruit_juice(R.drawable.grapefruit_juice),
		Lime_juice(R.drawable.lime_juice),
		Grenatine(R.drawable.grenadine),
		Simple_syrup(R.drawable.simple_syrup),
		Tomato_juice(R.drawable.tomato_juice),
		Cream(R.drawable.cream),
		Half_and_half(R.drawable.half_and_half),
		Peach_nectar(R.drawable.peach_nectar),
		Coffee(R.drawable.coffee),
		Fresh_lemon_juice(R.drawable.fresh_lemon_juice),
		Fresh_lime_juice(R.drawable.fresh_lime_juice),
		Sweet_and_sour(R.drawable.sweet_and_sour);
		
		
		IngredientType(int imageId){
			this.imageId = imageId;
		}
		
		int imageId;
	}
}
