package bootcamp.dio.listadetarefasroom.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ListaTarefasRepository (application: Application) {

    private val listaTarefasDao: ListaTarefasDao
    private val tudoListaTarefas: LiveData<List<ListaTarefas>>

    init {
        val database = AppDatabase.getDatabase(application.applicationContext)
        listaTarefasDao = database!!.listaTarefasDao()
        tudoListaTarefas = listaTarefasDao.buscaTudoDao()
    }

    fun inserirLtr(listaTarefas: ListaTarefas) = runBlocking {
        this.launch(Dispatchers.IO) {
            listaTarefasDao.inserirDao(listaTarefas)
        }
    }

    fun atualizaLtr(listaTarefas: ListaTarefas) = runBlocking {
        this.launch(Dispatchers.IO) {
            listaTarefasDao.atualizaLista(listaTarefas)
        }
    }

    fun excluirLtr(listaTarefas: ListaTarefas) = runBlocking {
        this.launch(Dispatchers.IO) {
            listaTarefasDao.excluirDAO(listaTarefas)
        }
    }

    fun buscaTudoLtr(): LiveData<List<ListaTarefas>> {
        return tudoListaTarefas
    }
}