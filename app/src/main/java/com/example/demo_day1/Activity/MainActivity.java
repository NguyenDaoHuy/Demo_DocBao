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
import android.widget.Toast;

import com.example.demo_day1.Adapter.AdapterBao;
import com.example.demo_day1.Model.Bao;
import com.example.demo_day1.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
   //     baoArrayList.add(new Bao("Chuột rút khi chơi thể thao","Khi đang chơi thể thao bị chuột rút, cần lập tức dừng vận động và nghỉ ngơi, thả chùng chi đang co rút để thư giãn bắp thịt, sau đó xoa bóp, có thể chườm nóng."));
   //     baoArrayList.add(new Bao("Bệnh đậu mùa khỉ trỗi dậy ở châu Âu","Bệnh đậu mùa khỉ xuất hiện ở nhiều quốc gia châu Âu, các chuyên gia phỏng đoán nguyên nhân là do các nước nới hạn chế di chuyển giai đoạn hậu Covid."));
   //     baoArrayList.add(new Bao("Vì sao bệnh viện dùng nhiều máy mượn để xét nghiệm bệnh nhân?","Các bệnh viện cho rằng sử dụng máy mượn, máy đặt của các đơn vị trúng thầu vật tư hóa chất mang lại nhiều lợi ích trong xét nghiệm, khám chữa bệnh, tránh lãng phí ngân sách."));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 new ReadXML().execute("https://vnexpress.net/rss/suc-khoe.rss");
            }
        });
    }

    private void getDuLieu() {

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
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
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
            NodeList nodeListDecription = doc.getElementsByTagName("description");
            String title = "";
            String description = "";
            String kq = "";
            baoArrayList = new ArrayList<>();
            for(int i = 0 ; i < nodeList.getLength() ; i++){
                String cdata = nodeListDecription.item(i+1).getTextContent();

                Element e = (Element) nodeList.item(i);
                title = parser.getValue(e,"title");
                description = parser.getValue(e,"pubDate").toString();
                baoArrayList.add(new Bao(title,description));
            }
            System.out.println("Số phần tử =========================" + baoArrayList.size());
            adapterBao = new AdapterBao(MainActivity.this,baoArrayList);
            lvBao.setAdapter(adapterBao);
        }
    }
}