package com.erkprog.zensofthrcrm.ui.vacancies.vacancyDetail;

import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;
import com.erkprog.zensofthrcrm.ui.BaseView;
import com.erkprog.zensofthrcrm.ui.ILifecycle;

public interface VacancyDetailContract extends BaseView {

  public interface View extends BaseView {

    void showVacancyDetails(Vacancy Vacancy);

  }

  public interface Presenter extends ILifecycle<View> {

    void getVacancyInternet(int vacancyId);

    void getVacancyLocal(int vacancyId);

  }
}