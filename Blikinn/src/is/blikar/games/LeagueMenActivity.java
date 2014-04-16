package is.blikar.games;

import java.util.ArrayList;
import java.util.HashMap;

import is.blikar.blikinn.MainScreenActivity;
import is.blikar.blikinn.R;

import is.blikar.utils.SlideHolder;

import android.app.Activity;
import android.app.ProgressDialog;
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
 * @author Aðalsteinn Stefánsson
 * @since 22.11.13
 * Description: Displays league table in TextView
 *
 */
public class LeagueMenActivity extends Activity {
	private SlideHolder mSlideHolder;
	ArrayList<HashMap<String, String>> LeagueMenList;
	LazyAdapter_leagueMen adapter;
	ListView list;
	
	public final static String TVPOS = "pos";
	public final static String TVTEAM = "team";
	public final static String TVGAMES = "games";
	public final static String TVWINS = "wins";
	public final static String TVDRAWS = "draws";
	public final static String TVLOSSES = "losses";
	public final static String TVGOALS = "goals";
	public final static String TVPOINTS = "ponts";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games_main_leaguemen);
		
		list=(ListView)findViewById(R.id.list);
		
		LeagueMenList = new ArrayList<HashMap<String, String>>();
		adapter=new LazyAdapter_leagueMen(this, LeagueMenList);
		
		new LeaguePosAsync().execute();
	
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
		
		//Layout
		Typeface font = Typeface.createFromAsset(getAssets(), "aller_rg.ttf");
		TextView header = (TextView) findViewById(R.id.LeagueMenHeader);
		header.setTypeface(font); 		
		
		Button btnResultsMen = (Button) findViewById(R.id.btnResultsMen);	
		btnResultsMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent ResultsMen = new Intent(LeagueMenActivity.this, ResultsMenActivity.class);
						startActivity(ResultsMen);
					}
				});
		Button btnNextGamesMen = (Button) findViewById(R.id.btnNextGamesMen);	
		btnNextGamesMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesMen = new Intent(LeagueMenActivity.this, NextGamesMenActivity.class);
						startActivity(NextGamesMen);
					}
				});
		Button btnNextGamesWomen = (Button) findViewById(R.id.btnNextGamesWomen);	
		btnNextGamesWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesWomen = new Intent(LeagueMenActivity.this, NextGamesWomenActivity.class);
						startActivity(NextGamesWomen);
					}
				});
		Button btnResultsWomen = (Button) findViewById(R.id.btnResultsWomen);	
		btnResultsWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent ResultsWomen = new Intent(LeagueMenActivity.this, ResultsWomenActivity.class);
						startActivity(ResultsWomen);
					}
				});	
		Button btnLiveGames = (Button) findViewById(R.id.btnLiveGames);	
		btnLiveGames.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LiveGames = new Intent(LeagueMenActivity.this, GamesNow.class);
						startActivity(LiveGames);
					}
				});
		Button btnLeagueMen = (Button) findViewById(R.id.btnLeagueTableMen);	
		btnLeagueMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueMen = new Intent(LeagueMenActivity.this, LeagueMenActivity.class);
						startActivity(LeagueMen);
					}
				});	
		Button btnLeagueWomen = (Button) findViewById(R.id.btnLeagueTableWomen);	
		btnLeagueWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueWomen = new Intent(LeagueMenActivity.this, LeagueWomenActivity.class);
						startActivity(LeagueWomen);
					}
				});	
	}
	
	public void onBackPressed() {
		 Intent back = new Intent(LeagueMenActivity.this, MainScreenActivity.class);
			startActivity(back);
    }  
	
	class LeaguePosAsync extends AsyncTask<Void, Void, Void> {
		ProgressDialog pd;
		XMLParseMenLeague helper;
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(LeagueMenActivity.this);
			pd.setMessage("Sæki deildarstöðu karla...");
			pd.setIndeterminate(false);
			pd.setCancelable(false);
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			helper = new XMLParseMenLeague();
			helper.get();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			
			StringBuilder pos = new StringBuilder();
			for(LeaguePosValue leaguePos : helper.leaguePositions) {
				pos.append(leaguePos.getPosition() + ".");																		
				pos.append("\n\n");
			}
			String tvPos = pos.toString();
			
			StringBuilder team = new StringBuilder();
			for(LeaguePosValue leaguePos : helper.leaguePositions) {
				team.append(leaguePos.getTeam());																			
				team.append("\n\n");
			}
			String tvTeam = team.toString();
			
			StringBuilder games = new StringBuilder();
			for(LeaguePosValue leaguePos : helper.leaguePositions) {
				games.append(leaguePos.getGames());																			
				games.append("\n\n");
			}
			String tvGames = games.toString();
			
			StringBuilder win = new StringBuilder();
			for(LeaguePosValue leaguePos : helper.leaguePositions) {
				win.append(leaguePos.getWins());																			
				win.append("\n\n");
			}
			String tvWins = win.toString();
			
			StringBuilder draw = new StringBuilder();
			for(LeaguePosValue leaguePos : helper.leaguePositions) {
				draw.append(leaguePos.getDraws());																			
				draw.append("\n\n");
			}
			String tvDraws = draw.toString();
			
			StringBuilder loss = new StringBuilder();
			for(LeaguePosValue leaguePos : helper.leaguePositions) {
				loss.append(leaguePos.getLosses());																			
				loss.append("\n\n");
			}
			String tvLosses = loss.toString();
			
			StringBuilder goals = new StringBuilder();
			for(LeaguePosValue leaguePos : helper.leaguePositions) {
				goals.append(leaguePos.getGoalsFor());
				goals.append(":");
				goals.append(leaguePos.getGoalsAgainst());
				goals.append("\n\n");
			}
			String tvGoals = goals.toString();
			
			
			
			StringBuilder points = new StringBuilder();			
			for(LeaguePosValue leaguePos : helper.leaguePositions) {
				points.append(leaguePos.getPoints());																				
				points.append("\n\n");
			}
			String tvPoints = points.toString();
			
			pd.dismiss();
			
			runOnUiThread(new Runnable() {
				public void run() {
				
				list.setAdapter(adapter);
				}
			});
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(TVPOS, tvPos);
			map.put(TVTEAM, tvTeam);
			map.put(TVGAMES, tvGames);
			map.put(TVWINS, tvWins);
			map.put(TVDRAWS, tvDraws);
			map.put(TVLOSSES, tvLosses);
			map.put(TVGOALS, tvGoals);
			map.put(TVPOINTS, tvPoints);
			LeagueMenList.add(map);
		}	
	}	
}