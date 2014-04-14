package is.blikar.players;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import is.blikar.blikinn.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: This class displays data about selected player on screen
 *
 */
public class SinglePlayerActivity_women extends Activity {
	private static final String WOMEN_NAME = "name_women";
	private static final String WOMEN_BIRTHDAY = "birthday_women";
	private static final String WOMEN_PICTURE = "picture_women";
	private static final String WOMEN_STATS_TOTALGAMES = "totalGames_women";
	private static final String WOMEN_STATS_TOTALGOALS = "totalGoals_women";
	private static final String WOMEN_STATS_LEAGUEGAMES = "leagueGames_women";
	private static final String WOMEN_STATS_LEAGUEGOALS = "leagueGoals_women";
	private static final String WOMEN_STATS_CUPGAMES = "cupGames_women";
	private static final String WOMEN_STATS_CUPGOALS = "cupGoals_women";
	private static final String WOMEN_STATS_EUROPEGAMES = "europeGames_women";
	private static final String WOMEN_STATS_EUROPEGOALS = "europeGoals_women";
	DisplayImageOptions options;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player_women);
        options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_white)
		.showImageForEmptyUri(R.drawable.ic_white)
		.showImageOnFail(R.drawable.ic_white)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new FadeInBitmapDisplayer(20))
		.build();

        Intent in = getIntent();
        ImageLoader imageLoader = ImageLoader.getInstance();

        String varName_women = in.getStringExtra(WOMEN_NAME);
        String varBirthday_women = in.getStringExtra(WOMEN_BIRTHDAY);
        String varPictureText_women = in.getStringExtra(WOMEN_PICTURE);;
        String varTotalGames_women = in.getStringExtra(WOMEN_STATS_TOTALGAMES);
        String varTotalGoals_women = in.getStringExtra(WOMEN_STATS_TOTALGOALS);
        String varLeagueGames_women = in.getStringExtra(WOMEN_STATS_LEAGUEGAMES);
        String varLeagueGoals_women = in.getStringExtra(WOMEN_STATS_LEAGUEGOALS);
        String varCupGames_women = in.getStringExtra(WOMEN_STATS_CUPGAMES);
        String varCupGoals_women = in.getStringExtra(WOMEN_STATS_CUPGOALS);
        String varEuropeGames_women = in.getStringExtra(WOMEN_STATS_EUROPEGAMES);
        String varEuropeGoals_women = in.getStringExtra(WOMEN_STATS_EUROPEGOALS);
        
        TextView lblName_women = (TextView) findViewById(R.id.name_label_women);
        TextView lblBirthday_women = (TextView) findViewById(R.id.birthday_label_women);
        ImageView lblPictureLarge_women = (ImageView) findViewById(R.id.pictureLarge_women);
        TextView lblTotalGames_women = (TextView) findViewById(R.id.totalGames_label_women);
        TextView lblTotalGoals_women = (TextView) findViewById(R.id.totalGoals_label_women);
        TextView lblLeagueGames_women = (TextView) findViewById(R.id.leagueGames_label_women);
        TextView lblLeagueGoals_women = (TextView) findViewById(R.id.leagueGoals_label_women);
        TextView lblCupGames_women = (TextView) findViewById(R.id.cupGames_label_women);
        TextView lblCupGoals_women = (TextView) findViewById(R.id.cupGoals_label_women);
        TextView lblEuropeGames_women = (TextView) findViewById(R.id.europeGames_label_women);
        TextView lblEuropeGoals_women = (TextView) findViewById(R.id.europeGoals_label_women);
        
        lblName_women.setText(varName_women);
        lblBirthday_women.setText(varBirthday_women);
        imageLoader.displayImage(varPictureText_women, lblPictureLarge_women, options);
        lblTotalGames_women.setText(varTotalGames_women);
        lblTotalGoals_women.setText(varTotalGoals_women);
        lblLeagueGames_women.setText(varLeagueGames_women);
        lblLeagueGoals_women.setText(varLeagueGoals_women);
        lblCupGames_women.setText(varCupGames_women);
        lblCupGoals_women.setText(varCupGoals_women);
        lblEuropeGames_women.setText(varEuropeGames_women);
        lblEuropeGoals_women.setText(varEuropeGoals_women);
    }
}
