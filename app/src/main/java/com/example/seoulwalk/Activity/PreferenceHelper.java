package com.example.seoulwalk.Activity;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String Dulle_start = "Dulle_start";
    private final String Dulle_end = "Dulle_end";
    private final String Dulle_time = "Dulle_time";
    private final String NAME = "name";
    private final String NICK = "nick";
    private final String PASS_WD = "passwd";
    private final String PHONE = "phone";
    private final String CHECK = "checks";
    private final String KID1 = "kid1";
    private final String ADDRESS = "address";
    private final String ADDRESS_KID = "Kid_Locaion";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared", 0);
        this.context = context;
    }





    /** 둘레길 시작₩ */
    public void putDulle_start(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(Dulle_start, loginOrOut);
        edit.apply();
    }

    public String getProfile() {
        return app_prefs.getString(Dulle_start, "");
    }


/** 둘레길 끝 */
    public void putDulle_end(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(Dulle_end, loginOrOut);
        edit.apply();
    }

    public String getDulle_end() {
        return app_prefs.getString(Dulle_end, "");

    }

    //비밀번호
    public void putDulle_time(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(Dulle_time, loginOrOut);
        edit.apply();
    }

    public String getDulle_time() {
        return app_prefs.getString(Dulle_time, "");
    }

    //닉네임
    public void putNick(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NICK, loginOrOut);
        edit.apply();
    }

    public String getNICK() {
        return app_prefs.getString(NICK, "");
    }

    //폰번호
    public void putPhone(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PHONE, loginOrOut);
        edit.apply();
    }

    public String getPHONE() {
        return app_prefs.getString(PHONE, "");
    }
    //계정체크
    public void putCheck(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(CHECK, loginOrOut);
        edit.apply();
    }

    public String getCHECK() {
        return app_prefs.getString(CHECK, "");
    }


    //자녀 아이디
    public void putKid1(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(KID1, loginOrOut);
        edit.apply();
    }

    public String getKID1() {
        return app_prefs.getString(KID1, "");
    }

    //설정 위치
    public void putAddress(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(ADDRESS, loginOrOut);
        edit.apply();
    }

    public String getADDRESS() {
        return app_prefs.getString(ADDRESS, "");
    }

    public void putAddressKid(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(ADDRESS_KID, loginOrOut);
        edit.apply();
    }

    public String getADDRESS_KID() {
        return app_prefs.getString(ADDRESS_KID, "");
    }

}

//
