package fr.esiea.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.esiea.web.entity.Artist;
import fr.esiea.web.entity.Shop;

@Repository
public class ArtistDaoImpl implements ArtistDao{

	private static final Logger logger = LoggerFactory.getLogger(ArtistDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public List<Artist> listArtist() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Artist> ArtistList = session.createQuery("from Artist").list();
		for(Artist p : ArtistList){
			logger.info("Artist List::"+p);
		}
		return ArtistList;
	}

	@Override
	public Artist getArtistById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Artist p = (Artist) session.load(Artist.class, new Integer(id));
		logger.info("Artist loaded successfully, Artist details="+p);
		return p;
	}
}
