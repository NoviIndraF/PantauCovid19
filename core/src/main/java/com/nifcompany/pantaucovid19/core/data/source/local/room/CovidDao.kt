package com.nifcompany.pantaucovid19.core.data.source.local.room

import androidx.room.*
import com.nifcompany.pantaucovid19.core.data.source.local.entity.IndonesiaEntity
import com.nifcompany.pantaucovid19.core.data.source.local.entity.ProvinceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CovidDao {
    @Query("SELECT * FROM dataIndonesia")
    fun getDataIndonesia(): Flow<IndonesiaEntity>

    @Query("SELECT * FROM dataProvinsi ORDER BY name ASC")
    fun getDataProvince(): Flow<List<ProvinceEntity>>

    @Query("SELECT * FROM dataProvinsi WHERE name LIKE :search || '%' ORDER BY name ASC")
    fun getSearchProvince(search: String?): Flow<List<ProvinceEntity>>

    @Query("SELECT * FROM dataProvinsi where isSave = 1 ORDER BY name ASC")
    fun getSaveDataProvince(): Flow<List<ProvinceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataIndonesia(indonesiaEntity: IndonesiaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataProvince(provinceEntity: List<ProvinceEntity>)

    @Update
    fun updateSaveDataProvince(province: ProvinceEntity)
}