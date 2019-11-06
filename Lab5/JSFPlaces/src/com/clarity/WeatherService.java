package com.clarity;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.util.*;
import java.util.Base64.Encoder;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ManagedBean(name="weatherService", eager=true)

public class WeatherService {
    private static final String YAHOO_APPLICATION_ID =
            "n57MqO36";
    private static final String WEATHER_BASE_URL =
            "https://weather-ydn-yql.media.yahoo.com/forecastrss";
    private static final String CLIENT_SECRET ="957b14ad4778f87435f1168a3b7279df92e7abeb";
    private static final String APP_ID ="xQQAvo58";
    private static final long serialVersionUID = 1L;

    private static final String CUSTOMER_SECRET="957b14ad4778f87435f1168a3b7279df92e7abeb";

    private static final String oauth_consumer_key ="dj0yJmk9VU11VHVYRkphbnBLJmQ9WVdrOWVGRlJRWFp2TlRnbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWJi";
    private static final String oauth_signature_method = "HMAC-SHA1";
    private static final String oauth_version ="1.0";
    private static final String oauth_signature = "6G/F55t61122s21JBdPNXZKeluo=";


    public Document getWeatherForZip(boolean isFarenheit, String city) throws Exception{

        long oauth_timestamp = new Date().getTime() / 1000;
        byte[] nonce = new byte[32];
        Random rand = new Random();
        rand.nextBytes(nonce);
        String oauth_nonce = new String(nonce).replaceAll("\\W", "");
        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + oauth_consumer_key);
        parameters.add("oauth_nonce=" + oauth_nonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + oauth_timestamp);
        parameters.add("oauth_version=1.0");
        // Make sure value is encoded
        parameters.add("location=" + URLEncoder.encode(city, "UTF-8"));
        //parameters.add("format=json");
        Collections.sort(parameters);

        StringBuffer parametersList = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }
        String signatureString = "GET&" +
                URLEncoder.encode(WEATHER_BASE_URL, "UTF-8") + "&" +
                URLEncoder.encode(parametersList.toString(), "UTF-8");

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

        String url = WEATHER_BASE_URL+"?"+parametersList.toString()+"&oauth_signature="+signature;
        String authorizationLine = "OAuth " +
                "oauth_consumer_key=\"" + oauth_consumer_key + "\", " +
                "oauth_nonce=\"" + oauth_nonce + "\", " +
                "oauth_timestamp=\"" + oauth_timestamp + "\", " +
                "oauth_signature_method=\"HMAC-SHA1\", " +
                "oauth_signature=\"" + signature + "\", " +
                "oauth_version=\"1.0\"";
        return getWeatherDocument(url, authorizationLine);
    }
    public String getWeatherFromDocument(Document document) {
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

    public String getLatFromDocument(Document document) {
        Element item =
                (Element) document.getElementsByTagName("item")
                        .item(0);
        Element geo =(Element) item.getElementsByTagName("geo:lat")
                .item(0);
        return geo.getFirstChild().getNodeValue();
    }

    public String getLongFromDocument(Document document) {
        Element item =
                (Element) document.getElementsByTagName("item")
                        .item(0);
        Element geo =(Element) item.getElementsByTagName("geo:long")
                .item(0);
        return geo.getFirstChild().getNodeValue();
    }


    private Document getWeatherDocument(String url, String authorizationLine) {

        Document document = null;
        try {
            HttpClient client = new HttpClient(); // Jakarta Commons

            GetMethod gm = new GetMethod(url);

            System.out.println(gm.getURI());
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
