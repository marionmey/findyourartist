package fr.esiea.web.dao;

import java.util.List;

import fr.esiea.web.entity.Artist;

public interface ArtistDao {
	public List<Artist> listArtist();
	public Artist getArtistById(int id);
}
