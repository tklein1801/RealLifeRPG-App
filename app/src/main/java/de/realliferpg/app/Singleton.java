package de.realliferpg.app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import de.realliferpg.app.objects.PlayerInfo;
import de.realliferpg.app.objects.Server;

public class Singleton {
    private static final Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    private Singleton() {
    }

    private RequestQueue volleyQueue;
    private Context context;
    private ArrayList<Server> servers;

    private PlayerInfo playerInfo;

    public RequestQueue getRequestQueue() {
        if (volleyQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            volleyQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return volleyQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public void setContext(Context context) {
        this.context = context;
    }
    public void setServerinfo(ArrayList<Server> servers) { this.servers= servers; }

    public Context getContext (){return this.context;}
    public ArrayList<Server> getServerinfo (){return this.servers;}

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }
}
