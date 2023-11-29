package com.example.LottoSpring.data.dto

import com.example.LottoSpring.domain.lotto.LottoTickets

data class NewLottoRequest(
    val totalLottoNumber: Int,
    val autoLottoNumber: Int,
    val manualLottoTickets: LottoTickets,
    val memberId: Long,
)
