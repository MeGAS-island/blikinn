package is.blikar.news;

import java.io.File;

import is.blikar.blikinn.R;
import android.app.Activity;
import android.content.Context;
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
public class SingleWomanNewsActivity extends Activity {
		        
	// JSON node keys
	private static final String TAG_NEWSTITLE = "news_title";
	private static final String TAG_DETAILS_NEWSPICTURE = "news_picture";
	private static final String TAG_DETAILS_ARTICLE = "article";
	
	DisplayImageOptions options;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_single_women);
        
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
		TextView header = (TextView) findViewById(R.id.newsTitle_label_women);
		header.setTypeface(font);   
        
        // Getting intent data
        Intent in = getIntent();
        ImageLoader imageLoader = ImageLoader.getInstance();
        
        // Get JSON values from previous intent
        String varNewsTitle = in.getStringExtra(TAG_NEWSTITLE);
        String varNewsPicture = in.getStringExtra(TAG_DETAILS_NEWSPICTURE);
        String varArticle = in.getStringExtra(TAG_DETAILS_ARTICLE);
        
        // Displaying all values on the screen
        TextView lblNewsTitle = (TextView) findViewById(R.id.newsTitle_label_women);
        ImageView lblNewsPictureSingle = (ImageView) findViewById(R.id.newsPictureSingle_women);
        TextView lblArticle = (TextView) findViewById(R.id.article_label_women);
        
        lblNewsTitle.setText(varNewsTitle);
        imageLoader.displayImage(varNewsPicture, lblNewsPictureSingle, options);
        lblArticle.setText(varArticle);
    }
	
	   @Override
	   protected void onStop(){
	      super.onStop();
	   }

	   //Fires after the OnStop() state
	   @Override
	   protected void onDestroy() {
	      super.onDestroy();
	      try {
	         trimCache(this);
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }

	   public static void trimCache(Context context) {
	      try {
	         File dir = context.getCacheDir();
	         if (dir != null && dir.isDirectory()) {
	            deleteDir(dir);
	         }
	      } catch (Exception e) {
	         // TODO: handle exception
	      }
	   }

	   public static boolean deleteDir(File dir) {
	      if (dir != null && dir.isDirectory()) {
	         String[] children = dir.list();
	         for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	               return false;
	            }
	         }
	      }

	      // The directory is now empty so delete it
	      return dir.delete();
	   }
}