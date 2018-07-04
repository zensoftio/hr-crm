package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;


import com.erkprog.zensofthrcrm.data.entity.Interview;

import java.util.List;

public interface InterviewsContract {

    interface View{

        void showCandidates(List<Interview> candidates);

        void showNoCandidates();

    }

    interface Presenter{

        void getInterviews();

    }

}
