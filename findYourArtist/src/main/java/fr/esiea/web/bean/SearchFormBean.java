package fr.esiea.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.esiea.web.entity.Artist;
import fr.esiea.web.entity.Shop;

public class SearchFormBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3169736964675044225L;
	private int idPathSelectedStyle;
	private int idPathSelectedCity;
	
	private List<Artist> listArtist=new ArrayList<Artist>();
	private List<Shop>listShops=new ArrayList<Shop>();
	
	
	
	/**
	 * @param idPathSelectedStyle
	 * @param idPathSelectedCity
	 * @param listArtist
	 * @param listShops
	 */
	public SearchFormBean(int idPathSelectedStyle, int idPathSelectedCity,
			List<Artist> listArtist, List<Shop> listShops) {
		super();
		this.idPathSelectedStyle = idPathSelectedStyle;
		this.idPathSelectedCity = idPathSelectedCity;
		this.listArtist = listArtist;
		this.listShops = listShops;
	}
	
	
	
	
	
	
	/**
	 * 
	 */
	public SearchFormBean() {
		super();
	}






	public int getIdPathSelectedStyle() {
		return idPathSelectedStyle;
	}
	public void setIdPathSelectedStyle(int idPathSelectedStyle) {
		this.idPathSelectedStyle = idPathSelectedStyle;
	}
	public int getIdPathSelectedCity() {
		return idPathSelectedCity;
	}
	public void setIdPathSelectedCity(int idPathSelectedCity) {
		this.idPathSelectedCity = idPathSelectedCity;
	}
	public List<Artist> getListArtist() {
		return listArtist;
	}
	public void setListArtist(List<Artist> listArtist) {
		this.listArtist = listArtist;
	}
	public List<Shop> getListShops() {
		return listShops;
	}
	public void setListShops(List<Shop> listShops) {
		this.listShops = listShops;
	}
	
	
	
}
