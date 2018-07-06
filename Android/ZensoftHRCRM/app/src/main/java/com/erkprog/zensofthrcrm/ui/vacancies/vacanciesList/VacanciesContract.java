package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import com.erkprog.zensofthrcrm.data.entity.Vacancy;

import java.util.List;

public interface VacanciesContract {

  interface View {
    void showVacancies(List<Vacancy> vacancies);

    void showVacancyDetail(int vacancyId);

    void showToast(String message);

    boolean isActive();
  }

  interface Presenter {
    void loadData();

    void onVacancyItemClick(Vacancy vacancy);
  }

}
