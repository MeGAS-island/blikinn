package is.blikar.blikinn;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
/** 
 * 
 * @author Guðný Lára Guðmundsdóttir 
 * @since 20.11.13 
 * Description: This class handles the menu bar 
 * 
 */ 
public class About extends Activity {
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_screen);
	}
}