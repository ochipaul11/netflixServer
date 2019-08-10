package api.netflix;

import api.netflix.Model.Genre;
import api.netflix.Model.Client;
import api.netflix.Model.Movie;
import api.netflix.Repository.GenreRepository;
import api.netflix.Repository.ClientRepository;
import api.netflix.Repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyData implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ClientRepository clientRepository;
    private final GenreRepository genreRepository;

    public DummyData(MovieRepository movieRepository, ClientRepository clientRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.clientRepository = clientRepository;
        this.genreRepository = genreRepository;
    }

    public void run(String... args) throws Exception{

       Client client0 = new Client((long)0,"paul");
       clientRepository.save(client0);
       Client client2 =clientRepository.save( new Client((long) 24235419,"NETFLIX"));
       clientRepository.save(client2);

        Genre genre1 = new Genre("Action");
        genreRepository.save(genre1);
        Genre genre2 = new Genre("Horror");
        genreRepository.save(genre2);
        Genre genre3 = new Genre("comedy");
        genreRepository.save(genre3);
        Genre genre4 = new Genre("Fantasy");
        genreRepository.save(genre4);
        Genre genre5 = new Genre("Thriller");
        genreRepository.save(genre5);
        Genre genre6 = new Genre("Romance");
        genreRepository.save(genre6);
        Genre genre7 = new Genre("Drama");
        genreRepository.save(genre7);

        Movie movie1 = movieRepository.save(new Movie("Polar","original",client2));
        movie1.addMovieGenre(genre1);
        movie1.addMovieGenre(genre5);
        movieRepository.save(movie1);
        Movie movie2 = movieRepository.save(new Movie("Creep","original",client2));
        movie2.addMovieGenre(genre2);
        movie2.addMovieGenre(genre5);
        movieRepository.save(movie2);
        Movie movie3 = movieRepository.save(new Movie("Blue Jay","original",client2));
        movie3.addMovieGenre(genre6);
        movieRepository.save(movie3);
        Movie movie4 = movieRepository.save(new Movie("The Week Of","original",client2));
        movie4.addMovieGenre(genre7);
        movie4.addMovieGenre(genre3);
        movieRepository.save(movie4);
    }


}
