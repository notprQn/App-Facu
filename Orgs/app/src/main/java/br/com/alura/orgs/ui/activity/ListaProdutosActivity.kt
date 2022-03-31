package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.database.dao.ProdutoDao
import br.com.alura.orgs.databinding.ActivityListaProdutosActivityBinding
import br.com.alura.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val TAG = "ListaProdutosActivity"

class ListaProdutosActivity : AppCompatActivity() {

    private val adapter = ListaProdutosAdapter(context = this)
    private val binding by lazy {
        ActivityListaProdutosActivityBinding.inflate(layoutInflater)
    }
    private val produtoDao: ProdutoDao by lazy {
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()

        val firebaseAuth = Firebase.auth
        //firebaseAuth.signInWithEmailAndPassword("joao-pedro@teste.com", "123456")
        val usuarioFirebase: FirebaseUser? = firebaseAuth.currentUser
        if (usuarioFirebase != null){
            Toast.makeText(this, "Usuário Logado ${usuarioFirebase.email}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Não tem usuário logados", Toast.LENGTH_SHORT).show()
        }
        firebaseAuth.signOut()
    }

    private fun cadastraUsuario(firebaseAuth: FirebaseAuth) {
        val tarefa = firebaseAuth.createUserWithEmailAndPassword("joao-pedro@teste.com", "123456")

        tarefa.addOnSuccessListener {
            Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
        }
        tarefa.addOnFailureListener {
            Log.e(TAG, "onCreate: ", it)
            Toast.makeText(this, "Falha ao Cadastrar o Usuário", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista_produto_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_login_icon -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_nome_asc -> {
                adapter.atualiza(produtoDao.buscaPorNomeAsc())
            }
            R.id.menu_nome_desc -> {
                adapter.atualiza(produtoDao.buscaPorNomeDesc())
            }
            R.id.menu_valor_asc -> {
                adapter.atualiza(produtoDao.buscaPorValorDesc())
            }
            R.id.menu_valor_desc -> {
                adapter.atualiza(produtoDao.buscaPorValorAsc())
            }
            R.id.menu_sem_filtro -> {
                adapter.atualiza(produtoDao.semFiltro())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.instancia(this)
        val produtoDao = db.produtoDao()
        adapter.atualiza(produtoDao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DetalhesProdutoActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
    }

}