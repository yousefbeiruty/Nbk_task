package com.example.domain.common




interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter?=null): Result
}