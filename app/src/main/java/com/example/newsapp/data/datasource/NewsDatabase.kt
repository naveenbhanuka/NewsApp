package com.example.newsapp.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.datasource.dto.Article
import com.example.newsapp.domain.model.ConverterTypes
import com.example.newsapp.domain.model.User
import com.example.newsapp.utill.Constant.DATABASE_NAME

@Database(entities = [User::class,Article::class], version = 1, exportSchema = false)
@TypeConverters(ConverterTypes::class)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao

    abstract fun articleDao(): ArticleDao

    companion object{
        @Volatile
        private var INSTANCE : NewsDatabase? = null

        fun getDatabase(context: Context) : NewsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }

    }
}