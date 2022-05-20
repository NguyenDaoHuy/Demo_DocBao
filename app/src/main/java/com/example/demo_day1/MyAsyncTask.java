package com.example.demo_day1;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.demo_day1.Activity.MainActivity;
import com.example.demo_day1.Adapter.AdapterBao;
import com.example.demo_day1.Model.Bao;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MyAsyncTask extends AsyncTask<Void,Void,Void> {
    ArrayList<Bao> baoArrayList;
    String link;
    ListView lv;
    private AdapterBao adapterBao;
    Context context;
    public MyAsyncTask(String str, ListView lv,Context context){
        this.link = str;
        this.lv = lv;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            baoArrayList = new ArrayList<>();
            baoArrayList = MySaxParser.xmlParser(is);
            String title = "";
            String description = "";
            for(int i = 0 ;i<baoArrayList.size();i++){
                title = baoArrayList.get(i).getTitle();
                description = baoArrayList.get(i).getDescription();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
      //  adapterBao = new AdapterBao(context,baoArrayList);
      //  lv.setAdapter(adapterBao);
    }
}
