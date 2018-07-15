package de.realliferpg.app.widget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;

import de.realliferpg.app.Singleton;
import de.realliferpg.app.helper.ApiHelper;
import de.realliferpg.app.interfaces.RequestCallbackInterface;
import de.realliferpg.app.objects.Changelog;
import de.realliferpg.app.objects.Server;
import de.realliferpg.app.R;



public class info_widget extends AppWidgetProvider implements RequestCallbackInterface {


    static void updateAppWidget(Context context,

                                AppWidgetManager appWidgetManager,

                                int appWidgetId) {

//Instantiate the RemoteViews object//

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.info_widget);

//Update your app’s text, using the setTextViewText method of the RemoteViews class//

        views.setTextViewText(R.id.id_value, String.valueOf(appWidgetId));

//Register the OnClickListener//

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://code.tutsplus.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.launch_url, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //Update all instances of this widget//

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onResponse(Object response, Class type) {

    }
}
