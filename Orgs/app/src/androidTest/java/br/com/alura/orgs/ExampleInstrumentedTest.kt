package br.com.alura.orgs

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test

//        fun `Testando se o adapter recebe lista de forma correta`(){
//            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//            val adapter = ListaProdutosAdapter(context = appContext)
//
//            val listaProdutosRecebida = adapter.atualiza(ArrayList<Produto>(
//                Arrays.asList(
//                Produto(
//                    nome = "Salada",
//                    descricao = "Sim",
//                    valor = BigDecimal(1)
//                ),
//                Produto(
//                    nome = "Salada 1",
//                    descricao = "Não",
//                    valor = BigDecimal(1)
//                )
//            )))
//
//            TestCase.assertThat(listaProdutosRecebida, 2)
//        }

}