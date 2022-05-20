package com.example.demo_day1;

import com.example.demo_day1.Model.Bao;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class MyHandler extends DefaultHandler {
    private ArrayList<Bao> baoArrayList;
    private Bao bao;
    private String chuoi_tam;
    boolean input = false;
    public MyHandler(){
        baoArrayList = new ArrayList<Bao>();
    }
    public ArrayList<Bao> getBaos(){
        return baoArrayList;
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(input == true){
            chuoi_tam = new String(ch,start,length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(qName.equalsIgnoreCase("item")){
             bao = new Bao();
             input = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equalsIgnoreCase("item")){
            baoArrayList.add(bao);
        }else {
            if(qName.equalsIgnoreCase("title")){
                bao.setTitle(chuoi_tam);
            }else
            if(qName.equalsIgnoreCase("description")){
                bao.setDescription(chuoi_tam);
            }
        }
    }
}
