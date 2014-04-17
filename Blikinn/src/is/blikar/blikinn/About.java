package is.blikar.blikinn;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
/** 
 * 
 * @author Guðný Lára Guðmundsdóttir 
 * @since 20.11.13 
 * Description: This class handles the menu bar 
 * 
 */ 
public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_screen);
		
		
		//FONT
		Typeface font = Typeface.createFromAsset(getAssets(), "aller_rg.ttf");
		
        //TEXT
		TextView header = (TextView) findViewById(R.id.aboutHeader);
		header.setTypeface(font);
		
		TextView about_text = (TextView) findViewById(R.id.about_text);
		about_text.setTypeface(font);    		
		
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