package com.example.LottoSpring.controller

import com.example.LottoSpring.data.dto.NewLottoRequest
import com.example.LottoSpring.domain.lotto.LottoTickets
import com.example.LottoSpring.domain.lotto.LottoTickets.Companion.mergeLottoTickets
import com.example.LottoSpring.exception.InsufficientBalanceException
import com.example.LottoSpring.service.LottoService
import com.example.LottoSpring.service.MemberDepositService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lotto")
class LottoController(private val lottoService: LottoService, private val memberDepositService: MemberDepositService) {
    @PostMapping
    fun newLotto(@RequestBody request: NewLottoRequest): ResponseEntity<LottoTickets> {
        memberDepositService.buyLotto(request.totalLottoNumber, request.memberId)
        val autoLottoTickets = lottoService.newAutoLotto(request.autoLottoNumber, request.memberId)

        val manualLottoTickets = lottoService.newManualLotto(request.manualLottoTickets, request.memberId)

        return ResponseEntity.status(HttpStatus.CREATED).body(mergeLottoTickets(autoLottoTickets, manualLottoTickets))
    }
}
