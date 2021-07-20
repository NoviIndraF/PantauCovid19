package com.nifcompany.pantaucovid19.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nifcompany.pantaucovid19.core.data.source.local.entity.IndonesiaEntity
import com.nifcompany.pantaucovid19.core.data.source.local.entity.ProvinceEntity

@Database(entities = [IndonesiaEntity::class, ProvinceEntity::class],
            version = 1,
            exportSchema = false)
abstract class CovidDatabase : RoomDatabase() {

    abstract fun covidDao(): CovidDao
}