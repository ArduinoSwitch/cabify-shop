package com.cabify.domain.usecase

interface UseCase<Params, Return> {
    operator fun invoke(params: Params): Return
}
