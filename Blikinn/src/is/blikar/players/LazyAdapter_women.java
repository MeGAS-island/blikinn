package is.blikar.players;

import is.blikar.blikinn.R;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.ImageLoader;
/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: This class displays female players in listview
 *
 */
public class LazyAdapter_women extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    ImageLoader imageLoader = ImageLoader.getInstance(); 
    DisplayImageOptions options; 
    
    public LazyAdapter_women(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(20))
		.build();
    }

    public int getCount() {
        return data.size();
    }
    
    private class ViewHolder {
		public TextView name_women, birthday_women, totalGames_women,
		totalGoals_women, leagueGames_women, leagueGoals_women,
		cupGames_women, cupGoals_women, europeGames_women,
		europeGoals_women, pictureText_women;
		public ImageView picture_women;
	}

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	View view = convertView;
		final ViewHolder holder;
		if (convertView == null) {
			view = inflater.inflate(R.layout.players_list_women, parent, false);
			holder = new ViewHolder();			
			holder.name_women = (TextView) view.findViewById(R.id.name_women);
			holder.birthday_women = (TextView) view.findViewById(R.id.birthday_women);
			holder.picture_women =(ImageView) view.findViewById(R.id.picture_women);
			holder.totalGames_women = (TextView) view.findViewById(R.id.totalGames_women);
			holder.totalGoals_women = (TextView) view.findViewById(R.id.totalGoals_women);
			holder.leagueGames_women = (TextView) view.findViewById(R.id.leagueGames_women);
			holder.leagueGoals_women = (TextView) view.findViewById(R.id.leagueGoals_women);
			holder.cupGames_women = (TextView) view.findViewById(R.id.cupGames_women);
			holder.cupGoals_women = (TextView) view.findViewById(R.id.cupGoals_women);
			holder.europeGames_women = (TextView) view.findViewById(R.id.europeGames_women);
			holder.europeGoals_women = (TextView) view.findViewById(R.id.europeGoals_women);
			holder.pictureText_women = (TextView) view.findViewById(R.id.pictureText_women);
			view.setTag(holder);
		} else 	holder = (ViewHolder) view.getTag();
        HashMap<String, String> player_women = new HashMap<String, String>();
        player_women = data.get(position);
        // Setting all values in listview
        holder.name_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_NAME));
        holder.birthday_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_BIRTHDAY));
        imageLoader.displayImage(player_women.get(PlayersWomenScreenActivity.WOMEN_PICTURE), holder.picture_women, options);
        holder.totalGames_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_STATS_TOTALGAMES));
        holder.totalGoals_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_STATS_TOTALGOALS));
        holder.leagueGames_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_STATS_LEAGUEGAMES));
        holder.leagueGoals_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_STATS_LEAGUEGOALS));
        holder.cupGames_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_STATS_CUPGAMES));
        holder.cupGoals_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_STATS_CUPGOALS));
        holder.europeGames_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_STATS_EUROPEGAMES));
        holder.europeGoals_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_STATS_EUROPEGOALS));
        holder.pictureText_women.setText(player_women.get(PlayersWomenScreenActivity.WOMEN_PICTURE));
        return view;
    }
}