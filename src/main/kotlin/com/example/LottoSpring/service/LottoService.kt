package com.example.LottoSpring.service

import com.example.LottoSpring.data.entity.LottoEntity
import com.example.LottoSpring.data.entity.LottoEntity.Companion.createLotto
import com.example.LottoSpring.domain.lotto.LottoGenerator.Companion.makeLottoTickets
import com.example.LottoSpring.domain.lotto.LottoTickets
import com.example.LottoSpring.repository.LottoRepository
import com.example.LottoSpring.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class LottoService(private val lottoRepository: LottoRepository, private val memberRepository: MemberRepository) {

    fun newAutoLotto(autoLottoNumber: Int, memberId: Long): LottoTickets {
        val lottoEntity = LottoEntity()
        val lottoTickets = makeLottoTickets(autoLottoNumber)

        for (lotto in lottoTickets.tickets) {
            val createLotto = createLotto(memberRepository.findById(memberId).get(), lotto)
            lottoRepository.save(createLotto)
        }
        return lottoTickets
    }
    fun newManualLotto(lottoTickets: LottoTickets, memberId: Long): LottoTickets {
        val lottoEntity = LottoEntity()

        for (lotto in lottoTickets.tickets) {
            // TODO: 로또 숫자 범위 검사
            val createLotto = createLotto(memberRepository.findById(memberId).get(), lotto)
            lottoRepository.save(createLotto)
        }

        return lottoTickets
    }
}
