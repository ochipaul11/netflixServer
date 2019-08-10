package api.netflix.Repository;

import api.netflix.Model.Client;
import api.netflix.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    //List<Genre> findGenreByMoviesContains

}
