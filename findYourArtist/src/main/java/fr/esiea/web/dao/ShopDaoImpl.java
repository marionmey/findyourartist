package fr.esiea.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.esiea.web.entity.Shop;

@Repository
public class ShopDaoImpl implements ShopDao{

	private static final Logger logger = LoggerFactory.getLogger(ShopDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public List<Shop> listShop() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Shop> ShopList = session.createQuery("from Shop").list();
		for(Shop p : ShopList){
			logger.info("Shop List::"+p);
		}
		return ShopList;
	}

	@Override
	public Shop getShopById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Shop p = (Shop) session.load(Shop.class, new Integer(id));
		logger.info("Shop loaded successfully, Shop details="+p);
		return p;
	}
}
