package is.blikar.utils;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.RemoteViews;
import is.blikar.blikinn.R;
import is.blikar.pictures.UILApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
/**
 * 
 * @author Snorri Hannesson
 * @since 6.11.13
 * Description: This class parses json strings and returns json objects
 *
 */

import static is.blikar.pictures.Constants.IMAGES;
/**
 * 
 * @author Snorri Hannesson
 * @since 25.11.13
 *
 */
public class UILWidgetProvider extends AppWidgetProvider {

	private static DisplayImageOptions displayOptions;
	static {
		displayOptions = DisplayImageOptions.createSimple();
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		UILApplication.initImageLoader(context);
		final int widgetCount = appWidgetIds.length;
		for (int i = 0; i < widgetCount; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}

	static void updateAppWidget(Context context, final AppWidgetManager appWidgetManager, final int appWidgetId) {
		int imgSize = 70;
		final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
		ImageSize minImageSize = new ImageSize(imgSize, imgSize); 
		ImageLoader.getInstance()
				.loadImage(IMAGES[0], minImageSize, displayOptions, new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						views.setImageViewBitmap(R.id.image_left, loadedImage);
						appWidgetManager.updateAppWidget(appWidgetId, views);
					}
				});
		ImageLoader.getInstance()
				.loadImage(IMAGES[1], minImageSize, displayOptions, new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						views.setImageViewBitmap(R.id.image_right, loadedImage);
						appWidgetManager.updateAppWidget(appWidgetId, views);
					}
				});
	}
}
