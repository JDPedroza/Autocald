package com.example.autocald.ui.sliders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.autocald.R;

public class SliderFragment extends Fragment {

    //creamos elementos de tipo vista imagen y texto
    View view;
    ImageView image;
    TextView title, content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //creamos la vista
        view = inflater.inflate(R.layout.fragment_slider, container, false);

        //llamamos los elementos creados en el layaout
        image = view.findViewById(R.id.image);
        title = view.findViewById(R.id.txtTitle);
        content = view.findViewById(R.id.txtContent);
        RelativeLayout background = view.findViewById(R.id.background);

        if(getArguments()!=null){
            title.setText(getArguments().getString("title"));
            content.setText(getArguments().getString("content"));
            image.setImageResource(getArguments().getInt("image"));
            background.setBackgroundColor(getArguments().getInt("color"));
        }

        return view;
    }
}
