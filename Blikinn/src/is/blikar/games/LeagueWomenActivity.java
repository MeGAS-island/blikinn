package is.blikar.games;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import is.blikar.blikinn.MainScreenActivity;
import is.blikar.blikinn.R;

import is.blikar.utils.SlideHolder;

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
 * @author Aðalsteinn Stefánsson
 * @since 22.11.13
 * Description: Displays league table in TextView
 *
 */
public class LeagueWomenActivity extends Activity {
	private SlideHolder mSlideHolder;
	
	ArrayList<HashMap<String, String>> LeagueWomenList;
	LazyAdapter_leagueWomen adapter;
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
		setContentView(R.layout.games_main_leaguewomen);
		
		list=(ListView)findViewById(R.id.list);
		
		LeagueWomenList = new ArrayList<HashMap<String, String>>();
		adapter=new LazyAdapter_leagueWomen(this, LeagueWomenList);
		
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
		TextView header = (TextView) findViewById(R.id.LeagueWomenHeader);
		header.setTypeface(font); 		
		
		Button btnResultsMen = (Button) findViewById(R.id.btnResultsMen);	
		btnResultsMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent ResultsMen = new Intent(LeagueWomenActivity.this, ResultsMenActivity.class);
						startActivity(ResultsMen);
					}
				});
		Button btnNextGamesMen = (Button) findViewById(R.id.btnNextGamesMen);	
		btnNextGamesMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesMen = new Intent(LeagueWomenActivity.this, NextGamesMenActivity.class);
						startActivity(NextGamesMen);
					}
				});
		Button btnNextGamesWomen = (Button) findViewById(R.id.btnNextGamesWomen);	
		btnNextGamesWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesWomen = new Intent(LeagueWomenActivity.this, NextGamesWomenActivity.class);
						startActivity(NextGamesWomen);
					}
				});
		Button btnResultsWomen = (Button) findViewById(R.id.btnResultsWomen);	
		btnResultsWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent ResultsWomen = new Intent(LeagueWomenActivity.this, ResultsWomenActivity.class);
						startActivity(ResultsWomen);
					}
				});	
		Button btnLiveGames = (Button) findViewById(R.id.btnLiveGames);	
		btnLiveGames.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LiveGames = new Intent(LeagueWomenActivity.this, GamesNow.class);
						startActivity(LiveGames);
					}
				});
		Button btnLeagueMen = (Button) findViewById(R.id.btnLeagueTableMen);	
		btnLeagueMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueMen = new Intent(LeagueWomenActivity.this, LeagueMenActivity.class);
						startActivity(LeagueMen);
					}
				});	
		Button btnLeagueWomen = (Button) findViewById(R.id.btnLeagueTableWomen);	
		btnLeagueWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueWomen = new Intent(LeagueWomenActivity.this, LeagueWomenActivity.class);
						startActivity(LeagueWomen);
					}
				});	
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
		 Intent back = new Intent(LeagueWomenActivity.this, MainScreenActivity.class);
			startActivity(back);
   }  
	
	class LeaguePosAsync extends AsyncTask<Void, Void, Void> {
		ProgressDialog pd;
		XMLParseWomenLeague helper;	
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(LeagueWomenActivity.this);
			pd.setMessage("Sæki deildarstöðu kvenna");
			pd.setIndeterminate(false);
			pd.setCancelable(false);
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			helper = new XMLParseWomenLeague();
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
			LeagueWomenList.add(map);
		}
	}	
}