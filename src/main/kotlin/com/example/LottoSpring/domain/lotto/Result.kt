package domain

import com.example.LottoSpring.domain.lotto.LottoTickets

class Result {
    private val result: HashMap<Rank, Int> = hashMapOf<Rank, Int>()
    fun updateResult(winner: Set<Int>, tickets: LottoTickets): HashMap<Rank, Int> {
        for (ticket in tickets.tickets) {
            val matchingNumbers = winner.intersect(ticket.numbers).size
            val rank = Rank.getRank(matchingNumbers)
            result[rank] = result.getOrDefault(rank, 0) + 1
        }
        return result
    }
    fun calculateReward(): Int {
        var totalPrizeAmount = 0
        for (key in result.keys) {
            totalPrizeAmount += result.getValue(key) * key.prizeAmount
        }
        return totalPrizeAmount
    }
}
