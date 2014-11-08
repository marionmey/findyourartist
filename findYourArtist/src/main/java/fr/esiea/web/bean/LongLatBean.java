package fr.esiea.web.bean;

import java.io.Serializable;

public class LongLatBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1254536544777284735L;
	
	
	
	
	/**
	 * 
	 */
	public LongLatBean() {
		super();
	}
	private Double longitude;
	private Double latitude;
	private String Label;
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	
	
	
}
