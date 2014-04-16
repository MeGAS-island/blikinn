package is.blikar.blikinn;

import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
/**
 * 
 * @author Aðalsteinn Stefánsson
 * @since 23.10.13
 * Description: This class loads advertisment in boot of app that is stored in a matrix
 *
 */
public class SplashScreenAd extends Activity {
 
    private static int SPLASH_TIME_OUT = 2000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      	String[] AdScreens = {"splash_ad_screen"};
    	String RandomAdScreen = (AdScreens[new Random().nextInt(AdScreens.length)]);
    	int layoutId = getResources().getIdentifier("layout/" + RandomAdScreen, "layout", getPackageName());
        setContentView(layoutId);
        new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenAd.this, MainScreenActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}