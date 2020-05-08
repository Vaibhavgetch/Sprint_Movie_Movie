package com.cg.moviemgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.moviemgmt.dao.MovieDao;
import com.cg.moviemgmt.entities.Movie;
import com.cg.moviemgmt.exceptions.InvalidArgumentException;
import com.cg.moviemgmt.exceptions.TheaterNotFoundException;

@Service
@Transactional
public class MovieServiceimpl implements IMovieService {
	private MovieDao dao;

	public MovieDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(MovieDao dao) {
		this.dao = dao;
	}

	
	public Movie save(Movie m) {

		if (m == null) {
			throw new InvalidArgumentException("Movie can't be Null");
		}

		Movie mov = dao.save(m);
		return mov;
	}

	
	public List<Movie> fetchAll() {

		List<Movie> movies = dao.findAll();
		return movies;
	}

	
	public Movie fetchById(int movieId) {

		Optional<Movie> optional = dao.findById(movieId);
		if (optional.isPresent()) {
			Movie mov = optional.get();
			return mov;
		}
		throw new TheaterNotFoundException("Movie not found for id=" + movieId);
	}

	
	public String delete(int movieId) {

		Movie movie = fetchById(movieId);
		dao.delete(movie);
		return "Deleted Succesully";
	}

}
