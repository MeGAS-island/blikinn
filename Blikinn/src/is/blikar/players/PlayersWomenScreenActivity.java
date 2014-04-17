package is.blikar.players;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: This class parsers through json string and puts data in right places
 *
 */
public class PlayersWomenScreenActivity extends Activity {
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> playerList_women;
 	JSONArray players_women = null;
 	ListView list;
    LazyAdapter_women adapter;
	private static String url = "http://blikar.is/app/femalePlayersJSON.php";
	static final String WOMEN_PLAYERS = "players_women";
	static final String WOMEN_ID = "id_women";
	static final String WOMEN_NAME = "name_women";
	static final String WOMEN_BIRTHDAY = "birthday_women";
	static final String WOMEN_PICTURE = "picture_women";
	static final String WOMEN_STATS = "stats_women";
	static final String WOMEN_STATS_TOTALGAMES = "totalGames_women";
	static final String WOMEN_STATS_TOTALGOALS = "totalGoals_women";
	static final String WOMEN_STATS_LEAGUEGAMES = "leagueGames_women";
	static final String WOMEN_STATS_LEAGUEGOALS = "leagueGoals_women";
	static final String WOMEN_STATS_CUPGAMES = "cupGames_women";
	static final String WOMEN_STATS_CUPGOALS = "cupGoals_women";
	static final String WOMEN_STATS_EUROPEGAMES = "europeGames_women";
	static final String WOMEN_STATS_EUROPEGOALS = "europeGoals_women";
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.players_women_screen);
		list=(ListView)findViewById(R.id.list);
		playerList_women = new ArrayList<HashMap<String, String>>();
		adapter=new LazyAdapter_women(this, playerList_women); 
	    new LoadPlayersMen().execute();
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

	class LoadPlayersMen extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PlayersWomenScreenActivity.this);
			pDialog.setMessage("Sæki leikmenn");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			JSONObject json = jParser.getJSONFromUrl(url);
			try {
				players_women = json.getJSONArray(WOMEN_PLAYERS);
				// loop through all Players
				for(int i = 0; i < players_women.length(); i++){
					JSONObject c = players_women.getJSONObject(i);
					String id_women = c.getString(WOMEN_ID);
					String name_women = c.getString(WOMEN_NAME);
					String birthday_women = c.getString(WOMEN_BIRTHDAY);
					String picture_women = c.getString(WOMEN_PICTURE);
					JSONObject stats_women = c.getJSONObject(WOMEN_STATS);
					String totalGames_women = stats_women.getString(WOMEN_STATS_TOTALGAMES);
					String totalGoals_women = stats_women.getString(WOMEN_STATS_TOTALGOALS);
					String leagueGames_women = stats_women.getString(WOMEN_STATS_LEAGUEGAMES);
					String leagueGoals_women = stats_women.getString(WOMEN_STATS_LEAGUEGOALS);
					String cupGames_women = stats_women.getString(WOMEN_STATS_CUPGAMES);
					String cupGoals_women = stats_women.getString(WOMEN_STATS_CUPGOALS);
					String europeGames_women = stats_women.getString(WOMEN_STATS_EUROPEGAMES);
					String europeGoals_women = stats_women.getString(WOMEN_STATS_EUROPEGOALS);
					HashMap<String, String> map_women = new HashMap<String, String>();

					map_women.put(WOMEN_ID, id_women);
					map_women.put(WOMEN_NAME, name_women);
					map_women.put(WOMEN_BIRTHDAY, birthday_women);
					map_women.put(WOMEN_PICTURE, picture_women);
					map_women.put(WOMEN_STATS_TOTALGAMES, totalGames_women);
					map_women.put(WOMEN_STATS_TOTALGOALS, totalGoals_women);
					map_women.put(WOMEN_STATS_LEAGUEGAMES, leagueGames_women);
					map_women.put(WOMEN_STATS_LEAGUEGOALS, leagueGoals_women);
					map_women.put(WOMEN_STATS_CUPGAMES, cupGames_women);
					map_women.put(WOMEN_STATS_CUPGOALS, cupGoals_women);
					map_women.put(WOMEN_STATS_EUROPEGAMES, europeGames_women);
					map_women.put(WOMEN_STATS_EUROPEGOALS, europeGoals_women);
					playerList_women.add(map_women);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
	        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String varName_women = ((TextView) view.findViewById(R.id.name_women)).getText().toString();
				String varBirthday_women = ((TextView) view.findViewById(R.id.birthday_women)).getText().toString();
				String varPictureText_women = ((TextView) view.findViewById(R.id.pictureText_women)).getText().toString();
				String varTotalGames_women = ((TextView) view.findViewById(R.id.totalGames_women)).getText().toString();
				String varTotalGoals_women = ((TextView) view.findViewById(R.id.totalGoals_women)).getText().toString();
				String varLeagueGames_women = ((TextView) view.findViewById(R.id.leagueGames_women)).getText().toString();
				String varLeagueGoals_women = ((TextView) view.findViewById(R.id.leagueGoals_women)).getText().toString();
				String varCupGames_women = ((TextView) view.findViewById(R.id.cupGames_women)).getText().toString();
				String varCupGoals_women = ((TextView) view.findViewById(R.id.cupGoals_women)).getText().toString();
				String varEuropeGames_women = ((TextView) view.findViewById(R.id.europeGames_women)).getText().toString();
				String varEuropeGoals_women = ((TextView) view.findViewById(R.id.europeGoals_women)).getText().toString();

				Intent in = new Intent(getApplicationContext(), SinglePlayerActivity_women.class);
				in.putExtra(WOMEN_NAME, varName_women);
				in.putExtra(WOMEN_BIRTHDAY, varBirthday_women);
				in.putExtra(WOMEN_PICTURE, varPictureText_women);
				in.putExtra(WOMEN_STATS_TOTALGAMES, varTotalGames_women);
				in.putExtra(WOMEN_STATS_TOTALGOALS, varTotalGoals_women);
				in.putExtra(WOMEN_STATS_LEAGUEGAMES, varLeagueGames_women);
				in.putExtra(WOMEN_STATS_LEAGUEGOALS, varLeagueGoals_women);
				in.putExtra(WOMEN_STATS_CUPGAMES, varCupGames_women);
				in.putExtra(WOMEN_STATS_CUPGOALS, varCupGoals_women);
				in.putExtra(WOMEN_STATS_EUROPEGAMES, varEuropeGames_women);
				in.putExtra(WOMEN_STATS_EUROPEGOALS, varEuropeGoals_women);
				startActivity(in);
				}			
			});
		return null;		
		}

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