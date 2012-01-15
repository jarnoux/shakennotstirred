package shaken.not.stirred;

import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeActivity extends Activity {
	private SharedPreferences prefs;
	private final Handler mTwitterHandler = new Handler();
	
    final Runnable mUpdateTwitterNotification = new Runnable() {
        public void run() {
        	Toast.makeText(getBaseContext(), "Tweet sent !", Toast.LENGTH_LONG).show();
        }
    };
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
/*
		HashMap<String, Integer> ingredients = new HashMap<String, Integer>();
		ingredients.put("Gin", 4);
		ingredients.put("Lime Juice", 1);
		ingredients.put("Simple Syrup", 1);
		Cocktail cocktail = new Cocktail("Gimlet", ingredients, true, 1);
*/		
		Cocktail cocktail = null;
		Bundle extras = getIntent().getExtras();
		if(extras!=null) {
			cocktail = (Cocktail) extras.getSerializable("cocktail");
		}
		
		

		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe);
		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

		TextView next = (TextView) findViewById(R.id.textView1);
		next.setText(cocktail.getName());

		TableLayout ingTable = (TableLayout) findViewById(R.id.tableLayout1);
		ingTable.removeAllViews();
		
/*
		String chs = "chd=t:";
		String chl = "chl=";
*/
		for (String s : cocktail.getIngredients().keySet()) {
			TableRow tr = new TableRow(this);

			TextView ingredient = new TextView(this);
			ingredient.setText(s + "     ");
			tr.addView(ingredient);

			if (cocktail.isCustom()) {
				Spinner spinner = new Spinner(this);
				ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
			            this, R.array.intsOneToTen, android.R.layout.simple_spinner_item);
			    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
			    spinner.setAdapter(adapter);
				int ing = cocktail.getIngredients().get(s);
				//parts.setText(ing + " custom parts");
				spinner.setLayoutParams(new TableRow.LayoutParams(120, 60));
				
				
				
				
				
				tr.addView(spinner);
				
				
			} else {
				TextView parts = new TextView(this);
				int ing = cocktail.getIngredients().get(s);
				parts.setText(ing + " parts");
				tr.addView(parts);
			}

			ingTable.addView(tr);
			/*
			 * chs += ing + ","; chl += s + "|";
			 */
		}

		// chs = chs.substring(0, chs.length()-1);
		// chl = chl.substring(0, chl.length()-1);
		
		updateAnalysis(cocktail);
		
		 Button tweet = (Button) findViewById(R.id.tweetButton);
		 /*
		 tweet.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (TwitterUtils.isAuthenticated(prefs)) {
            		sendTweet();
            	} else {
    				Intent i = new Intent(getApplicationContext(), PrepareRequestTokenActivity.class);
    				i.putExtra("tweet_msg",getTweetMsg());
    				startActivity(i);
            	}
				
			}
		});
		*/
		
		

		/*
		 * WebView mCharView = (WebView) findViewById(R.id.webView1); String
		 * mUrl = "https://chart.googleapis.com/chart?cht=p3&chs=250x100";
		 * mCharView.loadUrl(mUrl + "&" + chs + "&" + chl);
		 */
	}
	
	public void updateAnalysis(Cocktail demoCocktail) {
		ProgressBar sweetness = (ProgressBar) findViewById(R.id.sweetnessProgress);
		sweetness.setProgress((int) (demoCocktail.getSweetness() * 10));
		
		ProgressBar sourness = (ProgressBar) findViewById(R.id.sournessProgress);
		sourness.setProgress((int) (demoCocktail.getSourness() * 10));
		
		ProgressBar heaviness = (ProgressBar) findViewById(R.id.heavinessProgress);
		heaviness.setProgress((int) (demoCocktail.getHeaviness() * 10));
		
		ProgressBar herbalness = (ProgressBar) findViewById(R.id.herbalnessProgress);
		herbalness.setProgress((int) (demoCocktail.getHerbalness() * 10));
	}
	/*
	public void sendTweet() {
		Thread t = new Thread() {
	        public void run() {

	        	try {
	        		TwitterUtils.sendTweet(prefs,getTweetMsg());
	        		mTwitterHandler.post(mUpdateTwitterNotification);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
	        }

	    };
	    t.start();
	}
	*/
	private String getTweetMsg() {
		return "Tweeting from Android App at " + new Date().toLocaleString();
	}
}