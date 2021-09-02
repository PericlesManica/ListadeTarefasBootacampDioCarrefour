package bootcamp.dio.listadetarefasroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListaTarefas::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listaTarefasDao (): ListaTarefasDao

    companion object {
        private var INSTANCE: AppDatabase? = null

            fun getDatabase(context: Context): AppDatabase? {
                if (INSTANCE == null){
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                        AppDatabase::class.java,
                        "listatarefas_db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}