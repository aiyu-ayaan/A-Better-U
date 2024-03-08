package com.ajs.core.use_cases

import com.ajs.core.database.journal.JournalDao
import com.ajs.core.database.journal.JournalModel
import javax.inject.Inject


data class JournalUseCases @Inject constructor(
    val insertJournalUseCase: InsertJournalUseCase,
    val getAllJournalUseCase: GetAllJournalUseCase,
    val getJournalByIdUseCase: GetJournalByIdUseCase,
    val deleteJournalByIdUseCase: DeleteJournalByIdUseCase,
    val deleteAllJournalUseCase: DeleteAllJournalUseCase
)


data class InsertJournalUseCase @Inject constructor(
    private val dao: JournalDao
) {
    suspend operator fun invoke(model: JournalModel) = dao.insertJournal(model)
}

data class GetAllJournalUseCase @Inject constructor(
    private val dao: JournalDao
) {
    operator fun invoke() = dao.getAllJournal()
}

data class GetJournalByIdUseCase @Inject constructor(
    private val dao: JournalDao
) {
    suspend operator fun invoke(id: Int) = dao.getJournalById(id)
}


data class DeleteJournalByIdUseCase @Inject constructor(
    private val dao: JournalDao
) {
    suspend operator fun invoke(id: Int) = dao.deleteJournalById(id)
}

data class DeleteAllJournalUseCase @Inject constructor(
    private val dao: JournalDao
) {
    suspend operator fun invoke() = dao.deleteAllJournal()
}
