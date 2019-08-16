package api.netflix.Controller;

import api.netflix.Model.Client;
import api.netflix.Model.Genre;
import api.netflix.Model.Movie;
import api.netflix.NotFoundException;
import api.netflix.Repository.ClientRepository;
import api.netflix.Repository.GenreRepo;
import api.netflix.Repository.GenreRepository;
import api.netflix.Repository.MovieRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value="client")
public class ClientController {


    private ClientRepository clientRepository;
    private MovieRepository movieRepository;
    private GenreRepository genreRepository;
    private GenreRepo genreRepo;

    public ClientController(ClientRepository clientRepository, MovieRepository movieRepository, GenreRepository genreRepository, GenreRepo genreRepo) {
        this.clientRepository = clientRepository;
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.genreRepo = genreRepo;
    }


/*
GETTING A LIST OF ALL THE CLIENTS IN THE SYSTEM
GET http://localhost:9090/client/
 */
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

 /*CREATING A NEW CLIENT
  POST http://localhost:9090/client
   */
    @PostMapping
    public Client createClient(@RequestBody Client client) {

         return    clientRepository.save(client);
       }

/*EDITING DETAILS OF A CLIENT
  PATCH http://localhost:9090/client/14/update
  */
    @PatchMapping(value = "{id}/update")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        if (clientRepository.existsById((long) Math.toIntExact(id))) {
            Client updateClient = clientRepository.findClientById(Math.toIntExact(id));
            updateClient.setClientName(client.getClientName());
            //updateClient.setIdentificationNumber(client.getIdentificationNumber());

            clientRepository.save(updateClient);

        } else {


            NotFoundException notFoundException = new NotFoundException("No client with such id is available for update");
        }
        return client;
    }

/*
 DELETING A CLIENT BY ID
 DELETE http://localhost:9090/client/deleteClient/14
*/
    @DeleteMapping(value = "deleteCluent/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientRepository.deleteById(clientId);
    }

 /*
 GETTING A LIST OF ALL THE AVAILABLE MOVIES
 GET http://localhost:9090/client/movies
 */
    @GetMapping(value = "movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

/*
SEARCHING BY MOVIE TYPE(Original or suggested )
PATCH http://localhost:9090/client/movies/type?movietype=original
 */
    @GetMapping(value = "type")
    public  List<Movie> searchingByType(@RequestParam String movietype){

        return  movieRepository.findByMovieTypeIsLike(movietype);
    }


/*
POSTING A MOVIE WITH 2 CATEGORIES USING THE CLIENT ID
POST  http://localhost:9090/client/14/addMovie/3/4
 */
    @PostMapping(value = "{clientId}/addMovie/{genre3}/{genre4}")
    public Movie createMovie(@PathVariable int clientId, @PathVariable Long genre3, @PathVariable Long genre4, @RequestBody Movie movie) {
        if (clientRepository.existsClientById(clientId)) {
            Client client = clientRepository.findClientById(clientId);
            Genre genre1= genreRepository.findById(genre3).orElseThrow(() -> new NotFoundException("Incorrect genre selected: " + genre3));

            movie.addMovieGenre(genre1);

            Genre genre2 = genreRepository.findById(genre4).orElseThrow(() -> new NotFoundException("Incorrect genre selected: " + genre4));

            movie.addMovieGenre(genre2);



            movie.setClient(client);
            movieRepository.save(movie);
        } else {
           // clientRepository.findById((Int) clientId).orElseThrow(() -> new NotFoundException("No client with ID: " + clientId + " available for update"));
        }
        return movie;
    }
/*
PATCHING/EDITING A MOVIE SUGGESTED BY CLIENT
PATCH http://localhost:9090/client/14/editMovie/11
 */
    @PatchMapping(value = "{clientId}/editMovie/{movieId}")
    public Movie patchMovie(@PathVariable Long clientId, @PathVariable Long movieId, @RequestBody Movie movie) {
        if (clientRepository.existsById(clientId)) {
            Movie updating = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException("No movie with id " + movie.getId() + " is available for update"));
            Client updatingClient = clientRepository.findClientByIdentificationNumber(clientId);
            updating.setMovieName(movie.getMovieName());
            updating.setMovieType(movie.getMovieType());
            updating.setClient(updatingClient);
            movieRepository.save(updating);
        } else {
            clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("No client with ID: " + clientId + " exists"));
        }
        return movie;
    }
/*
DELETING MOVIE BY ID
DELETE http://localhost:9090/client/14/deleteMovie/11
 */
    @DeleteMapping(value = "{clientId}/deleteMovie/{id}")
    public void deleteMovie(@PathVariable Long clientId, @PathVariable Long movieId) {
        if (clientRepository.existsById(clientId)) {
            Client deletingClient = clientRepository.findClientByIdentificationNumber(clientId);
            movieRepository.deleteById(movieId);
        } else {
            clientRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Client of Movie entered does not Exit"));
        }
    }
/*
GETTING LIST OF GENRES
GET http://localhost:9090/client/genre
 */
    @GetMapping(value = "/genre")
    public List<Genre> genre() {
        return genreRepository.findAll();
    }


}
