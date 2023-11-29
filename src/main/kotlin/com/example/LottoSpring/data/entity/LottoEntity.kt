package com.example.LottoSpring.data.entity

import com.example.LottoSpring.domain.lotto.Lotto
import com.example.LottoSpring.domain.lotto.LottoTickets
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "LOTTO")
class LottoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOTTO_ID")
    var lottoId: Long = 0

    @Column(name = "NUMBER_1")
    var number1: Int = 0

    @Column(name = "NUMBER_2")
    var number2: Int = 0

    @Column(name = "NUMBER_3")
    var number3: Int = 0

    @Column(name = "NUMBER_4")
    var number4: Int = 0

    @Column(name = "NUMBER_5")
    var number5: Int = 0

    @Column(name = "NUMBER_6")
    var number6: Int = 0

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    lateinit var member: MemberEntity

    private fun setMemberEntity(member: MemberEntity) {
        this.member = member
        member.lottoTickets.add(this)
    }

    companion object {
        fun createLotto(member: MemberEntity, lottoTicket: Lotto): LottoEntity {
            val lottoEntity = LottoEntity()
            lottoEntity.number1 = lottoTicket.numbers.elementAt(0)
            lottoEntity.number2 = lottoTicket.numbers.elementAt(1)
            lottoEntity.number3 = lottoTicket.numbers.elementAt(2)
            lottoEntity.number4 = lottoTicket.numbers.elementAt(3)
            lottoEntity.number5 = lottoTicket.numbers.elementAt(4)
            lottoEntity.number6 = lottoTicket.numbers.elementAt(5)

            lottoEntity.setMemberEntity(member)
            return lottoEntity
        }
    }
}
