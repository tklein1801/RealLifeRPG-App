package de.realliferpg.app.widget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
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

    public static final String REFRESH_ACTION = "com.realliferpg.REFRESH_ACTION";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.info_widget);


        appWidgetManager.updateAppWidget(new ComponentName(context,info_widget.class), views);

        final ArrayList<Server> servers = Singleton.getInstance().getServerinfo();

        views.setTextViewText(R.id.id_value, servers.get(0).Servername);




        /*
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://code.tutsplus.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.launch_url, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
        */

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //Update all instances of this widget//
        final ArrayList<Server> servers = Singleton.getInstance().getServerinfo();



        if (servers != null){

            for (int appWidgetId : appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }

        }else {

            ApiHelper apiHelper = new ApiHelper(this);
            apiHelper.getServers();

            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.info_widget);

            Intent intentServiceCall = new Intent(context, info_widget.class);
            intentServiceCall.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);

            Intent refreshIntent = new Intent(context, info_widget.class);
            final Intent intent = refreshIntent.setAction(info_widget.REFRESH_ACTION);
            refreshIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            intentServiceCall.setData(Uri.parse(intentServiceCall.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.id_value, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds, rv);
        }

    }

    @Override
    public void onResponse(Object response, Class type) {
        Log.d("info_widget",response.toString());
        Gson gson = new Gson();
        Server.Wrapper value = gson.fromJson(response.toString(), Server.Wrapper.class);

        final ArrayList<Server> servers = new ArrayList<>(Arrays.asList(value.data));

        Singleton.getInstance().setServerinfo(servers);




    }
}
