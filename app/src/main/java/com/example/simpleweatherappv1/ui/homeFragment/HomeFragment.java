package com.example.simpleweatherappv1.ui.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.simpleweatherappv1.R;

import Interface.OpenWeather;
import model.WeatherRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static final float AbsoluteZero = - 273.15f ;
    private OpenWeather openWeather ;
    private TextView cityName;
    private TextView textViewTerm;
    private TextView textViewWind;
    private TextView textViewHumidity;
    private TextView textViewBarometer;
    private String keyApi = "05234d956a40614b54332f8547824af1";
    private String city = "Ufa";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRetorfit();
        requestRetrofit(city,keyApi);

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_today, container, false);
        cityName = root.findViewById(R.id.textCity);
        textViewTerm = root.findViewById(R.id.textViewTerm);
        textViewWind = root.findViewById(R.id.textViewWind);
        textViewHumidity = root.findViewById(R.id.textViewHumidity);
        textViewBarometer = root.findViewById(R.id.textViewBarometer);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                cityName.setText(s);
            }
        });
        return root;

    }

    private void initRetorfit() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl( "http://api.openweathermap.org/" )
                .addConverterFactory(GsonConverterFactory. create ())
                .build();
        openWeather = retrofit.create(OpenWeather. class );
    }

    private void requestRetrofit(String city,String keyApi) {
        openWeather .loadWeather(city, keyApi)
                .enqueue( new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call,
                                           Response<WeatherRequest> response) {
                        if (response.body() != null ) {
                            String result = response.body().getName(); // название города
                            Float result1 = response.body().getMain().getTemp() + AbsoluteZero; // температура
                            Float result2 = response.body().getWind().getSpeed();
                            int result3 = response.body().getMain().getHumidity();
                            int result4 = response.body().getMain().getPressure();
                            cityName .setText(result);
                            textViewTerm.setText(Float.toString(result1));
                            textViewWind.setText(Float.toString(result2));
                            textViewHumidity.setText(Integer.toString(result3));
                            textViewBarometer.setText(Integer.toString(result4));
                        }
                    }
                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t)
                    {
                        textViewTerm .setText( "Error" );
                        cityName.setText("Error");
                        textViewWind.setText("Error");
                        textViewHumidity.setText("Error");
                        textViewBarometer.setText("Error");
                    }
                });
    }
}