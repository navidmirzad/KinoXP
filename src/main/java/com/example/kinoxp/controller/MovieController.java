package com.example.kinoxp.controller;

import com.example.kinoxp.dto.PostMovieDTO;
import com.example.kinoxp.model.Admin;
import com.example.kinoxp.model.Genre;
import com.example.kinoxp.model.Movie;
import com.example.kinoxp.repositories.GenreRepository;
import com.example.kinoxp.repositories.MovieRepository;
import com.example.kinoxp.service.ApiServiceGetMovies;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class MovieController {

    @Autowired
    private ApiServiceGetMovies apiServiceGetMovies;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieRepository movieRepository;
    // get all movies from imdb api. shouldn't be called that often - 100 calls per day

    @GetMapping("/api/admin/imdb")
    public List<Movie> getImdbMovies() {
        List<Movie> movies = apiServiceGetMovies.getMovies();
        return movies;
    }

    @GetMapping("/kinoxp")
    public List<Movie> frontpageMovies() {
        return movieRepository.findAll();
    }

    @PostMapping("/addmovie")
    public ResponseEntity<Movie> addMovie(@RequestBody PostMovieDTO postMovieDTO) {
        Movie movie = new Movie();
        movie.setTitle(postMovieDTO.getTitle());
        movie.setDescription(postMovieDTO.getDescription());
        movie.setImage(postMovieDTO.getImage());
        movie.setYear(postMovieDTO.getYear());
        List<Genre> genres = genreRepository.findAllById(postMovieDTO.getGenres());
        Set<Genre> setGenre = new HashSet<>();
        genres.forEach(a -> setGenre.add(a));
        movie.setGenres(setGenre);
        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> findMovie(@PathVariable Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> editMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        Optional<Movie> findingMovie = movieRepository.findById(id);
        if (findingMovie.isPresent()) {
            movie.setId(id);
            movieRepository.save(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



   // @PutMapping("/editmovie")

 /*
    @GetMapping("/editaccount")
    public String editAccount(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user"); // vi det vi har gemt/sendt med i sessions. Her er det så User objektet.
        user = accountService.getUserById(user.getUserID()); // fra det User objekt vi fik med i sessions. Så får vi det userID bundet til den user vi gav med i sessions
        // hvilket er den vi loggede ind med. Så vores
        model.addAttribute("user", user); // Vi tilføjer vores user som vi fik med fra vores session til model, da vi skal vise alt dataen der hører til den bruger
        // da det er editAccount og man skal kunne se de informationer man evt. vil ændre.
        model.addAttribute("roles", Role.values()); // (Role.values() returnerer et array af værdierne 'Role') for at vise rollerne hvis man vil ændre dem.
        return "editaccount";
    }

    @PostMapping("editaccount")
    public String editedAccount(HttpSession session, User editedUser) {
        User user = (User) session.getAttribute("user"); // her tager vi det User objekt vi sendte med i sessions tidligere.
        accountService.editAccount(user.getUserID(), editedUser); // vi returner også den nye User som editedUser
        // Her kalder metoden til accountService.editAccount og beder den om at ændre brugeren
        // med det userID der er forbundet til den bruger vi er logget ind med. Muligt pga. sessions.
        return "redirect:/frontpage";
    }*/

}
