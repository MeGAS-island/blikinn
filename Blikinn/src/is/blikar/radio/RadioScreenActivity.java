package is.blikar.radio;
 
import is.blikar.blikinn.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: This class displays spreaker radio in a webview
 *
 */
public class RadioScreenActivity extends Activity {
	private WebView webView;
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_screen);
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.spreaker.com/user/breidablik");
	}
}