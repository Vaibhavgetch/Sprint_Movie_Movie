package com.cg.moviemgmt.exe;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.moviemgmt.dto.MovieRequestDto;
import com.cg.moviemgmt.entities.Movie;
import com.cg.moviemgmt.service.IMovieService;
import java.util.*;

@RestController
@RequestMapping("/movies")
public class MovieRestController {
	
	@Autowired
	private IMovieService movieService;
	
	@PostMapping("/add")
	private ResponseEntity<Movie> addMovie(@RequestBody MovieRequestDto movieDto){
		Movie movie = convertMovie(movieDto);
		movie = movieService.save(movie);
		ResponseEntity<Movie> response = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/get/{id}")
	private ResponseEntity<Movie> fetchById(@PathVariable("id") int movieId){;
		Movie movie = movieService.fetchById(movieId);
		ResponseEntity<Movie> response = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		return response;
	}
	
	@GetMapping
	ResponseEntity<List<Movie>> fetchAll(){
		List<Movie> movieList = movieService.fetchAll();
		ResponseEntity<List<Movie>> response = new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<String> delete(@PathVariable("id") int movieId)
	{
		String result  = movieService.delete(movieId);
		ResponseEntity<String> response = new ResponseEntity<String>(result, HttpStatus.OK);
		return response;
	}
	
	private Movie convertMovie(MovieRequestDto movieDto) {
		Movie movie = new Movie();
		movie.setMovieName(movieDto.getMovieName());
		movie.setMovieDirector(movieDto.getMovieDirector());
		movie.setMovieGenre(movieDto.getMovieGenre());
		movie.setMovieLength(movieDto.getMovieLength());
		movie.setMovieReleaseDate(movieDto.getMovieReleaseDate());
		List<String> list = movieDto.getLanguages();
		movie.setLanguages(list);
		return movie;
	}
	
	
}
