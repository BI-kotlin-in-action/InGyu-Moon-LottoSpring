package com.example.LottoSpring.controller

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
    @PostMapping("/new")
    fun newLotto(@RequestBody request: NewLottoRequest): ResponseEntity<out Any> {
        try {
            memberDepositService.buyLotto(request.totalLottoNumber, request.memberId)
        } catch (e: InsufficientBalanceException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잔액이 부족하여 로또를 구매할 수 없습니다.")
        }
        val autoLottoTickets = lottoService.newAutoLotto(request.autoLottoNumber, request.memberId)

        // TODO: 수동 로또 범위 검사
        val manualLottoTickets = lottoService.newManualLotto(request.manualLottoTickets, request.memberId)

        if (request.totalLottoNumber == request.autoLottoNumber) {
            return ResponseEntity.status(HttpStatus.CREATED).body(autoLottoTickets)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mergeLottoTickets(autoLottoTickets, manualLottoTickets))
    }
    data class NewLottoRequest(
        val totalLottoNumber: Int,
        val autoLottoNumber: Int,
        val manualLottoTickets: LottoTickets,
        val memberId: Long,
    )
}
