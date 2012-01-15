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
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShelfActivity extends Activity implements SensorListener {
	
	//Shake Stuff
    private SensorManager sensorMgr;
    private long lastUpdate = -1;
    private float x, y, z;
    private float last_x, last_y, last_z;
    private static int SHAKE_THRESHOLD = 2000;
    Vibrator v;
	//Shake Stuff
		
	private ArrayList<String> listName;
	private ArrayList<Integer> listIcon;
	private GridviewAdapter mAdapter;
	private ArrayAdapter<CharSequence> adapter;
	private GridView gridView;
	private ImageView shaker;
	private boolean shakerOpen;
	private boolean clearFlag;
	
	private HashSet<String> drinkPicks;
	public DataStore ds;
	private Animation hyperspaceJump;
	//private Intent i;
	
	public void onPause() {
		super.onPause();
		clearFlag = true;
	}
	
	public void onResume() {
		super.onResume();
		boolean accelSupported = sensorMgr.registerListener(this,
				SensorManager.SENSOR_ACCELEROMETER,
				SensorManager.SENSOR_DELAY_GAME);
		
		if (clearFlag) {
			drinkPicks = new HashSet<String>();
			TextView tv;
			tv = (TextView) findViewById(R.id.textViewz1);
			tv.setText("+");

			tv = (TextView) findViewById(R.id.textViewz2);
			tv.setText("+ ");

			tv = (TextView) findViewById(R.id.textViewz3);
			tv.setText("+ ");

			clearFlag = false;
		}
	}
	
//	public class DownloadImageTask extends AsyncTask<Void, Void, String> {
//	     protected String doInBackground(Void... values) {
//	    	 prepareList();
//	         return null;
//	     }
//
//	     protected void onPostExecute(String result) {
//	 		gridView.setAdapter(mAdapter);
//	     }
//	 }
//	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shelf);
		
		//Vibrator
		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		// start motion detection
		sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		boolean accelSupported = sensorMgr.registerListener(this,
			SensorManager.SENSOR_ACCELEROMETER,
			SensorManager.SENSOR_DELAY_GAME);
		if (!accelSupported) {
		    // on accelerometer on this device
		    sensorMgr.unregisterListener(this,
	                SensorManager.SENSOR_ACCELEROMETER);
		}
		
		// Initialize DataStore
		ds = DataStore.getInstance(this);
		
		shakerOpen = true;
		drinkPicks = new HashSet<String>();
		
		prepareList();
		//spinnerStart();
		shakerStart();
		
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
//				setItemId(position);
				int drinkCount = drinkPicks.size();
				
				if (drinkCount < 3) {
					shaker.startAnimation(hyperspaceJump);
					String name = listName.get(position);
					
					gridView.setAdapter(mAdapter);
					drinkPicks.add(name);
					TextView tv = null;
					switch (drinkCount) {
					case 0:
						tv = (TextView) findViewById(R.id.textViewz1);
						break;
					case 1:
						tv = (TextView) findViewById(R.id.textViewz2);
						break;
					case 2:
						tv = (TextView) findViewById(R.id.textViewz3);
						break;
					}
					
					tv.setText("+ " + name);
				}
			}
		});

//		Button butt1 = (Button) findViewById (R.id.button1);
//		butt1.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				  new Thread(new Runnable() {
//					    public void run() {
//					      prepareList();
//					      gridView.post(new Runnable() {
//					        public void run() {
//					        	gridView.setAdapter(mAdapter);
//					        }
//					      });
//					    }
//					  }).start();
//
//			}
//			
//		});
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
				
				 if("Alcoholic".equals(choice)) { 
					 ingList = ds.getIngredientsSortedByAlcohol(); }
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
		//i = new Intent(this, ResultActivity.class);
		
		shaker = (ImageView) findViewById(R.id.shaker);
		shaker.setClickable(true);

//		// Implement On click listener
//		shaker.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				shaker.setImageDrawable(getResources().getDrawable(R.drawable.shaker_closed));
//				shakerOpen = true;
//				
////				i.putExtra("ingredients", drinkPicks);
////				startActivity(i);
//			}
//		});
	}
	
//	
//	public void spinnerStart() {
//		Spinner spinner = (Spinner) findViewById(R.id.spinner);
//		adapter = ArrayAdapter.createFromResource(this, R.array.planets_array,
//				android.R.layout.simple_spinner_item);
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner.setAdapter(adapter);
//		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
//	}
	
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

//	public boolean setItemId(int position) {
//		if (listIcon.get(position) != R.drawable.silhouette) {
//			Log.d("BLAH", String.valueOf(position));
//			listIcon.set(position, R.drawable.silhouette);
//			return true;
//		}
//
//		return false;
//	}

	public void onAccuracyChanged(int sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(int sensor, float[] values) {
    	Log.d("sensor", "onSensorChanged: " + sensor);
	if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
	    long curTime = System.currentTimeMillis();
	    // only allow one update every 100ms.
	    if ((curTime - lastUpdate) > 100) {
		long diffTime = (curTime - lastUpdate);
		lastUpdate = curTime;

		x = values[SensorManager.DATA_X];
		y = values[SensorManager.DATA_Y];
		z = values[SensorManager.DATA_Z];

		float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;
		
		// Log.d("sensor", "diff: " + diffTime + " - speed: " + speed);
		if (speed > SHAKE_THRESHOLD) {
			sensorMgr.unregisterListener(this);
			//Log.d("sensor", "shake detected w/ speed: " + speed);
		    //Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
			Intent i = new Intent(this, ResultActivity.class);
			v.vibrate(1000);
			
			i.putExtra("ingredients", drinkPicks);
			startActivity(i);
		}
		last_x = x;
		last_y = y;
		last_z = z;
	    }
	}
		
	}

}
