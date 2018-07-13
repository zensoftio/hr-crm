package com.erkprog.zensofthrcrm.ui.vacancies.vacanciesList;

import com.erkprog.zensofthrcrm.data.entity.Vacancy;
import com.erkprog.zensofthrcrm.ui.BaseView;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

import java.util.List;

public interface VacanciesContract {

  interface View extends BaseView {

    void showVacancies(List<Vacancy> vacancies);

  }

  interface Presenter extends ILifecycle<View> {
    void loadData();

    void onVacancyItemClick(Vacancy vacancy);
  }

}
