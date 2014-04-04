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
 * Description: This class displays male players in listview
 *
 */
public class LazyAdapter_men extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    ImageLoader imageLoader = ImageLoader.getInstance(); 
    DisplayImageOptions options; 
    
    public LazyAdapter_men(Activity a, ArrayList<HashMap<String, String>> d) {
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
		public TextView name_men, birthday_men, playerPosition_men,
		totalGames_men, totalGoals_men, leagueGames_men, leagueGoals_men,
		cupGames_men, cupGoals_men, europeGames_men, europeGoals_men,
		pictureText_men;
		public ImageView picture_men;
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
			view = inflater.inflate(R.layout.players_list_men, parent, false);
			holder = new ViewHolder();			
			holder.name_men = (TextView) view.findViewById(R.id.name_men);
			holder.birthday_men = (TextView) view.findViewById(R.id.birthday_men);
			holder.playerPosition_men = (TextView) view.findViewById(R.id.playerPosition_men);
			holder.picture_men =(ImageView) view.findViewById(R.id.picture_men);
			holder.totalGames_men = (TextView) view.findViewById(R.id.totalGames_men);
			holder.totalGoals_men = (TextView) view.findViewById(R.id.totalGoals_men);
			holder.leagueGames_men = (TextView) view.findViewById(R.id.leagueGames_men);
			holder.leagueGoals_men = (TextView) view.findViewById(R.id.leagueGoals_men);
			holder.cupGames_men = (TextView) view.findViewById(R.id.cupGames_men);
			holder.cupGoals_men = (TextView) view.findViewById(R.id.cupGoals_men);
			holder.europeGames_men = (TextView) view.findViewById(R.id.europeGames_men);
			holder.europeGoals_men = (TextView) view.findViewById(R.id.europeGoals_men);
			holder.pictureText_men = (TextView) view.findViewById(R.id.pictureText_men);
			view.setTag(holder);
		} else holder = (ViewHolder) view.getTag();
        HashMap<String, String> player_men = new HashMap<String, String>();
        player_men = data.get(position);
        // Setting all values in listview
        holder.name_men.setText(player_men.get(PlayersMenScreenActivity.MEN_NAME));
        holder.birthday_men.setText(player_men.get(PlayersMenScreenActivity.MEN_BIRTHDAY));
        imageLoader.displayImage(player_men.get(PlayersMenScreenActivity.MEN_PICTURE), holder.picture_men, options);
        holder.playerPosition_men.setText(player_men.get(PlayersMenScreenActivity.MEN_PLAYERPOSITION));
        holder.totalGames_men.setText(player_men.get(PlayersMenScreenActivity.MEN_STATS_TOTALGAMES));
        holder.totalGoals_men.setText(player_men.get(PlayersMenScreenActivity.MEN_STATS_TOTALGOALS));
        holder.leagueGames_men.setText(player_men.get(PlayersMenScreenActivity.MEN_STATS_LEAGUEGAMES));
        holder.leagueGoals_men.setText(player_men.get(PlayersMenScreenActivity.MEN_STATS_LEAGUEGOALS));
        holder.cupGames_men.setText(player_men.get(PlayersMenScreenActivity.MEN_STATS_CUPGAMES));
        holder.cupGoals_men.setText(player_men.get(PlayersMenScreenActivity.MEN_STATS_CUPGOALS));
        holder.europeGames_men.setText(player_men.get(PlayersMenScreenActivity.MEN_STATS_EUROPEGAMES));
        holder.europeGoals_men.setText(player_men.get(PlayersMenScreenActivity.MEN_STATS_EUROPEGOALS));
        holder.pictureText_men.setText(player_men.get(PlayersMenScreenActivity.MEN_PICTURE));
        return view;
    }
}