package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Main {

    private static void Finder(String site, String siteName) throws IOException {
        Document doc = Jsoup.connect(site).get();
        String html = doc.toString();
        String findStr = "data-code=\"";
        int lastIndex = 0;
        List<String> promocodes = new ArrayList<String>();
        while(lastIndex != -1) {

            lastIndex = html.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                String promocode="";
                int i=11;
                while(html.charAt(lastIndex+i)!='"') {
                    promocode += html.charAt(lastIndex + i);
                    i++;
                }
                if(promocode.length()!=0) {
                    promocodes.add(promocode);
                    System.out.println(siteName+':'+promocode);
                }
                lastIndex += 1;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://promokod.tonkosti.ru/shops").get();
        String html = doc.toString();
        String findStr = "https://promokod.tonkosti.ru/shops/";
        int lastIndex = 0;
        while(lastIndex != -1) {

            lastIndex = html.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                String site="";
                int i=0;
                while(html.charAt(lastIndex+i)!='"') {
                    site += html.charAt(lastIndex + i);
                    i++;
                }
                i+=3;
                String siteName="";
                while(html.charAt(lastIndex+i)!='<') {
                    siteName += html.charAt(lastIndex + i);
                    i++;
                }
                Finder(site, siteName);
                lastIndex += 1;
            }
        }
    }
}
