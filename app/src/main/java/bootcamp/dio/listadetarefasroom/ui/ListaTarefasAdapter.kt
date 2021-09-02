package bootcamp.dio.listadetarefasroom.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.dio.listadetarefasroom.R
import bootcamp.dio.listadetarefasroom.data.ListaTarefas
import kotlinx.android.synthetic.main.item_lista_tarefas.view.*

class ListaTarefasAdapter(listaTarefaEvento: ListaTarefaEvento) : RecyclerView.Adapter<ListaTarefasAdapter.ViewHolder>() {

    private var listaTarefasList: List<ListaTarefas> = arrayListOf()
    private var filtroListaTarefasList: List<ListaTarefas> = arrayListOf()
    private val listener: ListaTarefaEvento = listaTarefaEvento

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lista_tarefas, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = filtroListaTarefasList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filtroListaTarefasList[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(listaTarefas: ListaTarefas, listener: ListaTarefaEvento) {
            itemView.tv_title.text = listaTarefas.titulo
            itemView.tv_date.text = listaTarefas.data
            itemView.tv_hour.text = listaTarefas.hora

            itemView.iv_delete.setOnClickListener {
                listener.onExcluiLista(listaTarefas)
            }

            itemView.setOnClickListener {
                listener.onViewLista(listaTarefas)
            }
        }
    }
    fun setTudoListaTarefas(listaTarefasItens: List<ListaTarefas>) {
        this.listaTarefasList = listaTarefasItens
        this.filtroListaTarefasList = listaTarefasItens
        notifyDataSetChanged()
    }

    interface ListaTarefaEvento {
        fun onExcluiLista(listaTarefas: ListaTarefas)
        fun onViewLista(listaTarefas: ListaTarefas)
    }
}