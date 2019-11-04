package com.clarity;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.Encoder;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ManagedBean(name="weatherService", eager=true)

public class WeatherService {
  private static final String YAHOO_APPLICATION_ID =
      "n57MqO36";
  private static final String WEATHER_BASE_URL =
      "https://weather-ydn-yql.media.yahoo.com/forecastrss?";
  private static final long serialVersionUID = 1L;

  private static final String CUSTOMER_SECRET="957b14ad4778f87435f1168a3b7279df92e7abeb";

  private static final String oauth_consumer_key ="dj0yJmk9VU11VHVYRkphbnBLJmQ9WVdrOWVGRlJRWFp2TlRnbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWJi";
  private static final String oauth_signature_method = "HMAC-SHA1";
  private static final String oauth_timestamp ="1572855175";
  private static final String oauth_nonce = "ecbRL2TbU53";
  private static final String oauth_version ="1.0";
  private static final String oauth_signature = "6G/F55t61122s21JBdPNXZKeluo=";


  //https://weather-ydn-yql.media.yahoo.com/forecastrss?location=sunnyvale,ca&oauth_consumer_key=dj0yJmk9VU11VHVYRkphbnBLJmQ9WVdrOWVGRlJRWFp2TlRnbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWJi&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1572855175&oauth_nonce=ecbRL2TbU53&oauth_version=1.0&oauth_signature=6G/F55t61122s21JBdPNXZKeluo=
  //oauth_nonce=ecbRL2TbU53&oauth_version=1.0&oauth_signature=6G/F55t61122s21JBdPNXZKeluo=
  public String getWeatherForZip(String zip,
      boolean isFarenheit, String city) {
//    String url =
//        WEATHER_BASE_URL + "appid=" + YAHOO_APPLICATION_ID
//            + "&" + "p=" + zip + "&u="
//            + (isFarenheit ? "f" : "c");


    String signature = null;
    try {
      SecretKeySpec signingKey = new SecretKeySpec((CUSTOMER_SECRET + "&").getBytes(), "HmacSHA1");
      Mac mac = Mac.getInstance("HmacSHA1");
      mac.init(signingKey);
      byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
      Encoder encoder = Base64.getEncoder();
      signature = encoder.encodeToString(rawHMAC);
    } catch (Exception e) {
      System.err.println("Unable to append signature");
      System.exit(0);
    }




    String url = WEATHER_BASE_URL +"location="+city +
            "&oauth_consumer_key="+oauth_consumer_key+
            "&oauth_signature_method="+oauth_signature_method+
            "&oauth_timestamp="+oauth_timestamp+
            "&oauth_nonce="+oauth_nonce+
            "&oauth_version="+oauth_version;
            //"&oauth_signature="+oauth_signature;
    //System.out.println(city);
    return getWeatherFromDocument(getWeatherDocument(url));
  }
  private String getWeatherFromDocument(Document document) {
    Element item =
        (Element) document.getElementsByTagName("item")
            .item(0);

    String title =
        ((Element) item.getElementsByTagName("title").item(0))
            .getFirstChild().getNodeValue();

    Element description =
        (Element) item.getElementsByTagName("description")
            .item(0);

    Element image =
        (Element) item.getElementsByTagName("img")
            .item(0);

    return "<div class='heading'>" + title + "</div>"
        + "<hr/>"
        + description.getFirstChild().getNodeValue();
  }
  private Document getWeatherDocument(String url) {
    //url ="https://weather-ydn-yql.media.yahoo.com/forecastrss?location=sunnyvale,ca&oauth_consumer_key=dj0yJmk9VU11VHVYRkphbnBLJmQ9WVdrOWVGRlJRWFp2TlRnbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWJi&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1572855175&oauth_nonce=ecbRL2TbU53&oauth_version=1.0&oauth_signature=6G/F55t61122s21JBdPNXZKeluo=";
    System.out.println(url);
    Document document = null;
    try {
      HttpClient client = new HttpClient(); // Jakarta Commons
      GetMethod gm = new GetMethod(url);
      if (HttpServletResponse.SC_OK == client
          .executeMethod(gm)) {
        InputStream in = gm.getResponseBodyAsStream();
        document =
            DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(in);
      }
    }
    catch (Exception e) {
      try {
        document =
            DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(
                    "cannedForecast.xml");
      }
      catch (Exception e1) {
        e1.printStackTrace();
      }
    }
    return document;
  }
}
