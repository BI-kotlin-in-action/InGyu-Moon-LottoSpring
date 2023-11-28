package com.example.LottoSpring.domain.lotto
class LottoGenerator {
    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
        private val numbers: List<Int> = (LottoNumber.MIN_NUMBER..LottoNumber.MAX_NUMBER).toList()
        fun makeLottoTickets(lottoPurchaseAmount: Int): LottoTickets {
            val lottoTickets = mutableListOf<Lotto>()
            repeat(lottoPurchaseAmount) {
                lottoTickets.add(generateLottoNumbers())
            }
            return LottoTickets(lottoTickets)
        }
        private fun generateLottoNumbers(): Lotto {
            return Lotto(numbers.shuffled().take(LOTTO_NUMBER_COUNT))
        }
    }
}
