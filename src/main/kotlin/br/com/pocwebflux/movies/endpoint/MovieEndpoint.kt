package br.com.pocwebflux.movies.endpoint

import br.com.pocwebflux.movies.model.Movie
import br.com.pocwebflux.movies.repository.MovieRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/movie")
class MovieEndpoint(val repository: MovieRepository) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@RequestBody movie: Movie): Mono<Movie> {
        movie.id = UUID.randomUUID().toString()
        return repository.save(movie)
    }

}