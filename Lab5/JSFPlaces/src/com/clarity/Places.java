package com.clarity;

import com.clarity.Place;
import com.clarity.User;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.el.ELResolver;

@ManagedBean()
@SessionScoped

public class Places {
  private ArrayList<Place> places = null;
  
  private static SelectItem[] zoomLevelItems = {
    new SelectItem("1"),
    new SelectItem("2"),
    new SelectItem("3"),
    new SelectItem("4"),
    new SelectItem("5"),
    new SelectItem("6"),
    new SelectItem("7"),
    new SelectItem("8"),
    new SelectItem("9"),
    new SelectItem("10"),
    new SelectItem("11")
 };
  
  public void addPlace(String days,
		               String city, 
		               String height,
                       String width,
		               String[] mapUrls,
		               String weather) {
	if (places == null) {
      places  = new ArrayList<Place>();
	}
	
    Place place = new Place(days, city, height,width, mapUrls, weather);
    places.add(place);
  }
  
  public ArrayList<Place> getPlacesList() { 
	return places; 
  }
  
  public void setPlacesList(ArrayList<Place> places) {
	this.places = places;
  }
  
  public SelectItem[] getzoomLevelItems() {
	return zoomLevelItems;
  }
  
  public String getShowContent() {
    String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
    if ("/pages/login.xhtml".equals(viewId)
    	|| ("/pages/showSource.xhtml").equals(viewId)
    	|| ("/pages/places.xhtml".equals(viewId) && 
    			places != null && places.size() > 0)) {
      return "display";
    }
    
    else return "none";
  }

  public String logout() {
	FacesContext fc = FacesContext.getCurrentInstance();     
	ELResolver elResolver = fc.getApplication().getELResolver();
	     
	User user = (User)elResolver.getValue(
	           fc.getELContext(), null, "user");
	
	user.setName("");
	user.setPassword("");
	   
    setPlacesList(null);
	   
    return "login"; 
  }
}