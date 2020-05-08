package com.cg.moviemgmt.service;

import java.util.List;

import com.cg.moviemgmt.entities.Movie;

public interface IMovieService {

	Movie save(Movie m);
	List<Movie> fetchAll() ;
	Movie fetchById(int movieId) ;
	String delete(int movieid) ;
	
}
