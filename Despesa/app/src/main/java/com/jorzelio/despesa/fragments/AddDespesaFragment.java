package com.jorzelio.despesa.fragments;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jorzelio.despesa.R;
import com.jorzelio.despesa.notification.DatePickerFragment;
import com.jorzelio.despesa.notification.NotificationPub;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDespesaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDespesaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddDespesaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddDespesaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDespesaFragment newInstance(String param1, String param2) {
        AddDespesaFragment fragment = new AddDespesaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_despesa, container, false);

        TextView selecionaData;
        selecionaData = view.findViewById(R.id.txtSelecionaData);

        if(ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
        }else{
            Log.i("debug", "Permissao garantida já!");
        }

        selecionaData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDatePicker();
                /*Toast toast = Toast.makeText(view.getContext(),
                        "Criando uma notificacao em 10 segundos..",
                        Toast.LENGTH_SHORT);
                toast.show();
                //faz a notificacao
                agendaNotificacao2(10000);*/
            }
        });

        //BottomNavigationView bottomNavigation = view.findViewById(R.id.btnv);
        //bottomNavigation.setBackgroundColor(getResources().getColor(R.color.cor_fundo_cadastro));
        return view;
    }

    private void showDatePicker() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    private void agendaNotificacao2(int delay) {
        Intent intent = new Intent(getContext(), NotificationPub.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//        apenas para teste
//        nfm.notify(0, builder.build());
        Log.i("debug", "agendando notificacao");
        PendingIntent pendingIntent2 =
                PendingIntent.getBroadcast(getContext(),
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime() + 1000,
                pendingIntent2
        );
    }
}