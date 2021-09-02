package bootcamp.dio.listadetarefasroom.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "listatarefas")
@Parcelize()
data class ListaTarefas(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "titulo") val titulo: String,
    @ColumnInfo(name = "descricao")val descricao: String,
    @ColumnInfo(name = "data")val data: String,
    @ColumnInfo(name = "hora")val hora: String) : Parcelable
