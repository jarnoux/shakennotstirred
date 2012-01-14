package shaken.not.stirred;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class ShelfActivity extends Activity
{  	
	
    private Integer[] mThumbIds = {
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher,
            R.drawable.bourbon_raw, R.drawable.ic_launcher
    };
    
    private GridView gridview;
    private ImageAdapter ia;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.shelf);

	    gridview = (GridView) findViewById(R.id.gridview);
	    ia = new ImageAdapter(this);
	    gridview.setAdapter(ia);

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	    	    setItemId(position);
	    	    gridview.setAdapter(ia);
	        }
	    });
	}
	
    public boolean setItemId(int position) {
    	if (mThumbIds[position] == R.drawable.bourbon_raw) {
    		Log.d("BLAH", String.valueOf(position));
    		mThumbIds[position] = R.drawable.ic_launcher;
    		return true;
    	}
    	
    	return false;
    }
    
	
	public class ImageAdapter extends BaseAdapter {
	    private Context mContext;

	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	        return mThumbIds.length;
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

	    // create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(mThumbIds[position]);
	        return imageView;
	    }
	}
}