package com.example.LottoSpring.service

import com.example.LottoSpring.data.dto.MemberDto
import com.example.LottoSpring.data.entity.MemberEntity
import com.example.LottoSpring.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun saveNewMember(memberDto: MemberDto): MemberDto {
        val member = MemberEntity()
        member.memberEmail = memberDto.memberEmail
        member.password = memberDto.password
        member.nickname = memberDto.nickname

        memberRepository.save(member)
        return memberDto
    }

    fun deleteMember(memberId: Long) {
        memberRepository.deleteById(memberId)
    }
}
