package fr.esiea.web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esiea.web.dao.ShopDao;
import fr.esiea.web.entity.Shop;
@Service
public class ShopServiceImpl implements ShopService{
	
	private ShopDao shopDao;

	public void setShopDAO(ShopDao shopDao) {
		this.shopDao = shopDao;
	}

	@Override
	@Transactional
	public List<Shop> listShop() {
		return this.shopDao.listShop();
	}

	@Override
	@Transactional
	public Shop getShopById(int id) {
		return this.shopDao.getShopById(id);
	}
}
