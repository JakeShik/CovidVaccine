package com.example.covidvaccine

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Regiao(var id: Long = -1, var nome: String) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(TabelaRegiao.CAMPO_NOME, nome)
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Regiao {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaRegiao.CAMPO_NOME)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)

            return Regiao(id, nome)
        }
    }
}