package api.netflix.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;



@Entity
@Table(name="genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "category_name")
    String genreName;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore

    private List<Movie> movies;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre(String GenreName, List<Movie> movies) {
        this.genreName = GenreName;
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }


}
