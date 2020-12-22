package com.shinta.idn.absensi.remote;

import com.shinta.idn.absensi.AbsensiActivity;

public class APIUtils {

    private APIUtils(){

    }

    public static final String API_URL = "http://192.168.1.7/attendance/index.php/";

    public static AbsensiActivity getAttendanceService(){
        return RetrovitClient.getClient(API_URL).create(AbsensiActivity.class);
    }

}
