package com.example.LottoSpring.domain.lotto

class LottoTickets(val tickets: List<Lotto>) {
    companion object {
        fun mergeLottoTickets(a: LottoTickets, b: LottoTickets): LottoTickets {
            val mergedTickets = a.tickets + b.tickets
            return LottoTickets(mergedTickets)
        }
    }
}
