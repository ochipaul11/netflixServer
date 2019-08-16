package api.netflix.Controller;

import api.netflix.Model.Movie;
import api.netflix.Repository.GenreRepository;
import api.netflix.Repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="movie")
public class MovieController {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    


    public MovieController(GenreRepository genreRepository, MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;

    }
    /*
    GETTING A LIST OF ALL THE AVAILABLE MOVIES
    GET http://localhost:9090/movies
     */
    @GetMapping
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

/*
SEARCHING BY MOVIE TYPE(Original or suggested )
PATCH http://localhost:9090/movies/type?movietype=original
 */

    @GetMapping(value = "type")
    public  List<Movie> searchingByType(@RequestParam String movietype){

        return  movieRepository.findByMovieTypeIsLike(movietype);
    }


}
