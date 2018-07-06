package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;

import java.util.List;

public class VacanciesFragment extends Fragment implements VacanciesContract.View{

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_vacancies_list, container, false);
    return v;
  }

  @Override
  public void showVacancies(List<Vacancy> vacancies) {

  }

  @Override
  public void showVacancyDetail(int vacancyId) {

  }

  @Override
  public void showToast(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

  }

  @Override
  public boolean isActive() {
    return isAdded();
  }
}
