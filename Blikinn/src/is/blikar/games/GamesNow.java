package is.blikar.games;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import is.blikar.utils.SlideHolder;
import is.blikar.blikinn.MainScreenActivity;
import is.blikar.blikinn.R;


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
 * @author Guðný Lára Guðmundsdóttir
 * @since 6.11.13
 * Description: Gets info from xml file and passes strings to listview adapter
 *
 */
public class GamesNow extends Activity {
	private SlideHolder mSlideHolder;
	
	ArrayList<HashMap<String, String>> LiveGamesList;
	LazyAdapter_LiveGames adapter;
	LazyAdapter_noLiveGame adapter_noLiveGame;
	ListView list;
	
	public boolean nogame = false;
	
	public final static String MOT = "mot";
	public final static String HEIMALID = "heimalid";
	public final static String HEIMASKOR = "heimaskor";
	public final static String UTISKOR = "utiskor";
	public final static String UTILID = "utilid";
	public final static String NYTTMARK = "nyttmark";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games_main_live);
		
		list=(ListView)findViewById(R.id.list);
		LiveGamesList = new ArrayList<HashMap<String, String>>();
		adapter=new LazyAdapter_LiveGames(this, LiveGamesList);
		adapter_noLiveGame=new LazyAdapter_noLiveGame(this, LiveGamesList);
		
		new PostAsync().execute();
		
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
				Intent ResultsMen = new Intent(GamesNow.this, ResultsMenActivity.class);
						startActivity(ResultsMen);
					}
				});
		Button btnNextGamesMen = (Button) findViewById(R.id.btnNextGamesMen);	
		btnNextGamesMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesMen = new Intent(GamesNow.this, NextGamesMenActivity.class);
						startActivity(NextGamesMen);
					}
				});
		Button btnNextGamesWomen = (Button) findViewById(R.id.btnNextGamesWomen);	
		btnNextGamesWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NextGamesWomen = new Intent(GamesNow.this, NextGamesWomenActivity.class);
						startActivity(NextGamesWomen);
					}
				});
		Button btnResultsWomen = (Button) findViewById(R.id.btnResultsWomen);	
		btnResultsWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent ResultsWomen = new Intent(GamesNow.this, ResultsWomenActivity.class);
						startActivity(ResultsWomen);
					}
				});	
		Button btnLiveGames = (Button) findViewById(R.id.btnLiveGames);	
		btnLiveGames.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LiveGames = new Intent(GamesNow.this, GamesNow.class);
						startActivity(LiveGames);
					}
				});	
		Button btnLeagueMen = (Button) findViewById(R.id.btnLeagueTableMen);	
		btnLeagueMen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueMen = new Intent(GamesNow.this, LeagueMenActivity.class);
						startActivity(LeagueMen);
					}
				});
		Button btnLeagueWomen = (Button) findViewById(R.id.btnLeagueTableWomen);	
		btnLeagueWomen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent LeagueWomen = new Intent(GamesNow.this, LeagueWomenActivity.class);
						startActivity(LeagueWomen);
					}
				});
		
		//Layout
		Typeface font = Typeface.createFromAsset(getAssets(), "aller_rg.ttf");
		
		TextView header = (TextView) findViewById(R.id.LiveHeader);
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
		 Intent back = new Intent(GamesNow.this, MainScreenActivity.class);
			startActivity(back);
   }  
	
	class PostAsync extends AsyncTask<Void, Void, Void> {
		ProgressDialog pd;
		XMLParser helper;	
		
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(GamesNow.this);
			pd.setMessage("Sæki leiki í gangi");
			pd.setIndeterminate(false);
			pd.setCancelable(false);
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			helper = new XMLParser();
			helper.get();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			int meet=0;
			int leikir=0;
			StringBuilder builder = new StringBuilder();
			StringBuilder tournament = new StringBuilder();
			StringBuilder homeTeam = new StringBuilder();
			StringBuilder homeGoal = new StringBuilder();
			StringBuilder awayGoal = new StringBuilder();
			StringBuilder awayTeam = new StringBuilder();
			StringBuilder newGoal = new StringBuilder();
			for (MotValue mot: helper.mots){
				if(mot.getGrein().equals("Knattspyrna")){
					if(mot.getKyn().equals("Karlar")){
						meet=mot.getMeet();
					}
					if(mot.getKyn().equals("Konur")){
						meet=mot.getMeet();
					}
				for(LeikirValue game : helper.leikir) {
					if(game.getMeet()==meet&&game.getDone()==false&&game.getStada().equals("ÍGangi")){
						if(game.getHeimalid().equals("Breiðablik")||game.getUtilid().equals("Breiðablik")){
							if(mot.getKyn().equals("Karlar")){
								builder.append("\n "+mot.getMot()+" karla\n");
								tournament.append(mot.getMot()+" karla");
							}
							if(mot.getKyn().equals("Konur")){
								builder.append("\n "+mot.getMot()+" kvenna\n");
								tournament.append(mot.getMot()+" karla");
							}
							builder.append("\n"+game.getHeimalid() + " ");
							homeTeam.append(game.getHeimalid());
							
							builder.append(game.getMarkHeima() + " - ");
							homeGoal.append(game.getMarkHeima());
							
							builder.append(game.getMarkUti());
							awayGoal.append(game.getMarkUti());
							
							builder.append(" "+game.getUtilid());
							awayTeam.append(game.getUtilid());
							
							if(game.getNyjastaMarkHeima()!=""  && game.getNyjastaMarkHeima()!=null){
								builder.append("Nýjasta mark " + game.getHeimalid() + " var skorað af " + game.getNyjastaMarkHeima() + "\n");
								newGoal.append("Nýjasta mark " + game.getHeimalid() + " var skorað af " + game.getNyjastaMarkHeima() + "\n");
							}
							if(game.getNyjastaMarkUti()!="" && game.getNyjastaMarkUti()!=""){
								builder.append("Nýjasta mark " + game.getUtilid() + " var skorað af " + game.getNyjastaMarkUti() + "\n");
								newGoal.append("Nýjasta mark " + game.getUtilid() + " var skorað af " + game.getNyjastaMarkUti() + "\n");
							}
							game.setDone(true);	
							leikir++;
						}
					}
				}
				}
			}
			
			String gamesresponse = builder.toString();
			if(gamesresponse == "" || gamesresponse == null || leikir==0){
				nogame = true;
			}
			
			pd.dismiss();
			runOnUiThread(new Runnable() {
				public void run() {	
					if (nogame) {
						list.setAdapter(adapter_noLiveGame);
					} else {
						list.setAdapter(adapter);
					}
				}
			});
			
			String mot = tournament.toString();
			String heimalid = homeTeam.toString();
			String heimaskor = homeGoal.toString();
			String utiskor = awayGoal.toString();
			String utilid = awayTeam.toString();
			String nyttmark = newGoal.toString();
			
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(MOT, mot);
			map.put(HEIMALID, heimalid);
			map.put(HEIMASKOR, heimaskor);
			map.put(UTISKOR, utiskor);
			map.put(UTILID, utilid);
			map.put(NYTTMARK, nyttmark);
			LiveGamesList.add(map);
		}
	}
}