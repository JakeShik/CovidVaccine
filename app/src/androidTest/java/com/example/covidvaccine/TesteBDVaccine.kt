package com.example.covidvaccine

import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TesteBDVaccine {
    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private fun getBdVaccineOpenHelper() = BdVaccineHelper(getAppContext())

    private fun insereRegiao(tabela: TabelaRegiao, categoria: Regiao): Long {
        val id = tabela.insert(categoria.toContentValues())
        assertNotEquals(-1, id)

        return id
    }

    private fun getCategoriaBaseDados(tabela: TabelaRegiao, id: Long): Regiao {
        val cursor = tabela.query(
            TabelaRegiao.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Regiao.fromCursor(cursor)
    }

    @Before
    fun apagaBaseDados() {
        getAppContext().deleteDatabase(BdVaccineHelper.NOME_BASE_DADOS)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val db = getBdVaccineOpenHelper().readableDatabase
        assert(db.isOpen)
        db.close()
    }

    @Test
    fun consegueInserirRegiao() {
        val db = getBdVaccineOpenHelper().writableDatabase
        val tabelaRegiao = TabelaRegiao(db)

        val regiao = Regiao(nome = "Guarda")
        regiao.id = insereRegiao(tabelaRegiao, regiao)

        assertEquals(regiao, getCategoriaBaseDados(tabelaRegiao, regiao.id))

        db.close()
    }

    @Test
    fun consegueAlterarRegiao() {
        val db = getBdVaccineOpenHelper().writableDatabase
        val tabelaRegiao = TabelaRegiao(db)

        val regiao= Regiao(nome = "Porto")
        regiao.id = insereRegiao(tabelaRegiao, regiao)

        regiao.nome = "Porto"

        val registosAlterados = tabelaRegiao.update(
            regiao.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(regiao.id.toString())
        )

        assertEquals(1, registosAlterados)

        assertEquals(regiao, getCategoriaBaseDados(tabelaRegiao, regiao.id))

        db.close()
    }

    @Test
    fun consegueEliminarRegiao() {
        val db = getBdVaccineOpenHelper().writableDatabase
        val tabelaRegiao = TabelaRegiao(db)

        val regiao = Regiao(nome = "teste")
        regiao.id = insereRegiao(tabelaRegiao, regiao)

        val registosEliminados = tabelaRegiao.delete(
            "${BaseColumns._ID}=?",
            arrayOf(regiao.id.toString())
        )

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerRegiao() {
        val db = getBdVaccineOpenHelper().writableDatabase
        val tabelaRegiao = TabelaRegiao(db)

        val regiao = Regiao(nome = "Lisboa")
        regiao.id = insereRegiao(tabelaRegiao, regiao)

        assertEquals(regiao, getCategoriaBaseDados(tabelaRegiao, regiao.id))

        db.close()
    }
}