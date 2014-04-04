package is.blikar.blikinn;
 
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
*
 * @author Guðný Lára Guðmundsdóttir
* @since 26.11.13
* Description: This class checks if phone has internet connection and returns true if it has, otherwise false
*
*/
public class ConnectionDetector {
	private Context context;
      
	public ConnectionDetector(Context context){
		this.context = context;
	}
 
	public boolean isConnectingToInternet(){
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					} 
		}
		return false;
	}
}