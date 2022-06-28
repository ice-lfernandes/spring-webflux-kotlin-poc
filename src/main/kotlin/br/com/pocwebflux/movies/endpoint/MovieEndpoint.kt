package br.com.pocwebflux.movies.endpoint

import br.com.pocwebflux.movies.exception.NotFoundException
import br.com.pocwebflux.movies.model.Movie
import br.com.pocwebflux.movies.repository.MovieRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
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

    @GetMapping(produces = ["application/stream+json"])
    fun get(): Flux<Movie> {
        return repository.findAll()
            .delayElements(Duration.ofSeconds(3))
    }

    @PutMapping
    fun put(@RequestBody movie: Movie): Mono<Movie> {
        return repository.save(movie)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: String): Mono<Void> {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(NotFoundException))
            .flatMap { movie -> repository.delete(movie) }
            .then(Mono.empty())
    }

}