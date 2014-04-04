package is.blikar.players;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import is.blikar.blikinn.R;
/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: this class creates tabs for men and women players
 *
 */
@SuppressWarnings("deprecation")
public class PlayersActivity extends TabActivity {
	private static final String MEN_SPEC = "Men";
	private static final String WOMEN_SPEC = "Women";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_main);
        Resources ressources = getResources();
        final TabHost tabHost = getTabHost(); 
        // Men tab
        Intent intentMen = new Intent().setClass(this, PlayersMenScreenActivity.class);
        TabSpec tabSpecMen = tabHost
          .newTabSpec(MEN_SPEC)
          .setIndicator("", ressources.getDrawable(R.drawable.icon_men))
          .setContent(intentMen);
        // Women tab
        Intent intentWomen = new Intent().setClass(this, PlayersWomenScreenActivity.class);
        TabSpec tabSpecWomen = tabHost
          .newTabSpec(WOMEN_SPEC)
          .setIndicator("", ressources.getDrawable(R.drawable.icon_women))
          .setContent(intentWomen); 
        tabHost.addTab(tabSpecMen);
        tabHost.addTab(tabSpecWomen);
        setTabColor(tabHost);
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                setTabColor(tabHost);
            }
        });
        tabHost.setCurrentTab(0);
    }

    public void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(this.getResources().getColor(R.color.white)); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(this.getResources().getColor(R.color.green)); // selected
    }
}