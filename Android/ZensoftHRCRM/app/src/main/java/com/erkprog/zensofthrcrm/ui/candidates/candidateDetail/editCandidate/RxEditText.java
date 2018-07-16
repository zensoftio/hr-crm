package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.editCandidate;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import rx.Observable;
import rx.subjects.PublishSubject;


public class RxEditText {
  public static Observable<String> getTextWatcherObservable(@NonNull final EditText editText) {

    final PublishSubject<String> subject = PublishSubject.create();

    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        subject.onNext(editable.toString());
      }
    });

    return subject;
  }
}