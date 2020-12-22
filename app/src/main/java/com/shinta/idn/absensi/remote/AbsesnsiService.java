package com.shinta.idn.absensi.remote;

import com.shinta.idn.absensi.model.SiswaItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AbsesnsiService {

    @GET("student/get/")
    Call<List<SiswaItem>> getAttendance();

    @FormUrlEncoded
    @POST("student/add")
    Call<SiswaItem> addAttendance(@Field("date") String date,
                                    @Field("name") String name,
                                    @Field("class") String classs,
                                    @Field("subject") String subject,
                                    @Field("attendance") String attendace);

    @FormUrlEncoded
    @PUT("student/update/")
    Call<SiswaItem> updateAttendance(@Field("id") int id,
                                       @Field("date") String date,
                                       @Field("name") String name,
                                       @Field("class") String classs,
                                       @Field("subject") String subject,
                                       @Field("attendance") String attendace);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = ("student/delete/"), hasBody = true)
    Call<SiswaItem> deleteAttendance(@Field("id") int id);

}
