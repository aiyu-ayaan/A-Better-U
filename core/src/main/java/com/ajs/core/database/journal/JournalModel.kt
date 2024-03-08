package com.ajs.core.database.journal

import androidx.room.Entity
import androidx.room.PrimaryKey


val colorList = listOf(
    0xFF4285F4,
    0xFFEA4335,
    0xFFFBBC05,
    0xFF34A853,
    0xFF4285F4,
    0xFFEA4335,
    0xFFFBBC05,
    0xFF34A853,
    0xFF4285F4,
    0xFFEA4335,
    0xFFFBBC05,
    0xFF34A853,
)

@Entity(tableName = "journal")
data class JournalModel(
    val title: String,
    val description: String,
    val color: Long,
    val imageData: ByteArray? = null,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JournalModel

        if (title != other.title) return false
        if (description != other.description) return false
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
        var result = title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + (imageData?.contentHashCode() ?: 0)
        result = 31 * result + created.hashCode()
        result = 31 * result + id
        return result
    }
}

