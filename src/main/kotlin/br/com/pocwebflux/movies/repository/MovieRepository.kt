package br.com.pocwebflux.movies.repository

import br.com.pocwebflux.movies.model.Movie
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : ReactiveCrudRepository<Movie, String>