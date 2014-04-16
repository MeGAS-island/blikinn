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
 * @author Aðasteinn Stefánsson
 * @since 6.11.13
 * Description: list Adapter for the womens league
 *
 */
public class LazyAdapter_leagueWomen extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    
    public LazyAdapter_leagueWomen(Activity a, ArrayList<HashMap<String, String>> d) {
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
            vi = inflater.inflate(R.layout.league_table_women, null);
        
        TextView tvPos = (TextView)vi.findViewById(R.id.tvPos);
        TextView tvTeam = (TextView)vi.findViewById(R.id.tvTeam);
        TextView tvGames = (TextView)vi.findViewById(R.id.tvGames);
        TextView tvWins = (TextView)vi.findViewById(R.id.tvWins);
        TextView tvDraws = (TextView)vi.findViewById(R.id.tvDraws);
        TextView tvLosses = (TextView)vi.findViewById(R.id.tvLosses);
        TextView tvGoals = (TextView)vi.findViewById(R.id.tvGoals);
        TextView tvPoints = (TextView)vi.findViewById(R.id.tvPoints);
        
        HashMap<String, String> games = new HashMap<String, String>();
        games = data.get(position);
        
        // Setting all values in listview
        tvPos.setText(games.get(LeagueWomenActivity.TVPOS));
        tvTeam.setText(games.get(LeagueWomenActivity.TVTEAM));
        tvGames.setText(games.get(LeagueWomenActivity.TVGAMES));
        tvWins.setText(games.get(LeagueWomenActivity.TVWINS));
        tvDraws.setText(games.get(LeagueWomenActivity.TVDRAWS));
        tvLosses.setText(games.get(LeagueWomenActivity.TVLOSSES));
        tvGoals.setText(games.get(LeagueWomenActivity.TVGOALS));
        tvPoints.setText(games.get(LeagueWomenActivity.TVPOINTS));
                
        return vi;
    }
}