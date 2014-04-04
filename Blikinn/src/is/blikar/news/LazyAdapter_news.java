package is.blikar.news;

//import is.blikar.utils.ImageLoader;
import is.blikar.blikinn.R;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: This class displays news titles and date in listview
 *
 */
public class LazyAdapter_news extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    ImageLoader imageLoader = ImageLoader.getInstance(); 
    DisplayImageOptions options;
    
    public LazyAdapter_news(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(20))
		.build();        
    }
    
    private class ViewHolder {
		public TextView newsTitle;
		public ImageView newsPicture;
		public TextView article;
		public TextView date;
		public TextView newsPictureText;
	}

    public int getCount() {
        return data.size();
    }

    public Object getItem(int news) {
        return news;
    }

    public long getItemId(int news) {
        return news;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	View view = convertView;
		final ViewHolder holder;
		if (convertView == null) {
			view = inflater.inflate(R.layout.news_list, parent, false);
			holder = new ViewHolder();
			holder.newsTitle = (TextView) view.findViewById(R.id.newsTitle);
			holder.article = (TextView)view.findViewById(R.id.article);
			holder.date = (TextView)view.findViewById(R.id.date);
			holder.newsPictureText = (TextView)view.findViewById(R.id.newsPictureText);			
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		HashMap<String, String> news = new HashMap<String, String>();
        news = data.get(position);
		
		holder.newsTitle.setText(news.get(NewsScreenActivity.TAG_NEWSTITLE));
		holder.article.setText(news.get(NewsScreenActivity.TAG_DETAILS_ARTICLE));
		holder.date.setText(news.get(NewsScreenActivity.TAG_DETAILS_DATE));
		holder.newsPictureText.setText(news.get(NewsScreenActivity.TAG_DETAILS_NEWSPICTURE));

		imageLoader.displayImage(news.get(NewsScreenActivity.TAG_DETAILS_NEWSPICTURE), holder.newsPicture, options);

		return view;
    }
}