package com.example.LottoSpring.repository

import com.example.LottoSpring.data.entity.LottoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LottoRepository : JpaRepository<LottoEntity, Long>
