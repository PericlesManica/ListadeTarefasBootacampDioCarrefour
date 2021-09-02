package bootcamp.dio.listadetarefasroom.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ListaTarefasDao {

    @Query("SELECT * FROM listatarefas ORDER BY id DESC")
    fun buscaTudoDao(): LiveData<List<ListaTarefas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserirDao(listaTarefas: ListaTarefas)

    @Delete
    suspend fun excluirDAO(listaTarefas: ListaTarefas)

    @Update
    suspend fun atualizaLista(listaTarefas: ListaTarefas)
}