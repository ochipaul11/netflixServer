package api.netflix.Repository;

import api.netflix.Model.Genre;
import api.netflix.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    List <Movie> findByMovieTypeLikeAndGenresIsContaining(String movieType,List<Genre>genres);
    List <Movie> findByGenres(Genre genre);
    List <Movie> findByMovieTypeIsLike(String movieType);
    List <Movie> findByMovieTypeLikeAndGenresEquals(String movieType,List<Genre>genres);

}