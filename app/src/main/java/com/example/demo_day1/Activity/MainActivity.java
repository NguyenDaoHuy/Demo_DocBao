package com.example.demo_day1.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_day1.Adapter.AdapterBao;
import com.example.demo_day1.Model.Bao;
import com.example.demo_day1.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterBao.IRSS {
    private TextView txtLink;
    private ImageView logo;
    private ListView lvBao;
    private AdapterBao adapterBao;
    private ArrayList<Bao> baoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        txtLink.setOnClickListener(v -> goToURL());
        logo.setOnClickListener(v -> goToURL());
        baoArrayList = new ArrayList<>();

        runOnUiThread(() -> {
             new ReadXML().execute("https://vnexpress.net/rss/suc-khoe.rss");
             baoArrayList = new ArrayList<>();
        });
        adapterBao = new AdapterBao(this);
        lvBao.setAdapter(adapterBao);
    }

    private void goToURL() {
        Uri uri =Uri.parse("https://vnexpress.net/suc-khoe");
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private void findView() {
        txtLink = findViewById(R.id.txtLink);
        logo = findViewById(R.id.logo);
        lvBao = findViewById(R.id.lvBao);
    }

    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line).append("\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public int getCount() {
        if(baoArrayList == null){
             return 0;
        } else {
            baoArrayList.size();
        }
        return baoArrayList.size();
    }

    @Override
    public Bao getData(int possition) {
        return baoArrayList.get(possition);
    }

    @Override
    public void setOnClick(int possition) {
        Intent intent = new Intent(this, DocBaoActivity.class);
        intent.putExtra("thongtin", baoArrayList.get(possition));
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    private class ReadXML extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document doc = parser.getDocument(s);
            NodeList nodeList = doc.getElementsByTagName("item");
            String title;
            String description;
            baoArrayList = new ArrayList<>();
            for(int i = 0 ; i < nodeList.getLength() ; i++){
                Element e = (Element) nodeList.item(i);
                title = getTagValue("title",e);
                description = getTagValue("description",e);
                String noHTMLString = description.replaceAll("\\<.*?\\>", "");
                baoArrayList.add(new Bao(title,noHTMLString));
            }
            for (Bao b: baoArrayList) {
                System.out.println(b);
            }
            //
            lvBao.setAdapter(adapterBao);
        }
    }
    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();
        Node nValue = nlList.item(0);
        return nValue.getNodeValue();

    }

}