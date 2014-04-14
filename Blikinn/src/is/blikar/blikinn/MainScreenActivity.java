package is.blikar.blikinn;

import static is.blikar.pictures.Constants.IMAGES;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import is.blikar.pictures.Constants.Extra;
import is.blikar.blikinn.R;
import is.blikar.games.GamesNow;
import is.blikar.news.NewsActivity;
import is.blikar.pictures.*;
import is.blikar.players.PlayersActivity;
import is.blikar.radio.RadioScreenActivity;
import is.blikar.trophies.TrophiesActivity;
import is.blikar.utils.JSONParser;
import is.blikar.TV.TVListActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author Aðalsteinn Stefánsson
 * @since 6.11.13
 * Description: This class is main menu with buttons to navigate
 *
 */

// Snorri er meistari
public class MainScreenActivity extends Activity {
	JSONParser jParser = new JSONParser();
	JSONArray photos = null;
	private static String url = "http://blikar.is/app/photosJSON.php";
	static final String TAG_INSTAGRAM = "instagram";
	static final String TAG_PHOTO= "photo";
	
	Boolean isInternetOn = false;
    ConnectionDetector cd;
    final Context context = this;
    CharSequence ok = "OK";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen_2);
		
		cd = new ConnectionDetector(getApplicationContext());
        isInternetOn = cd.isConnectingToInternet();
        if(!isInternetOn){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Engin nettenging");
            alertDialogBuilder
                   .setMessage("Þú ert ekki tengdur netinu. Vinsamlegast kveiktu á netinu til að nota appið.")
                   .setCancelable(false)
            	   .setPositiveButton(ok, new OnClickListener() {
            		   public void onClick(DialogInterface dialog, int id) { }
            		   
        			});
                   AlertDialog alertDialog = alertDialogBuilder.create();
                   alertDialog.show();
        		}
        else new LoadInstagramPhotos().execute();
		
        
        //Layout
		Typeface font = Typeface.createFromAsset(getAssets(), "aller_rg.ttf");
		TextView header = (TextView) findViewById(R.id.BlikinnHeader);
		header.setTypeface(font);       
	
		//BUTTONS
		Button btnNewsMainScreen = (Button) findViewById(R.id.btnNews);
		btnNewsMainScreen.setTypeface(font);
		btnNewsMainScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if(!isInternetOn){
		            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		            alertDialogBuilder.setTitle("Engin nettenging");
		            alertDialogBuilder
		            	.setMessage("Ekki náðist tenging við þjónustu, athugaðu netstillingar.")
		            	.setCancelable(false)
		            	.setPositiveButton(ok, new OnClickListener() {
		            		public void onClick(DialogInterface dialog, int id) { }
            		   
		            	});
		                   AlertDialog alertDialog = alertDialogBuilder.create();
		                   alertDialog.show();
		        } else {
				Intent NewsScreen = new Intent(MainScreenActivity.this, NewsActivity.class);
				startActivity(NewsScreen);
				}
			}
		});
		
		Button btnGamesResultsMainScreen = (Button) findViewById(R.id.btnGames);
		btnGamesResultsMainScreen.setTypeface(font);
	    btnGamesResultsMainScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if(!isInternetOn){
		            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		            alertDialogBuilder.setTitle("Engin nettenging");
		            alertDialogBuilder
		                   .setMessage("Ekki náðist tenging við þjónustu, athugaðu netstillingar.")
		                   .setCancelable(false)
		                   .setPositiveButton(ok, new OnClickListener() {
		            			public void onClick(DialogInterface dialog, int id) { }
            		   
		                   });
		                   AlertDialog alertDialog = alertDialogBuilder.create();
		                   alertDialog.show();
		        } else {
				Intent GamesResultsScreen = new Intent(MainScreenActivity.this, GamesNow.class);
				startActivity(GamesResultsScreen);
		        }
			}
		});		
		
		Button btnPlayersMainScreen = (Button) findViewById(R.id.btnPlayers);
		btnPlayersMainScreen.setTypeface(font);
		btnPlayersMainScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if(!isInternetOn){
		            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		            alertDialogBuilder.setTitle("Engin nettenging");
		            alertDialogBuilder
		                   .setMessage("Ekki náðist tenging við þjónustu, athugaðu netstillingar.")
		                   .setCancelable(false)
		                   .setPositiveButton(ok, new OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int id) { }	   
		                   });
		                   AlertDialog alertDialog = alertDialogBuilder.create();
		                   alertDialog.show();
		        } else {
				Intent PlayersMenScreen = new Intent(MainScreenActivity.this, PlayersActivity.class);
				startActivity(PlayersMenScreen);
		        }
			}
		});
		
		Button btnTrophiesMainScreen = (Button) findViewById(R.id.btnTrophies);
		btnTrophiesMainScreen.setTypeface(font);
		btnTrophiesMainScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent TrophiesMenScreen = new Intent(MainScreenActivity.this, TrophiesActivity.class);
				startActivity(TrophiesMenScreen);
			}
		});
		
		Button btnPicturesMainScreen = (Button) findViewById(R.id.btnPhotos);
		btnPicturesMainScreen.setTypeface(font);
		btnPicturesMainScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if(!isInternetOn){
		            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		            alertDialogBuilder.setTitle("Engin nettenging");
		            alertDialogBuilder
		                   .setMessage("Ekki náðist tenging við þjónustu, athugaðu netstillingar.")
		                   .setCancelable(false)
		                   .setPositiveButton(ok, new OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int id) { }
		            		});
		                   AlertDialog alertDialog = alertDialogBuilder.create();
		                   alertDialog.show();
		        } else {
				Intent PicturesScreen = new Intent(MainScreenActivity.this, ImageGridActivity.class);
				PicturesScreen.putExtra(Extra.IMAGES, IMAGES);
				startActivity(PicturesScreen);
		        }
			}
		});
		
		Button btnRadioMainScreen = (Button) findViewById(R.id.btnRadio);
		btnRadioMainScreen.setTypeface(font);
		btnRadioMainScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if(!isInternetOn){
		            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		            alertDialogBuilder.setTitle("Engin nettenging");
		            alertDialogBuilder
		                   .setMessage("Ekki náðist tenging við þjónustu, athugaðu netstillingar.")
		                   .setCancelable(false)
		                   .setPositiveButton(ok, new OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int id) { }
		                   });
		                   AlertDialog alertDialog = alertDialogBuilder.create();
		                   alertDialog.show();
		        } else {
				Intent RadioScreen = new Intent(MainScreenActivity.this, RadioScreenActivity.class);
				startActivity(RadioScreen);
		        }
			}
		});
		
		Button btnTvMainScreen = (Button) findViewById(R.id.btnTV);
		btnTvMainScreen.setTypeface(font);
		btnTvMainScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if(!isInternetOn){
		            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		            alertDialogBuilder.setTitle("Engin nettenging");
		            alertDialogBuilder
		                   .setMessage("Ekki náðist tenging við þjónustu, athugaðu netstillingar.")
		                   .setCancelable(false)
		                   .setPositiveButton(ok, new OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int id) { }
		                   });
		                   AlertDialog alertDialog = alertDialogBuilder.create();
		                   alertDialog.show();
		        } else {
				Intent TvScreen = new Intent(MainScreenActivity.this, TVListActivity.class);
				startActivity(TvScreen);
		        }
			}
		});			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_screen, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.about:
	        	Intent AboutScreen = new Intent(getApplicationContext(), About.class);
	   		 	startActivity(AboutScreen);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void onBackPressed() {
		   Intent intent = new Intent(Intent.ACTION_MAIN);
		   intent.addCategory(Intent.CATEGORY_HOME);
		   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   startActivity(intent);
	}
	
	class LoadInstagramPhotos extends AsyncTask<String, String, String> {
			
		@SuppressLint("NewApi")
		protected String doInBackground(String... args) {
			JSONObject json = jParser.getJSONFromUrl(url);
	
			try {
				photos = json.getJSONArray(TAG_INSTAGRAM);
				
				for(int i = 0; i < photos.length(); i++){
					JSONObject c = photos.getJSONObject(i);
					String photo = c.getString(TAG_PHOTO);
					// Adds each photo to String IMAGES (is.blikar.pictures.Constants.IMAGES)
					Constants.IMAGES[i] = photo;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}	
		return null;		
		}				
	}
}