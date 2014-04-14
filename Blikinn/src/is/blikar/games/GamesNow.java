package is.blikar.games;


import java.util.ArrayList;
import java.util.HashMap;

import is.blikar.utils.SlideHolder;
import is.blikar.blikinn.MainScreenActivity;
import is.blikar.blikinn.R;


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
 * @author Guðný Lára Guðmundsdóttir
 * @since 6.11.13
 * Description: Gets info from xml file and passes strings to listview adapter
 *
 */
public class GamesNow extends Activity {
	private SlideHolder mSlideHolder;
	
	ArrayList<HashMap<String, String>> LiveGamesList;
	LazyAdapter_LiveGames adapter;
	ListView list;
	
	public final static String LEIKUR = "leikur";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games_main);
		
		list=(ListView)findViewById(R.id.list);
		LiveGamesList = new ArrayList<HashMap<String, String>>();
		adapter=new LazyAdapter_LiveGames(this, LiveGamesList);
		
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
		
		//Layout
		Typeface font = Typeface.createFromAsset(getAssets(), "aller_rg.ttf");
		TextView header = (TextView) findViewById(R.id.LeikirHeader);
		header.setTypeface(font);   
		
		
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
			pd.setMessage("Sæki leiki í gangi...");
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
			StringBuilder builder = new StringBuilder();
			for (MotValue mot: helper.mots){
				if(mot.getGrein().equals("Knattspyrna")){
					if(mot.getKyn().equals("Karlar")){
						builder.append("\n "+mot.getMot()+" karla\n");
						meet=mot.getMeet();
					}
					if(mot.getKyn().equals("Konur")){
						builder.append("\n "+mot.getMot()+" kvenna\n");
						meet=mot.getMeet();
					}
				}
				for(LeikirValue game : helper.leikir) {
					if(game.getMeet()==meet&&game.getDone()==false&&game.getStada().equals("ÍGangi")){
						if(game.getHeimalid().equals("Breiðablik")||game.getUtilid().equals("Breiðablik")){
								builder.append("\n"+game.getHeimalid() + " ");
								builder.append(game.getMarkHeima() + " - ");
								builder.append(game.getMarkUti());
								builder.append(" "+game.getUtilid());
								builder.append("\n");
								builder.append("\n");
								if(game.getNyjastaMarkHeima()!=""  && game.getNyjastaMarkHeima()!=null){
									builder.append("Nýjasta mark " + game.getHeimalid() + " var skorað af " + game.getNyjastaMarkHeima() + "\n");
								}
								if(game.getNyjastaMarkUti()!="" && game.getNyjastaMarkUti()!=""){
									builder.append("Nýjasta mark " + game.getUtilid() + " var skorað af " + game.getNyjastaMarkUti() + "\n");
								}
								game.setDone(true);	
						}
					}
				}
			}	  
			
			pd.dismiss();
			runOnUiThread(new Runnable() {
				public void run() {			
					list.setAdapter(adapter);
				}
			});
			
			String gamesresponse = builder.toString();
			if(gamesresponse == "" || gamesresponse == null){
				gamesresponse = "Enginn leikur í gangi";
			}
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(LEIKUR, gamesresponse);
			LiveGamesList.add(map);
		}
	}
}