package api.netflix.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "movie_name")
    private String movieName;

    @Column
    private String movieType;

    @ManyToMany(cascade=CascadeType.ALL)
    @Column(name = "moviegenre")
    @JoinTable(name = "genres_movies",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "genreId"))
    private List<Genre> genres = new ArrayList<>();

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "clientId")
    private Client client;


    public Movie() {
    }

    public Movie(String movieName, String movieType) {
        this.movieName = movieName;
        this.movieType = movieType;

    }

    public Movie(String movieName, String movieType, Client client) {
        this.movieName = movieName;
        this.movieType = movieType;
       // this.genres = genres;
        this.client = client;
    }

    /* public Movie(String movieName, String movieType, List<Genre> genres, Client client) {
        this.movieName = movieName;
        this.movieType = movieType;
        this.genres = genres;
        this.client = client;
    }
*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

        public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addMovieGenre(Genre genre){
        genres.add(genre);
    }

    public void addMovieGenre(Set<Genre> genre) {
        genres.addAll(genre);
    }
}
