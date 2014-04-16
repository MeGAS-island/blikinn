package is.blikar.trophies;

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
 * @author Margrét Sigurpálsdóttir
 * @since 11.11.13
 * Description: This class creates men and women tabs for trophies
 *
 */
@SuppressWarnings("deprecation")
public class TrophiesActivity extends TabActivity {
	private static final String MEN_SPEC = "Men";
	private static final String WOMEN_SPEC = "Women";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_main);
        Resources ressources = getResources();
        final TabHost tabHost = getTabHost();      
        // Men tab
        Intent intentMen = new Intent().setClass(this, TrophiesMenScreenActivity.class);
        TabSpec tabSpecMen = tabHost
          .newTabSpec(MEN_SPEC)
          .setIndicator("", ressources.getDrawable(R.drawable.icon_male))
          .setContent(intentMen);
        // Women tab
        Intent intentWomen = new Intent().setClass(this, TrophiesWomenScreenActivity.class);
        TabSpec tabSpecWomen = tabHost
          .newTabSpec(WOMEN_SPEC)
          .setIndicator("", ressources.getDrawable(R.drawable.icon_female))
          .setContent(intentWomen);
        // add all tabs 
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

