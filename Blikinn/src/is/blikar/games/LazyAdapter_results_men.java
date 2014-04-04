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
 * @since 16.11.13
 * Description: This class displays games results from men
 *
 */
public class LazyAdapter_results_men extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    
    public LazyAdapter_results_men(Activity a, ArrayList<HashMap<String, String>> d) {
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
            vi = inflater.inflate(R.layout.results_men, null);
        
        TextView date = (TextView)vi.findViewById(R.id.dateResults_men);
        TextView homeTeam = (TextView)vi.findViewById(R.id.hometeamResults_men);
        TextView awayTeam = (TextView)vi.findViewById(R.id.awayteamResults_men);
        TextView homeScore = (TextView)vi.findViewById(R.id.homescoreResults_men);
        TextView awayScore = (TextView)vi.findViewById(R.id.awayscoreResults_men);
        TextView tournament = (TextView)vi.findViewById(R.id.tournamentResults_men);
        TextView arena = (TextView)vi.findViewById(R.id.arenaResults_men);
        TextView info = (TextView)vi.findViewById(R.id.infoResults_men);
        
        HashMap<String, String> games = new HashMap<String, String>();
        games = data.get(position);
        
        // Setting all values in listview
        date.setText(games.get(ResultsMenActivity.MEN_DATE));
        homeTeam.setText(games.get(ResultsMenActivity.MEN_HOMETEAM));
        awayTeam.setText(games.get(ResultsMenActivity.MEN_AWAYTEAM));
        homeScore.setText(games.get(ResultsMenActivity.MEN_DETAILS_HOMESCORE));
        awayScore.setText(games.get(ResultsMenActivity.MEN_DETAILS_AWAYSCORE));
        tournament.setText(games.get(ResultsMenActivity.MEN_DETAILS_TOURNAMENT));
        arena.setText(games.get(ResultsMenActivity.MEN_DETAILS_ARENA));
        info.setText(games.get(ResultsMenActivity.MEN_DETAILS_INFO));
        
        return vi;
    }
}