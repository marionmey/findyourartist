package fr.esiea.web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esiea.web.dao.ArtistDao;
import fr.esiea.web.entity.Artist;

@Service
public class ArtistServiceImpl implements ArtistService{

	private ArtistDao artistDao;

	public void setArtistDAO(ArtistDao artistDao) {
		this.artistDao = artistDao;
	}

	@Override
	@Transactional
	public List<Artist> listArtist() {
		return this.artistDao.listArtist();
	}

	@Override
	@Transactional
	public Artist getArtistById(int id) {
		return this.artistDao.getArtistById(id);
	}
}
