package com.ration.rationstudy.marty.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ration.rationstudy.R;

/**
 * Created by Charny on 2018-11-11.
 */

public class BsDialog  extends BottomSheetDialogFragment {


    TextView all;
    TextView number;
    TextView year;
    TextView grade;
    TextView part;
    BsListener listener;


    public static BsDialog getInstance(BsDialog.BsListener listener) { BsDialog dlg = new BsDialog();
    dlg.listener = listener;
    return dlg;}



    public void onViewClicked(View view) {
        int pos = 5;
        switch (view.getId()) {
            case R.id.all: // 1
                pos--;
            case R.id.number:
                pos--;
            case R.id.year:
                pos--;
            case R.id.grade:
                pos--;
            case R.id.part:  // 5
                pos--;
        }
        listener.onSelected(pos);
        dismiss();

    }



    public BsDialog() {
    }

    public interface BsListener {
        void onSelected(int pos);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bsdialog, container, false);

        all = v.findViewById(R.id.all);
        number = v.findViewById(R.id.number);
        year = v.findViewById(R.id.year);
        grade = v.findViewById(R.id.grade);
        part = v.findViewById(R.id.part);

        all.setOnClickListener(this::onViewClicked);
        number.setOnClickListener(this::onViewClicked);
        year.setOnClickListener(this::onViewClicked);
        grade.setOnClickListener(this::onViewClicked);
        part.setOnClickListener(this::onViewClicked);

        return v;

    }

}
