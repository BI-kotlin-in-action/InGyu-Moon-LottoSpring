package com.example.LottoSpring.service

import com.example.LottoSpring.data.dto.MemberDto
import com.example.LottoSpring.exception.InsufficientBalanceException
import com.example.LottoSpring.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberDepositService(private val memberRepository: MemberRepository) {
    companion object {
        private const val LOTTO_PRICE = 1000
    }
    fun getMemberDeposit(memberId: Long): Long{
        return memberRepository.findById(memberId).get().deposit
    }

    fun chargeMemberDeposit(chargeValue: Long, memberId: Long): Long {
        val member = memberRepository.findById(memberId).get()
        memberRepository.findById(memberId).get().deposit += chargeValue
        memberRepository.save(member)
        return member.deposit
    }

    @Transactional
    fun buyLotto(totalLottoNumber: Int, memberId: Long) {
        val member = memberRepository.findById(memberId).get()
        if (member.deposit >= totalLottoNumber * LOTTO_PRICE) {
            member.deposit -= totalLottoNumber * LOTTO_PRICE
            memberRepository.save(member)
        } else {
            throw InsufficientBalanceException("잔액이 부족합니다.")
        }
    }
    fun withdrawDeposit(withdrawValue: Long, memberId: Long): Long{
        val member = memberRepository.findById(memberId).get()
        member.deposit -= withdrawValue
        memberRepository.save(member)
        return member.deposit
    }
}
