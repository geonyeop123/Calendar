package com.yeop.calendar.domain;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HolidayAPI {

    private static StringBuilder urlBuilder = null;
    private static URL url = null;
    private static HttpURLConnection conn = null;
    private static BufferedReader br = null;
    private static DocumentBuilderFactory dbFactory = null;
    private static DocumentBuilder db = null;
    private static Document document = null;

    public static List<Holiday> getHoliday(int year, int month) throws Exception{
        String syear = String.valueOf(year);
        String smonth = month < 10 ? "0" + month : String.valueOf(month);
        List<Holiday> holidayList = new ArrayList<Holiday>();
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=HFZ7esAlPNwhEdbkFYSQSBpIpTrzsnPTiF%2FDS9WjP69UGXAvVaH8neE2AB1fLbvaTIjFEzPdzIr4236vzwyCYg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(syear, "UTF-8")); /*연*/
        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode(smonth, "UTF-8")); /*월*/
        url = new URL(urlBuilder.toString());
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        conn.disconnect();
        /* StringBuilder
        <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
        <response>
            <header>
                <resultCode>00</resultCode>
                <resultMsg>NORMAL SERVICE.</resultMsg>
            </header>
            <body>
                <items>
                    <item>
                        <dateKind>01</dateKind>
                        <dateName>공휴일 이름</dateName>
                        <isHoliday>Y</isHoliday>
                        <locdate>공휴일 날짜</locdate>
                        <seq>1</seq>
                    </item>
                </items>
                <numOfRows>10</numOfRows>
                <pageNo>1</pageNo>
                <totalCount>1</totalCount>
            </body>
        </response>
        */

        dbFactory = DocumentBuilderFactory.newInstance();
        db = dbFactory.newDocumentBuilder();
        document = db.parse(new InputSource(new StringReader(sb.toString())));

        // item 가져오기
        NodeList list = document.getElementsByTagName("item");

        // item 내의 dateName과 localDate 값 가져오기
        for(int i = 0; i < list.getLength(); i++){
            Node item = list.item(i);
            if(item.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) item;
                Holiday holiday = new Holiday();
                holiday.setName(element.getElementsByTagName("dateName").item(0).getTextContent());
                holiday.setDate(LocalDate.parse(element.getElementsByTagName("locdate").item(0).getTextContent(),
                                DateTimeFormatter.ofPattern("yyyyMMdd")));
                holidayList.add(holiday);
            }
        }
        return holidayList;
    }

}
