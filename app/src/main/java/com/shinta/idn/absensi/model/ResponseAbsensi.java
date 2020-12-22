package com.shinta.idn.absensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAbsensi {

    @SerializedName("student")
    private List<SiswaItem> student;

    @SerializedName("error")
    private boolean error;

    @SerializedName("status")
    private int status;

    public class ResponseAttendance {
    }

    public void setSiswa(List<SiswaItem> siswa){
        this.student = siswa;
    }

    public List<SiswaItem> getStudent(){
        return student;
    }

    public void setError(boolean error){
        this.error = error;
    }

    public boolean isError(){
        return error;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return status;
    }

}
