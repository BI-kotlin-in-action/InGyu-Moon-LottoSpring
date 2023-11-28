package com.example.LottoSpring.data.entity

import jakarta.persistence.*

@Entity
@Table(name = "MEMBER")
class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    var memberId: Long = 0

    @Column(unique = true, name = "MEMBER_EMAIL")
    var memberEmail: String = ""

    @Column(name = "PASSWORD")
    var password: String = ""

    @Column(unique = true, name = "NICKNAME")
    var nickname: String = ""

    @Column(name = "DEPOSIT")
    var deposit: Long = 0

    @OneToMany(mappedBy = "member")
    val lottoTickets: MutableList<LottoEntity> = mutableListOf()
}
