package com.jorzelio.despesa.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorzelio.despesa.R;
import com.jorzelio.despesa.adapter.AdapterDespesaCompartilhada;
import com.jorzelio.despesa.adapter.AdapterDespesaIndividual;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private MaterialCalendarView calendarView;
    private TextView txtSaldo, txtSaldoIndividual, txtSaldoCompartilhado;
    private RecyclerView rDespesaIndividual, rDespesaCompartilhada;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        txtSaldo = view.findViewById(R.id.txt_saldo);
        txtSaldoIndividual = view.findViewById(R.id.txt_saldoIndividual);
        txtSaldoCompartilhado = view.findViewById(R.id.txt_saldoCompartilhado);
        rDespesaIndividual = view.findViewById(R.id.recycler_dpIndividual);
        rDespesaCompartilhada = view.findViewById(R.id.recycler_dpCompart);

        configuraCalendar();

        // Configurar adpter
        AdapterDespesaIndividual adapterIndividual = new AdapterDespesaIndividual();
        AdapterDespesaCompartilhada adapterCompartilhado = new AdapterDespesaCompartilhada();

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());

        rDespesaIndividual.setLayoutManager(layoutManager);
        rDespesaIndividual.setHasFixedSize(true);
        rDespesaIndividual.setAdapter(adapterIndividual);

        rDespesaCompartilhada.setLayoutManager(layoutManager1);
        rDespesaCompartilhada.setHasFixedSize(true);
        rDespesaCompartilhada.setAdapter(adapterCompartilhado);
        
        return view;
    }

    private void configuraCalendar() {
        CharSequence meses [] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendarView.setTitleMonths(meses);

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2027, 0, 1))
                .commit();
    }
}