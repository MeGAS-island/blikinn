package is.blikar.news;

import is.blikar.blikinn.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: This class displays news article on selected news in listview
 *
 */
public class SingleNewsActivity extends Activity {
		        
	// JSON node keys
	private static final String TAG_NEWSTITLE = "news_title";
	private static final String TAG_DETAILS_NEWSPICTURE = "news_picture";
	private static final String TAG_DETAILS_ARTICLE = "article";
	
	DisplayImageOptions options;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_single);
        
        options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_white)
		.showImageForEmptyUri(R.drawable.ic_white)
		.showImageOnFail(R.drawable.ic_white)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new FadeInBitmapDisplayer(20))
		.build();
        
		//Layout
		Typeface font = Typeface.createFromAsset(getAssets(), "aller_rg.ttf");
		TextView header = (TextView) findViewById(R.id.newsTitle_label);
		header.setTypeface(font);   
       
        // Getting intent data
        Intent in = getIntent();
        ImageLoader imageLoader = ImageLoader.getInstance();
        
        // Get JSON values from previous intent
        String varNewsTitle = in.getStringExtra(TAG_NEWSTITLE);
        String varNewsPicture = in.getStringExtra(TAG_DETAILS_NEWSPICTURE);
        String varArticle = in.getStringExtra(TAG_DETAILS_ARTICLE);
        
        // Displaying all values on the screen
        TextView lblNewsTitle = (TextView) findViewById(R.id.newsTitle_label);
        ImageView lblNewsPictureSingle = (ImageView) findViewById(R.id.newsPictureSingle);
        TextView lblArticle = (TextView) findViewById(R.id.article_label);
        
        lblNewsTitle.setText(varNewsTitle);
        imageLoader.displayImage(varNewsPicture, lblNewsPictureSingle, options);
        lblArticle.setText(varArticle);
    }
}