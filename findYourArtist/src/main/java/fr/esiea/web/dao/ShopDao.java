package fr.esiea.web.dao;

import java.util.List;

import fr.esiea.web.entity.Shop;

public interface ShopDao {
	public List<Shop> listShop();
	public Shop getShopById(int id);
}
