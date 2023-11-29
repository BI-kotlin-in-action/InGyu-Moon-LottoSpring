package com.example.LottoSpring.domain.lotto

class Lotto(var numbers: Set<Int>) {
    constructor(inputNumbers: List<Int>) : this(inputNumbers.toSortedSet())
}
