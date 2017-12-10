package pl.javastart.minifilmweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/api/movies")
    public List<Movie> getAllMovies() {
        System.out.println("Odpytanie o wszystkie filmy");
        return movieRepository.findAll();
    }


    @GetMapping("/api/movies/{id}")
    public ResponseEntity getMovie(@PathVariable Long id) {
        Movie movie = movieRepository.findOne(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/movies")
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return ResponseEntity.ok(savedMovie);
    }

    @PutMapping("/api/movies/{id}")
    public ResponseEntity changeMovie(@PathVariable long id, @RequestBody Movie movie) {
        Movie existMovie = movieRepository.findOne(id);
        if (existMovie != null) {
            movie.setId(existMovie.getId());
            movie.setTitle(existMovie.getTitle());
            movie.setPremiereDate(existMovie.getPremiereDate());
            movieRepository.save(movie);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/movies/{id}")
    public ResponseEntity deleteMovie(@PathVariable long id) {
        Movie movie = movieRepository.findOne(id);
        if (movie != null) {
            movieRepository.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
