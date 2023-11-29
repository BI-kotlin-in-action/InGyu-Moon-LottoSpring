package com.example.LottoSpring.service

import com.example.LottoSpring.exception.InsufficientBalanceException
import com.example.LottoSpring.exception.NegativeValueException
import com.example.LottoSpring.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberDepositService(private val memberRepository: MemberRepository) {
    companion object {
        private const val LOTTO_PRICE = 1000
    }
    fun getMemberDeposit(memberId: Long): Long {
        return memberRepository.findById(memberId).get().deposit
    }

    @Transactional
    fun chargeMemberDeposit(chargeValue: Long, memberId: Long): Long {
        if (chargeValue < 0) {
            throw NegativeValueException("충전 금액은 음수일 수 없습니다.")
        }
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
    @Transactional
    fun withdrawDeposit(withdrawValue: Long, memberId: Long): Long {
        val member = memberRepository.findById(memberId).get()
        if (withdrawValue > member.deposit) {
            throw NegativeValueException("현재 충전금액 보다 큰 금액을 출금할 수 없습니다.")
        }
        member.deposit -= withdrawValue
        memberRepository.save(member)
        return member.deposit
    }
}
