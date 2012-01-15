package shaken.not.stirred;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShelfActivity extends Activity {
	private ArrayList<String> listName;
	private ArrayList<Integer> listIcon;
	private GridviewAdapter mAdapter;
	private ArrayAdapter<CharSequence> adapter;
	private GridView gridView;
	private ImageView shaker;
	private boolean shakerOpen;

	private HashSet<String> drinkPicks;
	public DataStore ds;
	private Animation hyperspaceJump;
	private Intent i;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shelf);
		
		// Initialize DataStore
		ds = DataStore.getInstance(this);
		
		shakerOpen = true;
		drinkPicks = new HashSet<String>();
		
		prepareList();
		spinnerStart();
		
		hyperspaceJump = AnimationUtils.loadAnimation(this,
				R.anim.hyperspace_jump);
		
		// prepared arraylist and passed it to the Adapter class
		mAdapter = new GridviewAdapter(this, listName, listIcon);

		// Set custom adapter to gridview
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(mAdapter);

		// Implement On Item click listener
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				setItemId(position);
				gridView.setAdapter(mAdapter);
	
				//CHANGE FOR REMOVING LATER
				drinkPicks.add(listName.get(position));
			}
		});

	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {

			String choice = parent.getItemAtPosition(pos).toString();
			List<Ingredient> ingList = null;

			if ("All flavors".equals(choice)) {
				Toast.makeText(parent.getContext(), "Showing all flavors",
						Toast.LENGTH_LONG).show();
				//prepareList();
			} else {
				Toast.makeText(parent.getContext(),
						"Showing only " + choice.toLowerCase() + " flavors",
						Toast.LENGTH_LONG).show();
				
				 if("Alcoholic".equals(choice)) { ingList =
				 ds.getIngredientsSortedByAlcohol(); }
			}

//			
//			listName = new ArrayList<String>(); 
//			listIcon = new ArrayList<Integer>();
//			  
//			for (int i = 0; i < ingList.size(); i++) {
//				listName.add(ingList.get(i).getName());
//				listIcon.add(ingList.get(i).getImageID()); 
//			}
//			  
//			gridView.setAdapter(mAdapter);
			
		}

		public void onNothingSelected(AdapterView parent) {
			// Do nothing.
		}
	}

	public void shakerStart() {
		i = new Intent(this, ResultActivity.class);
		
		shaker = (ImageView) findViewById(R.id.shaker);
		shaker.setClickable(true);

		// Implement On click listener
		shaker.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(shakerOpen) { 
					shaker.setImageDrawable(getResources().getDrawable(R.drawable.shaker_closed));
					shakerOpen = true;
				} else {
					//shaker.setImageDrawable(getResources().getDrawable(R.drawable.shaker_open));
					shakerOpen = false;
				}
				
//				i.putExtra("ingredients", drinkPicks);
//				startActivity(i);
			}
		});
	}
	
	public void spinnerStart() {
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		adapter = ArrayAdapter.createFromResource(this, R.array.planets_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}
	
	public void prepareList() {
		listName = new ArrayList<String>();
		listIcon = new ArrayList<Integer>();
		
		Map<String, Ingredient> ingMap =  ds.getIngredients();
		TreeSet<String> keys = new TreeSet<String>(ingMap.keySet());
		
		for (String key : keys) {
			listName.add(key);
			listIcon.add(ingMap.get(key).getImageID());
		}
	}

	public boolean setItemId(int position) {
		if (listIcon.get(position) != R.drawable.silhouette) {
			Log.d("BLAH", String.valueOf(position));
			listIcon.set(position, R.drawable.silhouette);
			return true;
		}

		return false;
	}

}
