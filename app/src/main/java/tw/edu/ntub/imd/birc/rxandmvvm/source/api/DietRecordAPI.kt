package tw.edu.ntub.imd.birc.rxandmvvm.source.api

import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import tw.edu.ntub.imd.birc.rxandmvvm.data.DietRecord
import tw.edu.ntub.imd.birc.rxandmvvm.data.ResponseBody


interface DietRecordAPI {

    @GET("dietRecord")
    fun searchAll(): Observable<Response<ResponseBody<DietRecord>>>

    ///不需圖片上傳用這個
    //    @POST("dietRecord")
//    fun createDietRecord(@Body body: JsonObject): Call<DietRecord>

    @Multipart
    @POST("dietRecord")
    fun createDietRecord(
        @PartMap params : Map<String, @JvmSuppressWildcards RequestBody> ,
        @Part imageFile: MultipartBody.Part
    ): Call<ResponseBody<DietRecord>>

}
