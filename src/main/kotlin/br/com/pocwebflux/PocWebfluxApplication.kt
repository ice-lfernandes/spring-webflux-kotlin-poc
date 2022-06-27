package br.com.pocwebflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PocWebfluxApplication

fun main(args: Array<String>) {
	runApplication<PocWebfluxApplication>(*args)
}
