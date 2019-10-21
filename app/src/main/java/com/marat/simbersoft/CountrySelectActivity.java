package com.marat.simbersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CountrySelectActivity extends AppCompatActivity {
   // private TextView mTextView;
    private SpecOfCountryAdapter mRetroAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_select);
        Intent intent = getIntent();
        String countryName = intent.getStringExtra("take this!");
        mListView = findViewById(R.id.spec_lv);

        getJSONResponse(countryName);
    }
    private void getJSONResponse(String countryName){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyInterface.JSON_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MyInterface api =retrofit.create(MyInterface.class);

        Call<String> call = api.getCountry(countryName);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
               Log.i("Responsestring", response.body().toString());

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body());

                        String jsonresponse = response.body().toString();
                        writeListView(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


//TODO преобразовать метод парсинга JSON в отрисовку карточки о стране
    //вроде как распарсил надотеперь лэйаут нарисовать
    private void writeListView(String response){
        try {
            JSONArray jsonArray = new JSONArray(response);

            ArrayList<ModelListView> modelListViewArrayList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                ModelListView modelListView =new ModelListView();
                JSONObject dataObj = jsonArray.getJSONObject(i);

                modelListView.setName(dataObj.getString("name"));
                modelListView.setArea(dataObj.getInt("area"));
                modelListView.setCapital(dataObj.getString("capital"));
                modelListView.setDemonym(dataObj.getString("demonym"));
                modelListView.setFlag(dataObj.getString("flag"));
                modelListView.setRegion(dataObj.getString("region"));
                modelListView.setSubregion(dataObj.getString("subregion"));
                modelListView.setPopulation(dataObj.getInt("population"));
                JSONArray correnciesArray = dataObj.getJSONArray("currencies");
                Correncies correncies = new Correncies();
                for(int j = 0; j < correnciesArray.length(); j++){
                    JSONObject corremciesObject = correnciesArray.getJSONObject(j);

                    correncies.setCode(corremciesObject.getString("code"));
                    correncies.setName(corremciesObject.getString("name"));
                    correncies.setSymbol(corremciesObject.getString("symbol"));

                }
                modelListView.setCorrencies(correncies);
                modelListViewArrayList.add(modelListView);
            }

            mRetroAdapter = new SpecOfCountryAdapter(this, modelListViewArrayList);

            mListView.setAdapter(mRetroAdapter);

        }catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
