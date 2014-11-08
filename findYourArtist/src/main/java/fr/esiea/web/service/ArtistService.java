package fr.esiea.web.service;

import java.util.List;

import fr.esiea.web.entity.Artist;

public interface ArtistService {
	public List<Artist> listArtist();
	public Artist getArtistById(int id);
}
