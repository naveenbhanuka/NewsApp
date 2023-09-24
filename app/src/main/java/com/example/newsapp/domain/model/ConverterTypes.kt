package com.example.newsapp.domain.model

import androidx.room.TypeConverter
import com.example.newsapp.data.datasource.dto.Source

class ConverterTypes {

    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }
}