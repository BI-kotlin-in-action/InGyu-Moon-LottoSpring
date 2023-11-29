package com.example.LottoSpring.domain.lotto

class LottoTickets(val tickets: List<Lotto>) {
    companion object {
        fun mergeLottoTickets(firstTicket: LottoTickets, secondTicket: LottoTickets): LottoTickets {
            val mergedTickets = firstTicket.tickets + secondTicket.tickets
            return LottoTickets(mergedTickets)
        }
    }
}
