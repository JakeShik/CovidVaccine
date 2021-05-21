package com.example.covidvaccine

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaRegiao(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db


    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME TEXT NOT NULL, $NUMERO_HABITANTES INTEGER NOT NULL, $NUMERO_VACINADOS INTEGER NOT NULL, $NUMERO_NAO_VACINADOS INTEGER NOT NULL, $FAIXA_ETARIA TEXT NOT NULL )")
    }

    fun insert(values: ContentValues): Long {
        return db.insert(NOME_TABELA, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(NOME_TABELA, whereClause, whereArgs)
    }
    fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
    }
    companion object {
        const val NOME_TABELA = "Regiões"
        const val CAMPO_NOME = "NomedaRegiao"
        const val NUMERO_HABITANTES = "NumeroHabitantes"
        const val NUMERO_VACINADOS = "NumeroHabitantesVacinados"
        const val NUMERO_NAO_VACINADOS = "NumeroHabitantesNaoVacinados"
        const val FAIXA_ETARIA = "FaixaEtáriaSendoVacinada"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME)
    }
}