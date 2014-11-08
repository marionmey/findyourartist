package fr.esiea.web.service;

import java.util.List;

import fr.esiea.web.entity.Shop;


public interface ShopService {
	public List<Shop> listShop();
	public Shop getShopById(int id);
}
