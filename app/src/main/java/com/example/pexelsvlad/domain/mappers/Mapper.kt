package com.example.pexelsvlad.domain.mappers

interface Mapper <IN, OUT> {
    fun map(input: IN): OUT
}