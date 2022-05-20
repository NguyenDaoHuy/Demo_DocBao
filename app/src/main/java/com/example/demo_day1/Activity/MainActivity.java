package com.example.demo_day1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    private TextView txtLink;
    private ImageView logo;
    private ListView lvBao;
    private AdapterBao adapterBao;
    private ArrayList<Bao> baoArrayList;
    private String link = "https://vnexpress.net/rss/suc-khoe.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 goToURL("https://vnexpress.net/suc-khoe");
            }
        });
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToURL("https://vnexpress.net/suc-khoe");
            }
        });
        baoArrayList = new ArrayList<>();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 new ReadXML().execute("https://vnexpress.net/rss/suc-khoe.rss");
            }
        });
    }

    private void goToURL(String s) {
        Uri uri =Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private void anhXa() {
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
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }

    private class ReadXML extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... strings) {
            String kq = docNoiDung_Tu_URL(strings[0]);
            return kq;
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document doc = parser.getDocument(s);
            NodeList nodeList = doc.getElementsByTagName("item");
            String title = "";
            String description = "";
            baoArrayList = new ArrayList<>();
            Bao bao = new Bao();
            for(int i = 0 ; i < nodeList.getLength() ; i++){
            //    String cdata = nodeListDecription.item(i+1).getTextContent();
            //    Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
            //    Matcher matcher = p.matcher(cdata);
            //    if(matcher.find()){
            //        description = matcher.group(1);
            //   }
                Element e = (Element) nodeList.item(i);
                title = getTagValue("title",e);
                description = getTagValue("description",e);
                String noHTMLString = description.replaceAll("\\<.*?\\>", "");
                baoArrayList.add(new Bao(title,noHTMLString));
            }
            for (Bao b: baoArrayList) {
                System.out.println(b);
            }
            System.out.println("Số phần tử =========================" + baoArrayList.size());
            adapterBao = new AdapterBao(MainActivity.this,baoArrayList);
            lvBao.setAdapter(adapterBao);
        }
    }
    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();

    }

}