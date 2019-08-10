package api.netflix.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "identification_number")
    Long identificationNumber;

    @Column(name = "client_name")
    String clientName;

    @OneToMany(mappedBy = "client")

    private List<Movie> movies = new ArrayList<>();

    public Client() {
    }

    public Client(Long identificationNumber, String clientName) {
        this.identificationNumber = identificationNumber;
        this.clientName = clientName;
    }

  /*  public Client(int identificationNumber, String clientName, Set<Movie> movie) {
        this.identificationNumber = identificationNumber;
        this.clientName = clientName;
        this.movie = movie;
    }
*/
    public int getId() {
        return id;
    }

    //public void setId(int id) {
    //    this.id = id;
    //}

    public Long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Long identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<Movie> getMovie() {
        return movies;
    }

    public void setMovie(List<Movie> movie) {
        this.movies = movie;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }


}
