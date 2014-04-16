package is.blikar.blikinn;

import java.io.File;
import java.util.Random;
import android.app.Activity;
import android.content.Context;
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
    
}