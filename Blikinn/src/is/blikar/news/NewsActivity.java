package is.blikar.news;
/**
 * 
 * @author Snorri Hannesson
 * @since 20.11.13
 * Description: This class creates tabs for women and men
 *
 */
import java.io.File;

import android.app.TabActivity;
import android.content.Context;
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
          .setIndicator("", ressources.getDrawable(R.drawable.icon_male))
          .setContent(intentMen);
        
        // Men tab
        Intent intentWomen = new Intent().setClass(this, NewsWomanScreenActivity.class);
        TabSpec tabSpecWomen = tabHost
          .newTabSpec(WOMEN_SPEC)
          .setIndicator("", ressources.getDrawable(R.drawable.icon_female))
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
    
    @Override
    protected void onStop(){
       super.onStop();
    }

    //Fires after the OnStop() state
    @Override
    protected void onDestroy() {
       super.onDestroy();
       try {
          trimCache(this);
       } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }
    }

    public static void trimCache(Context context) {
       try {
          File dir = context.getCacheDir();
          if (dir != null && dir.isDirectory()) {
             deleteDir(dir);
          }
       } catch (Exception e) {
          // TODO: handle exception
       }
    }

    public static boolean deleteDir(File dir) {
       if (dir != null && dir.isDirectory()) {
          String[] children = dir.list();
          for (int i = 0; i < children.length; i++) {
             boolean success = deleteDir(new File(dir, children[i]));
             if (!success) {
                return false;
             }
          }
       }

       // The directory is now empty so delete it
       return dir.delete();
    }

    public void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(this.getResources().getColor(R.color.white)); // unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(this.getResources().getColor(R.color.green)); // selected
    }
}