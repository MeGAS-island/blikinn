package is.blikar.players;

import is.blikar.blikinn.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
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
public class SinglePlayerActivity_men extends Activity {
	private static final String MEN_NAME = "name_men";
	private static final String MEN_BIRTHDAY = "birthday_men";
	private static final String MEN_PICTURE = "picture_men";
	private static final String MEN_PLAYERPOSITION = "playerPosition_men";
	private static final String MEN_STATS_TOTALGAMES = "totalGames_men";
	private static final String MEN_STATS_TOTALGOALS = "totalGoals_men";
	private static final String MEN_STATS_LEAGUEGAMES = "leagueGames_men";
	private static final String MEN_STATS_LEAGUEGOALS = "leagueGoals_men";
	private static final String MEN_STATS_CUPGAMES = "cupGames_men";
	private static final String MEN_STATS_CUPGOALS = "cupGoals_men";
	private static final String MEN_STATS_EUROPEGAMES = "europeGames_men";
	private static final String MEN_STATS_EUROPEGOALS = "europeGoals_men";
	DisplayImageOptions options;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player_men);
        options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_white)
		.showImageForEmptyUri(R.drawable.ic_white)
		.showImageOnFail(R.drawable.ic_white)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new FadeInBitmapDisplayer(20))
		.build();
        
		//Layout
		Typeface font = Typeface.createFromAsset(getAssets(), "aller_rg.ttf");
		TextView header = (TextView) findViewById(R.id.name_label_men);
		header.setTypeface(font);  
        

        Intent in = getIntent();
        ImageLoader imageLoader = ImageLoader.getInstance();

        String varName_men = in.getStringExtra(MEN_NAME);
        String varBirthday_men = in.getStringExtra(MEN_BIRTHDAY);
        String varPictureText_men = in.getStringExtra(MEN_PICTURE);
        String varPlayerPosition_men = in.getStringExtra(MEN_PLAYERPOSITION);
        String varTotalGames_men = in.getStringExtra(MEN_STATS_TOTALGAMES);
        String varTotalGoals_men = in.getStringExtra(MEN_STATS_TOTALGOALS);
        String varLeagueGames_men = in.getStringExtra(MEN_STATS_LEAGUEGAMES);
        String varLeagueGoals_men = in.getStringExtra(MEN_STATS_LEAGUEGOALS);
        String varCupGames_men = in.getStringExtra(MEN_STATS_CUPGAMES);
        String varCupGoals_men = in.getStringExtra(MEN_STATS_CUPGOALS);
        String varEuropeGames_men = in.getStringExtra(MEN_STATS_EUROPEGAMES);
        String varEuropeGoals_men = in.getStringExtra(MEN_STATS_EUROPEGOALS);

        TextView lblName_men = (TextView) findViewById(R.id.name_label_men);
        TextView lblBirthday_men = (TextView) findViewById(R.id.birthday_label_men);
        ImageView lblPictureLarge_men = (ImageView) findViewById(R.id.pictureLarge_men);
        TextView lblPlayerPosition_men = (TextView) findViewById(R.id.playerPosition_label_men);
        TextView lblTotalGames_men = (TextView) findViewById(R.id.totalGames_label_men);
        TextView lblTotalGoals_men = (TextView) findViewById(R.id.totalGoals_label_men);
        TextView lblLeagueGames_men = (TextView) findViewById(R.id.leagueGames_label_men);
        TextView lblLeagueGoals_men = (TextView) findViewById(R.id.leagueGoals_label_men);
        TextView lblCupGames_men = (TextView) findViewById(R.id.cupGames_label_men);
        TextView lblCupGoals_men = (TextView) findViewById(R.id.cupGoals_label_men);
        TextView lblEuropeGames_men = (TextView) findViewById(R.id.europeGames_label_men);
        TextView lblEuropeGoals_men = (TextView) findViewById(R.id.europeGoals_label_men);
        
        lblName_men.setText(varName_men);
        lblBirthday_men.setText(varBirthday_men);
        imageLoader.displayImage(varPictureText_men, lblPictureLarge_men, options);
        lblPlayerPosition_men.setText(varPlayerPosition_men);
        lblTotalGames_men.setText(varTotalGames_men);
        lblTotalGoals_men.setText(varTotalGoals_men);
        lblLeagueGames_men.setText(varLeagueGames_men);
        lblLeagueGoals_men.setText(" ("+varLeagueGoals_men+")");
        lblCupGames_men.setText(varCupGames_men);
        lblCupGoals_men.setText(" ("+varCupGoals_men+")");
        lblEuropeGames_men.setText(varEuropeGames_men);
        lblEuropeGoals_men.setText(" ("+varEuropeGoals_men+")");
    }
}
