package com.marat.simbersoft;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyInterface {

    String JSON_URL = "https://restcountries.eu/rest/v2/";

    @GET("all?fields=name")
    Call<String> getString();

    @GET("name/{name}")
    Call<String> getCountry(@Path("name") String countryName);
}
