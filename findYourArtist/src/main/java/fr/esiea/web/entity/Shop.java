package fr.esiea.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shop")
public class Shop {
	
	private int id;
	private String name;
	private int number;
	private String street;
	private int zipcode;
	private String city;
	
	/**
	 * @param id
	 * @param name
	 * @param number
	 * @param street
	 * @param zipcode
	 * @param city
	 */
	public Shop(int id, String name, int number, String street, int zipcode,
			String city) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}
	
	
	/**
	 * 
	 */
	public Shop() {
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
	
	@Column(name = "number")
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Column(name = "street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name = "zipcode")
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}


	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", number=" + number
				+ ", street=" + street + ", zipcode=" + zipcode + ", city="
				+ city + "]";
	}
	
	
}
