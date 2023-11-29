package com.example.LottoSpring.controller

import com.example.LottoSpring.data.dto.DepositRequest
import com.example.LottoSpring.data.dto.DepositResponse
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

    @PostMapping
    fun newMember(@RequestBody memberDto: MemberDto): ResponseEntity<MemberDto> {
        val saveNewMember = memberService.saveNewMember(memberDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(saveNewMember)
    }

    @PostMapping("/charge")
    fun chargeDeposit(@RequestBody request: DepositRequest): ResponseEntity<DepositResponse> {
        val memberDeposit = memberDepositService.chargeMemberDeposit(request.value, request.memberId)
        val depositResponse = DepositResponse(memberDeposit, request.value)

        return ResponseEntity.status(HttpStatus.OK).body(depositResponse)
    }

    @PostMapping("/withdraw")
    fun withdrawDeposit(@RequestBody request: DepositRequest): ResponseEntity<DepositResponse> {
        val afterWithdrawDeposit = memberDepositService.withdrawDeposit(request.value, request.memberId)
        val depositResponse = DepositResponse(afterWithdrawDeposit, request.value)

        return ResponseEntity.status(HttpStatus.OK).body(depositResponse)
    }
}
