package com.jdk;

import com.sun.org.apache.xpath.internal.operations.String;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class lambda {

    public static Locale thLocal = new Locale("th", "TH", "TH");

    public static void main(String[] args) {
    new StringBuilder();


//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd HH:mm:ss", thLocal);
//        String str=simpleDateFormat.format(new Date());
//        System.out.println(str);
//        Locale[] thaiLocale = {new Locale("th"), new Locale("th", "TH"), new Locale("th", "TH", "TH")};
//        for (Locale locale : thaiLocale) {
//            StringBuilder textArea=new StringBuilder();
//            NumberFormat nf = NumberFormat.getNumberInstance(locale);
//            StringBuffer msgBuff = new StringBuffer();
//            msgBuff.append(locale.toString() + ": ");
//            msgBuff.append(nf.format(573.34));
//            textArea.append(msgBuff.toString() + "/n");
//            System.out.println(textArea);
//        }
    }
}
