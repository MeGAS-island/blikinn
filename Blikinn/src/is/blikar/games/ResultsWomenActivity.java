package is.blikar.games;

import is.blikar.utils.JSONParser;
import is.blikar.utils.SlideHolder;
import is.blikar.blikinn.MainScreenActivity;
import is.blikar.blikinn.R;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
/**
 * 
 * @author Snorri Hannesson
 * @since 19.11.13
 * Description: This class passes strings to listview adapter and creates buttons for slider
 *
 */
public class ResultsWomenActivity extends Activity {

	private ProgressDialog pDialog;
	private SlideHolder mSlideHolder;
	
	JSONParser jParser = new JSONParser();
	
	ArrayList<HashMap<String, String>> ResultsList;
	
	ListView list;
    LazyAdapter_results_women adapter;
    
	private static String url = "http://blikar.is/app/resultsFemaleJSON.php";
	
	// JSON Node names
	public static final String WOMEN_GAMES = "games";
	public static final String WOMEN_ENTRYDATE = "entryDate";
	public static final String WOMEN_DATE = "date";
	public static final String WOMEN_HOMETEAM = "homeTeam";
	public static final String WOMEN_AWAYTEAM = "awayTeam";
	public static final String WOMEN_DETAILS_HOMESCORE = "homeScore";
	public static final String WOMEN_DETAILS_AWAYSCORE = "awayScore";	
	public static final String WOMEN_DETAILS = "details";
	public static final String WOMEN_DETAILS_TOURNAMENT = "tournament";
	public static final String WOMEN_DETAILS_ARENA = "arena";
	public static final String WOMEN_DETAILS_INFO = "info";

	JSONArray results = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games_main);
		
		list=(ListView)findViewById(R.id.list);

		ResultsList = new ArrayList<HashMap<String, String>>();
		adapter=new LazyAdapter_results_women(this, ResultsList);
		
		new LoadResults().execute();
		
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		
		View toggleView = findViewById(R.id.slideScreen);
		toggleView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSlideHolder.toggle();
			}
		});
		
		Button btnNextGamesMen = (Button) findViewById(R.id.btnNextGamesMen);	
		btnNextGamesMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesMen = new Intent(ResultsWomenActivity.this, NextGamesMenActivity.class);
						startActivity(NextGamesMen);
					}
				});
		
		Button btnNextGamesWomen = (Button) findViewById(R.id.btnNextGamesWomen);	
		btnNextGamesWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesWomen = new Intent(ResultsWomenActivity.this, NextGamesWomenActivity.class);
						startActivity(NextGamesWomen);
					}
				});
		
		Button btnResultsMen = (Button) findViewById(R.id.btnResultsMen);	
		btnResultsMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent ResultsMen = new Intent(ResultsWomenActivity.this, ResultsMenActivity.class);
						startActivity(ResultsMen);
					}
				});
		
		Button btnResultsWomen = (Button) findViewById(R.id.btnResultsWomen);	
		btnResultsWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent ResultsWomen = new Intent(ResultsWomenActivity.this, ResultsWomenActivity.class);
						startActivity(ResultsWomen);
					}
				});
		
		Button btnLiveGames = (Button) findViewById(R.id.btnLiveGames);	
		btnLiveGames.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LiveGames = new Intent(ResultsWomenActivity.this, GamesNow.class);
						startActivity(LiveGames);
					}
				});	
		
		Button btnLeagueMen = (Button) findViewById(R.id.btnLeagueTableMen);	
		btnLeagueMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueMen = new Intent(ResultsWomenActivity.this, LeagueMenActivity.class);
						startActivity(LeagueMen);
					}
				});	
		
		Button btnLeagueWomen = (Button) findViewById(R.id.btnLeagueTableWomen);	
		btnLeagueWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueWomen = new Intent(ResultsWomenActivity.this, LeagueWomenActivity.class);
						startActivity(LeagueWomen);
					}
				});	
		
		}
	
	 public void onBackPressed() {
		 Intent back = new Intent(ResultsWomenActivity.this, MainScreenActivity.class);
			startActivity(back);
     }  
	
	class LoadResults extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ResultsWomenActivity.this);
			pDialog.setMessage("Sæki leiki...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		protected String doInBackground(String... args) {

		JSONObject json = jParser.getJSONFromUrl(url);
		

		try {
			results = json.getJSONArray(WOMEN_GAMES);
			
			for(int i = 0; i < results.length(); i++){
				JSONObject c = results.getJSONObject(i);
				
				// Storing each json item in variable
				String entryDate = c.getString(WOMEN_ENTRYDATE);
				String date = c.getString(WOMEN_DATE);
				String homeTeam = c.getString(WOMEN_HOMETEAM);
				String awayTeam = c.getString(WOMEN_AWAYTEAM);
				
				JSONObject details = c.getJSONObject(WOMEN_DETAILS);
				String homeScore = details.getString(WOMEN_DETAILS_HOMESCORE);
				String awayScore = details.getString(WOMEN_DETAILS_AWAYSCORE);
				String tournament = details.getString(WOMEN_DETAILS_TOURNAMENT);
				String arena = details.getString(WOMEN_DETAILS_ARENA);
				String info = details.getString(WOMEN_DETAILS_INFO);

				HashMap<String, String> map = new HashMap<String, String>();
				
				// 1280020544 is the entryDate of a dummy game that is in the json string if there are no games
				String DummygameWomen = "1280020544";
				if (DummygameWomen.equals(entryDate)) {
					date = "";
					homeTeam = "Enginn leikur";
					awayTeam = "";
					tournament = "";
					arena = "";
					info = "";
				}
				
				// adding each child node to HashMap key => value
				map.put(WOMEN_DATE, date);
				map.put(WOMEN_HOMETEAM, homeTeam);
				map.put(WOMEN_AWAYTEAM, awayTeam);
				map.put(WOMEN_DETAILS_HOMESCORE, homeScore);
				map.put(WOMEN_DETAILS_AWAYSCORE, awayScore);
				map.put(WOMEN_DETAILS_TOURNAMENT, tournament);
				map.put(WOMEN_DETAILS_ARENA, arena);
				map.put(WOMEN_DETAILS_INFO, info);

				ResultsList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;		
	}
		
		//After completing background task Dismiss the progress dialog
		protected void onPostExecute(String file_url) {
			
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				
				public void run() {
					list.setAdapter(adapter);
				}
			});
		}		
	}
}	