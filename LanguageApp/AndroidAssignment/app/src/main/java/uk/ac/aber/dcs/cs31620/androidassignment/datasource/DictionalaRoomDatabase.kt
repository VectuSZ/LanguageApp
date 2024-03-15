package uk.ac.aber.dcs.cs31620.androidassignment.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.androidassignment.model.Word
import uk.ac.aber.dcs.cs31620.androidassignment.model.WordDao


@Database(entities = [Word::class], version = 1)
abstract class DictionalaRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private var instance: DictionalaRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        @Synchronized
        fun getDatabase(context: Context): DictionalaRoomDatabase?{
            if(instance == null){
                instance =
                    Room.databaseBuilder<DictionalaRoomDatabase>(
                        context.applicationContext,
                        DictionalaRoomDatabase::class.java,
                        "dictionala_database"
                    )
                        .allowMainThreadQueries()
                        .addCallback(roomDatabaseCallback(context))
                        .build()
            }
            return instance
        }

        private fun roomDatabaseCallback(context: Context):Callback{
            return object : Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    coroutineScope.launch {
                        populateDatabase(context, getDatabase(context)!!)
                    }
                }
            }
        }

        private suspend fun populateDatabase(context: Context, instance: DictionalaRoomDatabase){

            val firstWord = Word(
                0,
                "Cat",
                "Kot"
            )

            val secondWord = Word(
                0,
                "Dog",
            "Pies"
            )

            val thirdWord = Word(
                0,
                "Hamster",
                "Chomik"
            )

            val fourthWord = Word(
                0,
                "Elephant",
                "Słoń"
            )

            val fifthWord = Word(
                0,
                "Lion",
                "Lew"
            )

            val sixthWord = Word(
                0,
                "Tiger",
                "Tygrys"
            )

            val seventhWord = Word(
                0,
                "Penguin",
                "Pingwin"
            )

            val eighthWord = Word(
                0,
                "Squirrel",
                "Wiewiórka"
            )

            val ninthWord = Word(
                0,
                "Bird",
                "Ptak"
            )

            val tenthWord = Word(
                0,
                "Ant",
                "Mrówka"
            )

            val wordList = mutableListOf(
                firstWord,
                secondWord,
                thirdWord,
                fourthWord,
                fifthWord,
                sixthWord,
                seventhWord,
                eighthWord,
                ninthWord,
                tenthWord
            )

            val dao = instance.wordDao()
            dao.insertMultipleWords(wordList)

        }
    }
}