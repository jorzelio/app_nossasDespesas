package com.jorzelio.despesa.notification;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // data atual como padrão
        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        // Cria um novo seletor de data com a data atual como padrão
        return new DatePickerDialog(getActivity(), this, ano, mes, dia);
    }
    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        // Aqui você pode fazer algo com a data selecionada, como agendar uma notificação
        // Neste exemplo, agendamos uma notificação um dia antes da data selecionada
        scheduleNotification(ano, mes, dia); // Subtrai 1 para gerar a notificação um dia antes
    }

    private void scheduleNotification(int ano, int mes, int dia) {
        // Implementar lógica para agendar uma notificação aqui
        // Use AlarmManager para agendar um alarme que dispara um BroadcastReceiver ou IntentService
        // que cria e exibe a notificação
        // Este código é uma simplificação e não inclui a lógica completa

        // Exemplo: Agendando um IntentService para ser executado um dia antes da data selecionada
        // Lembre-se de substituir YourIntentService.class pelo seu próprio serviço
        // E ajustar os detalhes conforme necessário

        // Intent intent = new Intent(getContext(), YourIntentService.class);
        // PendingIntent pendingIntent = PendingIntent.getService(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        // Calendar calendar = Calendar.getInstance();
        // calendar.set(year, month, day);

        // AlarmManagerCompat.setExactAndAllowWhileIdle(alarmManager, AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
