package is.blikar.games;

import java.util.ArrayList;
import java.util.HashMap;
import is.blikar.blikinn.R;
import is.blikar.utils.SlideHolder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author Guðný Lára Guðmundsdóttir
 * @since 12.11.13
 * Description: list adapter for live games
 *
 */
public class LazyAdapter_LiveGames extends BaseAdapter {
	
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    
    public LazyAdapter_LiveGames(Activity a, ArrayList<HashMap<String, String>> d) {
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
            vi = inflater.inflate(R.layout.live_games_list, null);
        
        TextView mot = (TextView)vi.findViewById(R.id.tournamentLive);
        TextView heimalid = (TextView)vi.findViewById(R.id.hometeamLive);
        TextView heimaskor = (TextView)vi.findViewById(R.id.homescoreLive);
        TextView utiskor = (TextView)vi.findViewById(R.id.awayscoreLive);
        TextView utilid = (TextView)vi.findViewById(R.id.awayteamLive);
        TextView nyttmark = (TextView)vi.findViewById(R.id.newGoalLive);
        
        HashMap<String, String> games = new HashMap<String, String>();
        games = data.get(position);
        
        // Setting all values in listview
        mot.setText(games.get(GamesNow.MOT));
        heimalid.setText(games.get(GamesNow.HEIMALID));
        heimaskor.setText(games.get(GamesNow.HEIMASKOR));
        utiskor.setText(games.get(GamesNow.UTISKOR));
        utilid.setText(games.get(GamesNow.UTILID));
        nyttmark.setText(games.get(GamesNow.NYTTMARK));
        
        return vi;
    }
}