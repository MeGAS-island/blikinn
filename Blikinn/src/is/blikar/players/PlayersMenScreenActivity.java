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
import android.graphics.Typeface;
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
public class PlayersMenScreenActivity extends Activity {
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> playerList_men;
 	JSONArray players_men = null;
 	ListView list;
    LazyAdapter_men adapter;
	private static String url = "http://blikar.is/app/malePlayersJSON.php";
	// JSON Node names
	static final String MEN_PLAYERS = "players_men";
	static final String MEN_ID = "id_men";
	static final String MEN_NAME = "name_men";
	static final String MEN_BIRTHDAY = "birthday_men";
	static final String MEN_PICTURE = "picture_men";
	static final String MEN_PLAYERPOSITION = "playerPosition_men";
	static final String MEN_STATS = "stats_men";
	static final String MEN_STATS_TOTALGAMES = "totalGames_men";
	static final String MEN_STATS_TOTALGOALS = "totalGoals_men";
	static final String MEN_STATS_LEAGUEGAMES = "leagueGames_men";
	static final String MEN_STATS_LEAGUEGOALS = "leagueGoals_men";
	static final String MEN_STATS_CUPGAMES = "cupGames_men";
	static final String MEN_STATS_CUPGOALS = "cupGoals_men";
	static final String MEN_STATS_EUROPEGAMES = "europeGames_men";
	static final String MEN_STATS_EUROPEGOALS = "europeGoals_men";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.players_men_screen);
		list=(ListView)findViewById(R.id.list);
		playerList_men = new ArrayList<HashMap<String, String>>();
		adapter=new LazyAdapter_men(this, playerList_men); 
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
			pDialog = new ProgressDialog(PlayersMenScreenActivity.this);
			pDialog.setMessage("Sæki leikmenn...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			JSONObject json = jParser.getJSONFromUrl(url);
			try {
				players_men = json.getJSONArray(MEN_PLAYERS);
				// loop through all Players
				for(int i = 0; i < players_men.length(); i++){
					JSONObject c = players_men.getJSONObject(i);
					String id_men = c.getString(MEN_ID);
					String name_men = c.getString(MEN_NAME);
					String birthday_men = c.getString(MEN_BIRTHDAY);
					String picture_men = c.getString(MEN_PICTURE);
					String playerPosition_men = c.getString(MEN_PLAYERPOSITION);
					JSONObject stats_men = c.getJSONObject(MEN_STATS);
					String totalGames_men = stats_men.getString(MEN_STATS_TOTALGAMES);
					String totalGoals_men = stats_men.getString(MEN_STATS_TOTALGOALS);
					String leagueGames_men = stats_men.getString(MEN_STATS_LEAGUEGAMES);
					String leagueGoals_men = stats_men.getString(MEN_STATS_LEAGUEGOALS);
					String cupGames_men = stats_men.getString(MEN_STATS_CUPGAMES);
					String cupGoals_men = stats_men.getString(MEN_STATS_CUPGOALS);
					String europeGames_men = stats_men.getString(MEN_STATS_EUROPEGAMES);
					String europeGoals_men = stats_men.getString(MEN_STATS_EUROPEGOALS);

					HashMap<String, String> map_men = new HashMap<String, String>();
					
					map_men.put(MEN_ID, id_men);
					map_men.put(MEN_NAME, name_men);
					map_men.put(MEN_BIRTHDAY, birthday_men);
					map_men.put(MEN_PICTURE, picture_men);
					map_men.put(MEN_PLAYERPOSITION, playerPosition_men);
					map_men.put(MEN_STATS_TOTALGAMES, totalGames_men);
					map_men.put(MEN_STATS_TOTALGOALS, totalGoals_men);
					map_men.put(MEN_STATS_LEAGUEGAMES, leagueGames_men);
					map_men.put(MEN_STATS_LEAGUEGOALS, leagueGoals_men);
					map_men.put(MEN_STATS_CUPGAMES, cupGames_men);
					map_men.put(MEN_STATS_CUPGOALS, cupGoals_men);
					map_men.put(MEN_STATS_EUROPEGAMES, europeGames_men);
					map_men.put(MEN_STATS_EUROPEGOALS, europeGoals_men);

					playerList_men.add(map_men);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
	        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String varName_men = ((TextView) view.findViewById(R.id.name_men)).getText().toString();
				String varBirthday_men = ((TextView) view.findViewById(R.id.birthday_men)).getText().toString();
				String varPictureText_men = ((TextView) view.findViewById(R.id.pictureText_men)).getText().toString();
				String varPlayerPosition_men = ((TextView) view.findViewById(R.id.playerPosition_men)).getText().toString();
				String varTotalGames_men = ((TextView) view.findViewById(R.id.totalGames_men)).getText().toString();
				String varTotalGoals_men = ((TextView) view.findViewById(R.id.totalGoals_men)).getText().toString();
				String varLeagueGames_men = ((TextView) view.findViewById(R.id.leagueGames_men)).getText().toString();
				String varLeagueGoals_men = ((TextView) view.findViewById(R.id.leagueGoals_men)).getText().toString();
				String varCupGames_men = ((TextView) view.findViewById(R.id.cupGames_men)).getText().toString();
				String varCupGoals_men = ((TextView) view.findViewById(R.id.cupGoals_men)).getText().toString();
				String varEuropeGames_men = ((TextView) view.findViewById(R.id.europeGames_men)).getText().toString();
				String varEuropeGoals_men = ((TextView) view.findViewById(R.id.europeGoals_men)).getText().toString();					
					
				Intent in = new Intent(getApplicationContext(), SinglePlayerActivity_men.class);
				in.putExtra(MEN_NAME, varName_men);
				in.putExtra(MEN_BIRTHDAY, varBirthday_men);
				in.putExtra(MEN_PICTURE, varPictureText_men);
				in.putExtra(MEN_PLAYERPOSITION, varPlayerPosition_men);
				in.putExtra(MEN_STATS_TOTALGAMES, varTotalGames_men);
				in.putExtra(MEN_STATS_TOTALGOALS, varTotalGoals_men);
				in.putExtra(MEN_STATS_LEAGUEGAMES, varLeagueGames_men);
				in.putExtra(MEN_STATS_LEAGUEGOALS, varLeagueGoals_men);
				in.putExtra(MEN_STATS_CUPGAMES, varCupGames_men);
				in.putExtra(MEN_STATS_CUPGOALS, varCupGoals_men);
				in.putExtra(MEN_STATS_EUROPEGAMES, varEuropeGames_men);
				in.putExtra(MEN_STATS_EUROPEGOALS, varEuropeGoals_men);
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