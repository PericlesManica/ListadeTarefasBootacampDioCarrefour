package bootcamp.dio.listadetarefasroom.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import bootcamp.dio.listadetarefasroom.data.ListaTarefas
import bootcamp.dio.listadetarefasroom.data.ListaTarefasRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository: ListaTarefasRepository = ListaTarefasRepository(application)
    private val buscaTudo: LiveData<List<ListaTarefas>> = repository.buscaTudoLtr()

    fun inserirMvm(listaTarefas: ListaTarefas) {
        repository.inserirLtr(listaTarefas)
    }

    fun atualizaMvm(listaTarefas: ListaTarefas) {
        repository.atualizaLtr(listaTarefas)
    }

    fun buscaTudoMvm(): LiveData<List<ListaTarefas>> {
        return buscaTudo
    }

    fun excluirMvm(listaTarefas: ListaTarefas) {
        repository.excluirLtr(listaTarefas)
    }
}