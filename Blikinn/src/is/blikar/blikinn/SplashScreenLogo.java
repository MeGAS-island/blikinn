package is.blikar.blikinn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
/**
 * 
 * @author Aðalsteinn Stefánsson
 * @since 6.11.13
 * Description: This class publishes displays logo at boot
 *
 */
public class SplashScreenLogo extends Activity {
 
    private static int SPLASH_TIME_OUT = 2000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_logo_screen);
 
        new Handler().postDelayed(new Runnable() {
  
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenLogo.this, SplashScreenAd.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}