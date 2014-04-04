package is.blikar.pictures;

import android.app.Activity;
import com.nostra13.universalimageloader.core.ImageLoader;
/**
 * 
 * @author Margrét Sigurpálsdóttir
 * @since 25.11.13
 * Description: 
 *
 */
public abstract class BaseActivity extends Activity {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
}