package shaken.not.stirred;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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
	public DataStore ds;

	private Animation hyperspaceJump;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shelf);

		// ds = DataStore.getInstance(this);

		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		adapter = ArrayAdapter.createFromResource(this, R.array.planets_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

		prepareList();

		hyperspaceJump = AnimationUtils.loadAnimation(this,
				R.anim.hyperspace_jump);

		shaker = (ImageView) findViewById(R.id.shaker);
		shaker.setClickable(true);
		
		// Implement On click listener
		shaker.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shaker.setImageDrawable(getResources().getDrawable(R.drawable.shaker_closed));
			}
		});
		
		
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
				shaker.startAnimation(hyperspaceJump);
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
			} else {
				Toast.makeText(parent.getContext(),
						"Showing only " + choice.toLowerCase() + " flavors",
						Toast.LENGTH_LONG).show();
				/*
				 * if("Alcoholic".equals(choice)) { ingList =
				 * ds.getIngredientsSortedByAlcohol(); }
				 */
			}

			/*
			 * listName = new ArrayList<String>(); listIcon = new
			 * ArrayList<Integer>();
			 * 
			 * for (int i = 0; i < ingList.size(); i++) {
			 * listName.add(ingList.get(i).getName());
			 * listIcon.add(ingList.get(i).getImageID()); }
			 * 
			 * gridView.setAdapter(mAdapter);
			 */
		}

		public void onNothingSelected(AdapterView parent) {
			// Do nothing.
		}
	}

	public void prepareList()

	{

		listName = new ArrayList<String>();

		listName.add("India");

		listName.add("Brazil");

		listName.add("Canada");

		listName.add("China");

		listName.add("France");

		listName.add("Germany");

		listName.add("Iran");

		listName.add("Italy");

		listName.add("Japan");

		listName.add("Korea");

		listName.add("Mexico");

		listName.add("Netherlands");

		listName.add("Portugal");

		listName.add("Russia");

		listName.add("Saudi Arabia");

		listName.add("Spain");

		listName.add("Turkey");

		listName.add("United Kingdom");

		listName.add("United States");

		listIcon = new ArrayList<Integer>();

		listIcon.add(R.drawable.amaretto);

		listIcon.add(R.drawable.amaretto);

		listIcon.add(R.drawable.amaretto);

		listIcon.add(R.drawable.blue_curacao);

		listIcon.add(R.drawable.campari);

		listIcon.add(R.drawable.blue_curacao);

		listIcon.add(R.drawable.campari);

		listIcon.add(R.drawable.amaretto);

		listIcon.add(R.drawable.campari);

		listIcon.add(R.drawable.campari);

		listIcon.add(R.drawable.amaretto);

		listIcon.add(R.drawable.amaretto);

		listIcon.add(R.drawable.campari);

		listIcon.add(R.drawable.campari);

		listIcon.add(R.drawable.amaretto);

		listIcon.add(R.drawable.campari);

		listIcon.add(R.drawable.amaretto);

		listIcon.add(R.drawable.campari);

		listIcon.add(R.drawable.campari);

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
