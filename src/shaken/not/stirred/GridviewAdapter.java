package shaken.not.stirred;
      
    import java.util.ArrayList;  
    import android.app.Activity;  
import android.view.Gravity;
    import android.view.LayoutInflater;  
    import android.view.View;  
    import android.view.ViewGroup;  
    import android.widget.BaseAdapter;  
    import android.widget.ImageView;  
import android.widget.TextView;  
      
    public class GridviewAdapter extends BaseAdapter  
    {  
        private ArrayList<String> listName;  
        private ArrayList<Integer> listIcon;  
        private Activity activity;  
      
        public GridviewAdapter(Activity activity,ArrayList<String> listCountry, ArrayList<Integer> listFlag) {  
            super();  
            this.listName = listCountry;  
            this.listIcon = listFlag;  
            this.activity = activity;  
        }  
      
        public int getCount() {  
            // TODO Auto-generated method stub  
            return listName.size();  
        }  
        
        public String getItem(int position) {  
            // TODO Auto-generated method stub  
            return listName.get(position);  
        }  
      
        public long getItemId(int position) {  
            // TODO Auto-generated method stub  
            return 0;  
        }  
      
        public static class ViewHolder  
        {  
            public ImageView imgViewFlag;  
            public TextView txtViewTitle;  
        }  
      
        
        public View getView(int position, View convertView, ViewGroup parent) {  
            // TODO Auto-generated method stub  
            ViewHolder view;  
            LayoutInflater inflator = activity.getLayoutInflater();  
      
            if(convertView==null)  
            {  
                view = new ViewHolder();  
                convertView = inflator.inflate(R.layout.gridview_row, null);  
      
                view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView1);  
                view.txtViewTitle.setGravity(Gravity.CENTER_HORIZONTAL);
                view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView1);  
      
                convertView.setTag(view);  
            }  
            else  
            {  
                view = (ViewHolder) convertView.getTag();  
            }  
      
            view.txtViewTitle.setText(listName.get(position));  
            view.imgViewFlag.setImageResource(listIcon.get(position));  
      
            return convertView;  
        }  
    }  