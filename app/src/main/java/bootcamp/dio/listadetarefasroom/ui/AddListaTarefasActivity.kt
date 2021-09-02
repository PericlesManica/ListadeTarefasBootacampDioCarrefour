package bootcamp.dio.listadetarefasroom.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bootcamp.dio.listadetarefasroom.R
import bootcamp.dio.listadetarefasroom.data.ListaTarefas
import bootcamp.dio.listadetarefasroom.extensions.format
import bootcamp.dio.listadetarefasroom.extensions.text
import bootcamp.dio.listadetarefasroom.util.Constants
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_add_lista_tarefas.*
import java.util.*

class AddListaTarefasActivity : AppCompatActivity() {

    var lisTarefas: ListaTarefas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_lista_tarefas)

        val intent = intent
        if (intent != null && intent.hasExtra(Constants.INTENT_OBJECT)){
            val listarefa: ListaTarefas = intent.getParcelableExtra(Constants.INTENT_OBJECT)!! //adicionado !!
            this.lisTarefas = listarefa
            prePopulateData(listarefa)
        }
        insertListener()
    }

    private fun prePopulateData(listaTarefas: ListaTarefas){
        it_titulo.setText(listaTarefas.titulo)
        it_descricao.setText(listaTarefas.descricao)
        it_data.setText(listaTarefas.data)
        it_hora.setText(listaTarefas.hora)
    }

    private fun insertListener() {
        mb_cancel.setOnClickListener {
            finish()
        }

        til_date.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                til_date.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        til_hour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker.addOnPositiveButtonClickListener {
                val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour

                til_hour.text = "$hour:$minute"
            }
            timePicker.show(supportFragmentManager, null)
        }

        mb_create.setOnClickListener{
            salvarListaTarefa()
        }

        iv_voltar.setOnClickListener {
            finish()
        }
    }

    private fun salvarListaTarefa(){
        if(vazioOuNulo()){
            val id = if (lisTarefas != null) lisTarefas?.id else null
            val listarefa = ListaTarefas(id = id, titulo = it_titulo.text.toString(),
                descricao = it_descricao.text.toString(), data = it_data.text.toString(),
                hora = it_hora.text.toString())
            val intent = Intent()
            intent.putExtra(Constants.INTENT_OBJECT, listarefa)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun vazioOuNulo(): Boolean{
        if (it_titulo.text.isNullOrEmpty()){
            til_title.error = "Favor inserir um Título!"
            it_titulo.requestFocus()
            return false
        }
        return true
    }
}
