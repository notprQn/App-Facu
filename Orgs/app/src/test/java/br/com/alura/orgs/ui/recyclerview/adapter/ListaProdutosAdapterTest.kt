package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import br.com.alura.orgs.model.Produto
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.mockito.Mockito
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList

class ListaProdutosAdapterTest : TestCase(){

    @Test
    fun `Testando se o adapter recebe lista de forma correta`(){

        val adapter = Mockito.spy(ListaProdutosAdapter(Context))
        Mockito.doNothing().`when`(adapter).notifyDataSetChanged()

        val listaProdutosRecebida = adapter.atualiza(ArrayList<Produto>(Arrays.asList(
            Produto(
                nome = "Salada",
                descricao = "Sim",
                valor = BigDecimal(1)
            ),
            Produto(
                nome = "Salada 1",
                descricao = "Não",
                valor = BigDecimal(1)
        )
        )))

        assertEquals(listaProdutosRecebida, 2)
    }

}