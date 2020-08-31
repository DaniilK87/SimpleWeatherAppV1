package com.example.simpleweatherappv1.ui.cityFragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleweatherappv1.R;

public class CityFragment extends Fragment {


    private CityViewModel slideshowViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        String[] data = getResources().getStringArray(R.array. items );

        
        slideshowViewModel =
                ViewModelProviders.of(this).get(CityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_city, container, false);
        final TextView textView = root.findViewById(R.id.searchCity);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                initRecyclerView(data);
            }
        });
        return root;
    }

    private void initRecyclerView(String[] data) {
         RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
         recyclerView.setHasFixedSize( true );

        // Менеджер
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Адаптер
        SocnetAdapter adapter = new SocnetAdapter(data);
        recyclerView.setAdapter(adapter);

        // Установим слушателя
        adapter.SetOnItemClickListener(new SocnetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast. makeText (CityFragment.this, String. format ( "%s - %d" ,
                        ((TextView)view).getText(), position), Toast. LENGTH_SHORT ).show();
            }
        });
    }
}