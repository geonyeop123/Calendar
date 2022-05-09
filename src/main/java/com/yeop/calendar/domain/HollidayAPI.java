package com.yeop.calendar.domain;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HollidayAPI {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=HFZ7esAlPNwhEdbkFYSQSBpIpTrzsnPTiF%2FDS9WjP69UGXAvVaH8neE2AB1fLbvaTIjFEzPdzIr4236vzwyCYg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode("2022", "UTF-8")); /*연*/
        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode("09", "UTF-8")); /*월*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb);

        try{
        DocumentBuilderFactory factory  =  DocumentBuilderFactory.newInstance();
        DocumentBuilder builder    =  factory.newDocumentBuilder();
        //Document document     =  builder.parse("c:/person.xml");

        //*****************************************************
        Document document     =  builder.parse(new InputSource(new StringReader(sb.toString())));
        //여기가 핵심 *******************************************
        // root 구하기
        NodeList list = document.getElementsByTagName("item");

        //태그 (< >)의 이름으로 불러오는 내용
        for(int i = 0; i < list.getLength(); i++){
            Node item = list.item(i);
            if(item.getNodeType() == Node.ELEMENT_NODE) { // 노드의 타입이 Element일 경우(공백이 아닌 경우)
                System.out.println(item.getNodeName());
                System.out.println(item.getTextContent());
            } else {
                System.out.println("공백 입니다.");
            }
        }


    }catch(Exception e){
        e.printStackTrace();

    }
    }

}
