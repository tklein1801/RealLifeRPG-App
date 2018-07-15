package de.realliferpg.app.widget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;

import de.realliferpg.app.Singleton;
import de.realliferpg.app.helper.ApiHelper;
import de.realliferpg.app.interfaces.RequestCallbackInterface;
import de.realliferpg.app.objects.Server;
import de.realliferpg.app.R;



public class info_widget extends AppWidgetProvider implements RequestCallbackInterface {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {


        ApiHelper apiHelper = new ApiHelper(this);
        apiHelper.getServers();

        Object response = Singleton.getInstance().getServerinfo();

        final int count = appWidgetIds.length;
        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];


            Gson gson = new Gson();

            Server.Wrapper value = gson.fromJson(response.toString(), Server.Wrapper.class);

            final ArrayList<Server> servers = new ArrayList<>(Arrays.asList(value.data));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.info_widget);


            remoteViews.setTextViewText(R.id.lv_widget_serverList, servers.get(1).Description);


        }
    }

        @Override
        public void onResponse(Object response, Class type) {


        Singleton.getInstance().setServerinfo(response);


    }
}
