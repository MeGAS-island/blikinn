package is.blikar.news;

import is.blikar.utils.JSONParser;
import is.blikar.blikinn.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: This class parsers through JSON file and puts in a listview
 *
 */
public class NewsWomanScreenActivity extends Activity {
	
	private ProgressDialog pDialog;
	
	JSONParser jParser = new JSONParser();		
    ArrayList<HashMap<String, String>> newsList;
 	JSONArray news = null; 	
 	ListView list;
    LazyAdapter_newsWomen adapter;
	
	// URL to make request
	private static String url = "http://blikar.is/app/newsJSON_women.php";
	
	// JSON Node names
	static final String TAG_NEWS = "news";
	static final String TAG_NEWSTITLE = "news_title";
	static final String TAG_DETAILS = "details";
	static final String TAG_DETAILS_NEWSPICTURE = "news_picture";
	static final String TAG_DETAILS_ARTICLE = "article";
	static final String TAG_DETAILS_DATE = "date";
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_screen_women);
		
		list=(ListView)findViewById(R.id.list);
		
		// Hashmap for ListView
		newsList = new ArrayList<HashMap<String, String>>();
		adapter=new LazyAdapter_newsWomen(this, newsList); 
	    new LoadNews().execute();	    
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
		
	class LoadNews extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewsWomanScreenActivity.this);
			pDialog.setMessage("Sæki fréttir...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
	
		protected String doInBackground(String... args) {
			JSONObject json = jParser.getJSONFromUrl(url);			
			Log.d("News JSON: ", json.toString());
	
			try {
				news = json.getJSONArray(TAG_NEWS);
			
				for(int i = 0; i < news.length(); i++){
					JSONObject c = news.getJSONObject(i);
					
					// Storing each JOSN item in variable
					String newsTitle = c.getString(TAG_NEWSTITLE);
					
					// Details is a JSON Object
					JSONObject details = c.getJSONObject(TAG_DETAILS);
					String newsPicture = details.getString(TAG_DETAILS_NEWSPICTURE);
					String article = details.getString(TAG_DETAILS_ARTICLE);
					String date = details.getString(TAG_DETAILS_DATE);
									
					// Creating new HashMap
					HashMap<String, String> map_news = new HashMap<String, String>();
					
					// Adding each child node to HashMap key => value
					map_news.put(TAG_NEWSTITLE, newsTitle);
					map_news.put(TAG_DETAILS_NEWSPICTURE, newsPicture);
					map_news.put(TAG_DETAILS_ARTICLE, article);
					map_news.put(TAG_DETAILS_DATE, date);

					// Adding HashList to ArrayList
					newsList.add(map_news);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
						
	        // Click event for single list row
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// Getting values from selected ListItem
					String varNewsTitle = ((TextView) view.findViewById(R.id.newsTitle_women)).getText().toString();
					String varNewsPictureText = ((TextView) view.findViewById(R.id.newsPictureText_women)).getText().toString();
					String varArticle = ((TextView) view.findViewById(R.id.article_women)).getText().toString();
					
					// Starting new intent
					Intent in = new Intent(getApplicationContext(), SingleWomanNewsActivity.class);
					in.putExtra(TAG_NEWSTITLE, varNewsTitle);
					in.putExtra(TAG_DETAILS_NEWSPICTURE, varNewsPictureText);
					in.putExtra(TAG_DETAILS_ARTICLE, varArticle);

					startActivity(in);
				}			
			});
		return null;		
		}

		// After completing background task Dismiss the progress dialog
		protected void onPostExecute(String file_url) {
			// Dismiss the dialog after getting all products
			pDialog.dismiss();
			// Updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					// Updating parsed JSON data into ListView
			        list.setAdapter(adapter);
				}
			});
		}
	}
}