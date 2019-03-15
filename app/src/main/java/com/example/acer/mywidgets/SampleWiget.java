package com.example.acer.mywidgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class SampleWiget extends AppWidgetProvider
{
    SharedPreferences sharedPreferences;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int myWidgetid:appWidgetIds)
        {
            sharedPreferences=context.getSharedPreferences(MainActivity.name,Context.MODE_PRIVATE);
            String s2=sharedPreferences.getString("manasa","no Data generated");
            Intent i=new Intent(context,MainActivity.class);
            PendingIntent pi=PendingIntent.getActivity(context,1,i,0);
            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.design);
            views.setTextViewText(R.id.widget,s2);
            views.setOnClickPendingIntent(R.id.widget,pi);
            appWidgetManager.updateAppWidget(myWidgetid,views);
        }
    }
}
