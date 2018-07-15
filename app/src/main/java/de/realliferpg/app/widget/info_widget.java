package de.realliferpg.app.widget;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import de.realliferpg.app.R;
import de.realliferpg.app.adapter.ServerListAdapter;
import de.realliferpg.app.helper.ApiHelper;
import de.realliferpg.app.interfaces.RequestCallbackInterface;
import de.realliferpg.app.objects.Server;

public class info_widget extends AppWidgetProvider implements RequestCallbackInterface{
    Context contex;
    int[] WidgetIds;
    AppWidgetManager appWidgetManagerglo;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        WidgetIds = appWidgetIds;
        contex = context;
        appWidgetManagerglo = appWidgetManager;

        final ApiHelper apiHelper = new ApiHelper(this);
        apiHelper.getServers();

    }

    @Override
    public void onResponse(Object response, Class type) {
        final int count = WidgetIds.length;
        for (int i = 0; i < count; i++) {
            int widgetId = WidgetIds[i];
            if (type.equals(Server.Wrapper.class)) {
                RemoteViews remoteViews = new RemoteViews(contex.getPackageName(), R.layout.info_widget);

                Gson gson = new Gson();

                Server.Wrapper value = gson.fromJson(response.toString(), Server.Wrapper.class);

                final ArrayList<Server> servers = new ArrayList<>(Arrays.asList(value.data));

                remoteViews.setTextViewText(R.id.lv_widget_serverList, servers.get(1).Description);


            }


        }
    }
}
