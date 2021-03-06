package is.blikar.games;

import is.blikar.utils.JSONParser;
import is.blikar.utils.SlideHolder;
import is.blikar.blikinn.MainScreenActivity;
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
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 
 * @author Snorri Hannesson
 * @since 20.11.13
 * Description: This class passes strings to listview adapter and creates buttons for slider
 *
 */
public class NextGamesMenActivity extends Activity {

	private ProgressDialog pDialog;
	private SlideHolder mSlideHolder;
	
	public boolean jsonNull = false;
	
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> NextGamesList;
	
	ListView list;
    LazyAdapter_nextGames_men adapter;
    LazyAdapter_noGame adapter_noGame;
	
	// url to make request
	private static String url = "http://www.blikar.is/app/nextGamesMaleJSON.php";
	
	// JSON Node names
	public static final String MEN_GAMES = "games";
	public static final String MEN_ENTRYDATE = "entryDate";
	public static final String MEN_DATE = "date";
	public static final String MEN_HOMETEAM = "homeTeam";
	public static final String MEN_AWAYTEAM = "awayTeam";
	public static final String MEN_DETAILS = "details";
	public static final String MEN_DETAILS_TOURNAMENT = "tournament";
	public static final String MEN_DETAILS_ARENA = "arena";
	public static final String MEN_DETAILS_INFO = "info";

	JSONArray nextGames = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games_main_nextmen);
		
		list=(ListView)findViewById(R.id.list);

		NextGamesList = new ArrayList<HashMap<String, String>>();
		adapter=new LazyAdapter_nextGames_men(this, NextGamesList);
		adapter_noGame=new LazyAdapter_noGame(this, NextGamesList);
		
		new LoadNextGames().execute();
		
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		
		View toggleView = findViewById(R.id.slideScreen);
		toggleView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSlideHolder.toggle();
			}
		});
		
		Button toggle = (Button) findViewById(R.id.btntoggle);	
		toggle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mSlideHolder.toggle();
				
			}
		});		
		
		Button btnResultsMen = (Button) findViewById(R.id.btnResultsMen);	
		btnResultsMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent ResultsMen = new Intent(NextGamesMenActivity.this, ResultsMenActivity.class);
						startActivity(ResultsMen);
					}
				});
		Button btnNextGamesMen = (Button) findViewById(R.id.btnNextGamesMen);	
		btnNextGamesMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesMen = new Intent(NextGamesMenActivity.this, NextGamesMenActivity.class);
						startActivity(NextGamesMen);
					}
				});
		Button btnNextGamesWomen = (Button) findViewById(R.id.btnNextGamesWomen);	
		btnNextGamesWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesWomen = new Intent(NextGamesMenActivity.this, NextGamesWomenActivity.class);
						startActivity(NextGamesWomen);
					}
				});
		Button btnResultsWomen = (Button) findViewById(R.id.btnResultsWomen);	
		btnResultsWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent ResultsWomen = new Intent(NextGamesMenActivity.this, ResultsWomenActivity.class);
						startActivity(ResultsWomen);
					}
				});
		Button btnLiveGames = (Button) findViewById(R.id.btnLiveGames);	
		btnLiveGames.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LiveGames = new Intent(NextGamesMenActivity.this, GamesNow.class);
						startActivity(LiveGames);
					}
				});	
		Button btnLeagueMen = (Button) findViewById(R.id.btnLeagueTableMen);	
		btnLeagueMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueMen = new Intent(NextGamesMenActivity.this, LeagueMenActivity.class);
						startActivity(LeagueMen);
					}
				});
		Button btnLeagueWomen = (Button) findViewById(R.id.btnLeagueTableWomen);	
		btnLeagueWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueWomen = new Intent(NextGamesMenActivity.this, LeagueWomenActivity.class);
						startActivity(LeagueWomen);
					}
				});
		
		//Layout
		Typeface font = Typeface.createFromAsset(getAssets(), "aller_rg.ttf");
		TextView header = (TextView) findViewById(R.id.NextMenHeader);
		header.setTypeface(font); 
		
		btnLiveGames.setTypeface(font);
		
		TextView karlar = (TextView) findViewById(R.id.games_men_header);
		karlar.setTypeface(font);
		
		btnLeagueMen.setTypeface(font);
		
		btnResultsMen.setTypeface(font);
		
		btnNextGamesMen.setTypeface(font);
		
		TextView konur = (TextView) findViewById(R.id.games_women_header);
		konur.setTypeface(font);
		
		btnLeagueWomen.setTypeface(font);
		
		btnResultsWomen.setTypeface(font);
		
		btnNextGamesWomen.setTypeface(font);
		
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
	
	public void onBackPressed() {
		 Intent back = new Intent(NextGamesMenActivity.this, MainScreenActivity.class);
			startActivity(back);
	}  
	
	class LoadNextGames extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NextGamesMenActivity.this);
			pDialog.setMessage("Sæki leiki");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		protected String doInBackground(String... args) {
			JSONObject json = jParser.getJSONFromUrl(url);
			if (json == null) {
				jsonNull = true;
				HashMap<String, String> map = new HashMap<String, String>();
							
				NextGamesList.add(map);
			} else {
				try {
					nextGames = json.getJSONArray(MEN_GAMES);
				
					for(int i = 0; i < nextGames.length(); i++){
						JSONObject c = nextGames.getJSONObject(i);
					
						// Storing each json item in variable
						String entryDate = c.getString(MEN_ENTRYDATE);
						String date = c.getString(MEN_DATE);
						String homeTeam = c.getString(MEN_HOMETEAM);
						String awayTeam = c.getString(MEN_AWAYTEAM);
					
						JSONObject details = c.getJSONObject(MEN_DETAILS);
						String tournament = details.getString(MEN_DETAILS_TOURNAMENT);
						String arena = details.getString(MEN_DETAILS_ARENA);
						String info = details.getString(MEN_DETAILS_INFO);
					
						HashMap<String, String> map = new HashMap<String, String>();
					
						// Dummygames are glitches in the blikar.is database
						String DummyGame1 = "1398980394";
						String DummyGame2 = "1396551642";
						String DummyGame3 = "1280020544";
						String DummyGame4 = "1386426611";
						
						
						if (!DummyGame1.equals(entryDate) && !DummyGame2.equals(entryDate) && !DummyGame3.equals(entryDate) && !DummyGame4.equals(entryDate)) {
							// adding each child node to HashMap key => value
							map.put(MEN_DATE, date);
							map.put(MEN_HOMETEAM, homeTeam);
							map.put(MEN_AWAYTEAM, awayTeam);
							map.put(MEN_DETAILS_TOURNAMENT, tournament);
							map.put(MEN_DETAILS_ARENA, arena);
							map.put(MEN_DETAILS_INFO, info);
	
							NextGamesList.add(map);
						}
					
					}
				} 
				catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;	
		}
		
		//After completing background task Dismiss the progress dialog
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				public void run() {
					if (jsonNull) {
						list.setAdapter(adapter_noGame);
					} else {
						list.setAdapter(adapter);
					}
				}
			});
		}	
	}
}		