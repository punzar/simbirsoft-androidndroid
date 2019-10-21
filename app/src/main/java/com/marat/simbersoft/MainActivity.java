package com.marat.simbersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String EXTRA_SELECTED_COUNTRY = "take this!";
    private ListView mListView;
    private CountriesListAdapter mCountriesListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.lv);

        getJSONResponse();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {

                Context context = mListView.getContext();

                ModelListView selected = (ModelListView) mListView.getAdapter().getItem(position);
                String text = selected.getName();
                //Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,CountrySelectActivity.class);

                intent.putExtra(EXTRA_SELECTED_COUNTRY, text);
                startActivity(intent);
            }
        });
    }


    private void getJSONResponse(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyInterface.JSON_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MyInterface api =retrofit.create(MyInterface.class);

        Call<String> call = api.getString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

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

    private void writeListView(String response){
        try {
            JSONArray jsonArray = new JSONArray(response);
            ArrayList<ModelListView> modelListViewArrayList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                ModelListView modelListView = new ModelListView();
                JSONObject dataObj = jsonArray.getJSONObject(i);

                modelListView.setName(dataObj.getString("name"));
                modelListViewArrayList.add(modelListView);
            }

            mCountriesListAdapter = new CountriesListAdapter(this, modelListViewArrayList);

            mListView.setAdapter(mCountriesListAdapter);

        }catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
