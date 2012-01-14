package shaken.not.stirred;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class ShelfActivity extends Activity
{  	
    private ArrayList<String> listName;  
    private ArrayList<Integer> listIcon; 
    private GridviewAdapter mAdapter;  
    private GridView gridView;  
    private ImageView shaker;	
	
    
    Animation hyperspaceJump;
    private Animation anim;
    
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.shelf);
	    
        prepareList();  
	    
	    hyperspaceJump = 
	            AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
        
	    shaker = (ImageView) findViewById (R.id.shaker);
	    
        // prepared arraylist and passed it to the Adapter class  
        mAdapter = new GridviewAdapter(this,listName, listIcon);  
  
        // Set custom adapter to gridview  
        gridView = (GridView) findViewById(R.id.gridview);  
        gridView.setAdapter(mAdapter);  
        
        // Implement On Item click listener  
        gridView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	    	    setItemId(position);
	    	    gridView.setAdapter(mAdapter);
	    	    shaker.startAnimation(hyperspaceJump);

	        }
	    });

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
