package com.ajs.core.database.journal


import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
fun getColorList(): List<Int> {
    return listOf(
        0xFFFCF0F0,
        0xFFFDEDE0,
        0xFFFADED5,
        0xFFFCE1CF,
        0xFFFBFDE1,
        0xFFEFFED9,
        0xFFF8FFD1,
        0xFFEEFEC4,
        0xFFDBFEF5,
        0xFFCEF3DB,
        0xFFBFE8CC,
        0xFFFDBAD8,
        0xFFFDD1FA,
        0xFFF6E6F5,
        0xFFDDDEF1
    ).map { it.toInt() }
}


@Keep
@Entity(tableName = "journal")
data class JournalModel(
    val question: String,
    val answer: String,
    val color: Int,
    val imageData: ByteArray? = null,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JournalModel

        if (question != other.question) return false
        if (answer != other.answer) return false
        if (color != other.color) return false
        if (imageData != null) {
            if (other.imageData == null) return false
            if (!imageData.contentEquals(other.imageData)) return false
        } else if (other.imageData != null) return false
        if (created != other.created) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = question.hashCode()
        result = 31 * result + answer.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + (imageData?.contentHashCode() ?: 0)
        result = 31 * result + created.hashCode()
        result = 31 * result + id
        return result
    }
}

