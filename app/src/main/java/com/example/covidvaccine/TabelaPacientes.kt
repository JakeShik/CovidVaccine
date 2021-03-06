package com.example.covidvaccine

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaPacientes(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db


    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, _NOME TEXT NOT NULL, $REGIAO TEXT NOT NULL, $E_MAIL TEXT NOT NULL, $DATA_NASCIMENTO DATE NOT NULL)")
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

        const val NOME_TABELA = "Vacinas"
        const val REGIAO = "Regiao"
        const val E_MAIL = "email"
        const val DATA_NASCIMENTO = "DataNascimento"
        //val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME)
    }
}