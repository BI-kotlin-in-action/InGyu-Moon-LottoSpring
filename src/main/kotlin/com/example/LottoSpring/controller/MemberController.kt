package com.example.LottoSpring.controller

import com.example.LottoSpring.data.dto.MemberDto
import com.example.LottoSpring.service.MemberDepositService
import com.example.LottoSpring.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService, private val memberDepositService: MemberDepositService) {

    @PostMapping("/new")
    fun newMember(@RequestBody memberDto: MemberDto): ResponseEntity<MemberDto> {
        val saveNewMember = memberService.saveNewMember(memberDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(saveNewMember)
    }

    @PostMapping("/deposit")
    fun chargeDeposit(@RequestBody request: DepositRequest): ResponseEntity<String> {
        if (request.value < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("충전 금액은 음수일 수 없습니다.")
        }
        val memberDeposit = memberDepositService.chargeMemberDeposit(request.value, request.memberId)
        return ResponseEntity.status(HttpStatus.OK).body("<충전완료>\n" + request.value + "원이 충전되었습니다.\n" + "잔액: " + memberDeposit)
    }

    @PostMapping("/withdraw")
    fun withdrawDeposit(@RequestBody request: DepositRequest): ResponseEntity<String> {
        val memberDeposit = memberDepositService.getMemberDeposit(request.memberId)
        if (memberDeposit < request.value) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("현재 충전금액 보다 큰 금액을 출금할 수 없습니다.")
        }
        val afterWithdrawDeposit = memberDepositService.withdrawDeposit(request.value, request.memberId)
        return ResponseEntity.status(HttpStatus.OK).body("<출금 완료>\n" + request.value + "원이 출금되었습니다.\n" + "잔액: " + afterWithdrawDeposit)
    }

    data class DepositRequest(
        val value: Long,
        val memberId: Long,
    )
}
