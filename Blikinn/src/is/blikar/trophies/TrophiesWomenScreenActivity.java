package is.blikar.trophies;

import java.io.File;

import is.blikar.blikinn.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 
 * @author Aðalsteinn Stefánsson
 * @since 6.11.13
 * Description: This class displays women trophies
 *
 */
public class TrophiesWomenScreenActivity extends Activity {
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.trophies_women_screen);    
	       
		   Typeface font = Typeface.createFromAsset(getAssets(), "aller_rg.ttf");
		   
		   TextView tv_islandsmeistarar_kvenna = (TextView) findViewById(R.id.tv_islandsmeistarar_kvenna);
		   tv_islandsmeistarar_kvenna.setTypeface(font);
		   
		   TextView tv_bikarmeistarar_kvenna = (TextView) findViewById(R.id.tv_bikarmeistarar_kvenna);
		   tv_bikarmeistarar_kvenna.setTypeface(font);
		   
		   TextView biggest_victory_men = (TextView) findViewById(R.id.biggest_victory_women);
		   biggest_victory_men.setTypeface(font);    
	       
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