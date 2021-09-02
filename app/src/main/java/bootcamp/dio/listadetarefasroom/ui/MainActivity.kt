package bootcamp.dio.listadetarefasroom.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bootcamp.dio.listadetarefasroom.R
import bootcamp.dio.listadetarefasroom.data.ListaTarefas
import bootcamp.dio.listadetarefasroom.util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_state.*
import kotlinx.android.synthetic.main.empty_state.view.*

class MainActivity : AppCompatActivity(), ListaTarefasAdapter.ListaTarefaEvento {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var listaTarefasAdapter: ListaTarefasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpPermissions()
        rv_tasks.layoutManager = LinearLayoutManager(this)
        listaTarefasAdapter =  ListaTarefasAdapter(this)
        rv_tasks.adapter = listaTarefasAdapter

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.buscaTudoMvm().observe(this, Observer {
            listaTarefasAdapter.setTudoListaTarefas(it)
            listaVazia()
        })

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddListaTarefasActivity::class.java)
            startActivityForResult(intent, Constants.INTENT_CREATE_LISTA)
        }
    }

    override fun onExcluiLista(listaTarefas: ListaTarefas) {
        mainViewModel.excluirMvm(listaTarefas)
    }

    override fun onViewLista(listaTarefas: ListaTarefas) {
        val intent = Intent( this@MainActivity, AddListaTarefasActivity::class.java)
        intent.putExtra(Constants.INTENT_OBJECT, listaTarefas)
        startActivityForResult(intent, Constants.INTENT_UPDATE_LISTA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            val listaTarefas = data?.getParcelableExtra<ListaTarefas>(Constants.INTENT_OBJECT)!!
            when (requestCode){
                Constants.INTENT_CREATE_LISTA -> {
                    mainViewModel.inserirMvm(listaTarefas)
                }
                Constants.INTENT_UPDATE_LISTA -> {
                    mainViewModel.atualizaMvm(listaTarefas)
                }
            }
        }
    }

    private fun setUpPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
    }

        fun listaVazia(){
            if (listaTarefasAdapter.itemCount != 0){
                include_empty.visibility = View.GONE
            }else {
                include_empty.visibility = View.VISIBLE
        }
    }
}