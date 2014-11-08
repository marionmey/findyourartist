package fr.esiea.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {

	private int id;
	private String name;
	private String style;
	private int idShop;
	
	/**
	 * @param id
	 * @param name
	 * @param style
	 * @param idShop
	 */
	public Artist(int id, String name, String style, int idShop) {
		super();
		this.id = id;
		this.name = name;
		this.style = style;
		this.idShop = idShop;
	}
	
	/**
	 * 
	 */
	public Artist() {
		super();
	}

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "style")
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	@Column(name = "id_shop")
	public int getIdShop() {
		return idShop;
	}
	public void setIdShop(int idShop) {
		this.idShop = idShop;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", style=" + style
				+ ", idShop=" + idShop + "]";
	}
	
	
}
