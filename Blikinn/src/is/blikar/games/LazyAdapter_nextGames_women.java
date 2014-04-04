package is.blikar.games;

import java.util.ArrayList;
import java.util.HashMap;
import is.blikar.blikinn.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: This class displays next games from women
 *
 */
public class LazyAdapter_nextGames_women extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    
    public LazyAdapter_nextGames_women(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.next_games_women, null);
        
        TextView date = (TextView)vi.findViewById(R.id.dateNextGames_women);
        TextView homeTeam = (TextView)vi.findViewById(R.id.hometeamNextGames_women);
        TextView awayTeam = (TextView)vi.findViewById(R.id.awayteamNextGames_women);
        TextView tournament = (TextView)vi.findViewById(R.id.tournamentNextGames_women);
        TextView arena = (TextView)vi.findViewById(R.id.arenaNextGames_women);
        TextView info = (TextView)vi.findViewById(R.id.infoNextGames_women);
        
        HashMap<String, String> games = new HashMap<String, String>();
        games = data.get(position);
        
        // Setting all values in listview
        date.setText(games.get(NextGamesWomenActivity.WOMEN_DATE));
        homeTeam.setText(games.get(NextGamesWomenActivity.WOMEN_HOMETEAM));
        awayTeam.setText(games.get(NextGamesWomenActivity.WOMEN_AWAYTEAM));
        tournament.setText(games.get(NextGamesWomenActivity.WOMEN_DETAILS_TOURNAMENT));
        arena.setText(games.get(NextGamesWomenActivity.WOMEN_DETAILS_ARENA));
        info.setText(games.get(NextGamesWomenActivity.WOMEN_DETAILS_INFO));
        
        return vi;
    }
}