package com.example.acer.mywidgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String name="com.example.acer.mywidgets";
    //TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.list);
       // tv=findViewById();
        String[] s=getResources().getStringArray(R.array.listItems);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,s);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemname=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, itemname, Toast.LENGTH_SHORT).show();
                sp=getSharedPreferences(name,MODE_PRIVATE);
                editor=sp.edit();
                StringBuffer stringBuffer=new StringBuffer();
                stringBuffer.append(itemname);
                editor.putString("manasa",stringBuffer.toString());
                editor.apply();
                Intent i1=new Intent(MainActivity.this,SampleWiget.class);
                i1.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                int[] ids=AppWidgetManager.getInstance(MainActivity.this).getAppWidgetIds(new ComponentName(getApplication(),SampleWiget.class));
                i1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                sendBroadcast(i1);




            }
        });

    }
}
