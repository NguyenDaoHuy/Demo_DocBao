package com.example.demo_day1;

import com.example.demo_day1.Model.Bao;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;

public class MySaxParser {
    public static ArrayList<Bao> xmlParser(InputStream is){
        ArrayList<Bao> baos = null;
        try{
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            MyHandler myHandler = new MyHandler();
            xmlReader.setContentHandler(myHandler);
            xmlReader.parse(new InputSource(is));
            baos = myHandler.getBaos();
        }catch (Exception e){
            e.printStackTrace();
        }
        return baos;
    }
}
