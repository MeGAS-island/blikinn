package is.blikar.news;
/**
 * 
 * @author Snorri Hannesson
 * @since 20.11.13
 * Description: This class creates tabs for women and men
 *
 */
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import is.blikar.blikinn.R;

@SuppressWarnings("deprecation")
public class NewsActivity extends TabActivity {
	// TabSpec Names
	private static final String MEN_SPEC = "Men";
	private static final String WOMEN_SPEC = "Women";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_main);
        
        Resources ressources = getResources();
        final TabHost tabHost = getTabHost();
                
        // Men tab
        Intent intentMen = new Intent().setClass(this, NewsScreenActivity.class);
        TabSpec tabSpecMen = tabHost
          .newTabSpec(MEN_SPEC)
          .setIndicator("", ressources.getDrawable(R.drawable.ic_tv_color))
          .setContent(intentMen);
        
        // Men tab
        Intent intentWomen = new Intent().setClass(this, NewsWomanScreenActivity.class);
        TabSpec tabSpecWomen = tabHost
          .newTabSpec(WOMEN_SPEC)
          .setIndicator("", ressources.getDrawable(R.drawable.ic_radio_color))
          .setContent(intentWomen);
    
        // Add all tabs 
        tabHost.addTab(tabSpecMen);
        tabHost.addTab(tabSpecWomen);

        setTabColor(tabHost);

        tabHost.setOnTabChangedListener(new OnTabChangeListener() {

            public void onTabChanged(String tabId) {
                setTabColor(tabHost);
            }
        });
        // Set Windows tab as default (zero based)
        tabHost.setCurrentTab(0);
    }

    public void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(this.getResources().getColor(R.color.white)); // unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(this.getResources().getColor(R.color.green)); // selected
    }
}