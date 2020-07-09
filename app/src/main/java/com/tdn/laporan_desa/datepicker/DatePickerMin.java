package com.tdn.laporan_desa.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerMin extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private PandaDateListener dateListener;
    private DatePickerDialog datePickerDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int tahun = c.get(Calendar.YEAR);
        int bulan = c.get(Calendar.MONTH);
        int hari = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, tahun, bulan, hari);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateListener.onDatePandaSet(year, month, dayOfMonth);
    }

    public void setDateListener(PandaDateListener listener) {
        this.dateListener = listener;
    }

    public interface PandaDateListener {
        void onDatePandaSet(int tahun, int bulan, int hari);
    }
}
