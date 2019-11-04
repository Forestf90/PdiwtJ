package com.clarity;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


@ManagedBean(name="mapService", eager=true)

public class MapService {
  private static final String APPID = "AIzaSyDnxh2m-QEDmdYV_bDn-mPQAEDzg1J9CrM";
  private static final long serialVersionUID = 1L;
  
  public String getMap() {
	String[] urls = getMap("2033 Dove Creek Ct.", "Loveland", "CO");
	return urls[0];
  }
  public String[] getMap(String streetAddress, String city,
      String state) {
    String[] urls = new String[12];
    boolean cannotAccessWebService = false;
    
    for (int i=1; i <= urls.length; ++i) {
      Document document = getMapDocumentFromWebService(
          streetAddress, city, state, APPID, i);
      
      if (document == null) {
        System.out.print("asfadsgdsg");
        cannotAccessWebService = true;
        break;
      }
      urls[i-1] = getMapUrlFromDocument(document);
      System.out.println(urls[i-1]);
    }
    
    if (cannotAccessWebService) {
      for (int i=1; i <= urls.length; ++i) {
        urls[i-1] = "images/maps/map-" + i + ".png";
      }      
    }
        urls[0] ="http://api.tomtom.com/map/1/staticimage?key=VFOCTqfcLytxrjC1REdaxEcempifsfWY&zoom=9&center=13.567893,46.112341&format=jpg&layer=basic&style=main&width=1305&height=748&view=Unified";
    return urls;
  }
  private String getMapUrlFromDocument(Document document) {
    NodeList result =
        (NodeList) document.getElementsByTagName("Result");

    Element mapUrl = (Element) result.item(0);
    System.out.println(result);
    return mapUrl.getFirstChild().getNodeValue();
  }
  private Document getMapDocumentFromWebService(
      String streetAddress, String city, String state,
      String appid, int zoomLevel) {
    String url =
        "http://api.local.yahoo.com/MapsService/V1"
            + "/mapImage?appid=" + appid + "&street="
            + encode(streetAddress) + "&city=" + city
            + "&state=" + state + "&image_width=300"
            + "&image_height=300"
            + "&zoom=" + zoomLevel;
    System.out.println(url);
    //url ="https://maps.googleapis.com/maps/api/js?key=AIzaSyDnxh2m-QEDmdYV_bDn-mPQAEDzg1J9CrM&callback=initMap";
    url = "http://api.tomtom.com/map/1/staticimage?key=VFOCTqfcLytxrjC1REdaxEcempifsfWY&zoom=9&center=13.567893,46.112341&format=jpg&layer=basic&style=main&width=1305&height=748&view=Unified";
    return getDocumentFromUrl(url);
  }
  private Document getDocumentFromUrl(String url) {
    HttpClient client = new HttpClient();
    GetMethod get = new GetMethod(url);
    Document document = null;

    try {
      int result = client.executeMethod(get);
      if (result == 200) {
        InputStream in = get.getResponseBodyAsStream();
      document =
            DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(in);
      }
    }
    catch (Exception e) {
      //System.out.println(e);
      //throw e;
      return null;
    }
    return document;
  }
  private String encode(String streetAddress) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < streetAddress.length(); ++i) {
      if (streetAddress.charAt(i) == ' ')
        buffer.append('+');
      else
        buffer.append(streetAddress.charAt(i));
    }
    return buffer.toString();
  }
}
