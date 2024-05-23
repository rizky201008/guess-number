package com.vixiloc.guessnumber.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnalyzeAnswer {
    operator fun invoke(userAnswer: Int, answer: Int): Flow<Boolean> = flow {
        emit(userAnswer == answer)
    }
}