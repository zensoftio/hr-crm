package com.erkprog.zensofthrcrm.ui.interviews.interviewsList;


<<<<<<< HEAD
import android.content.Context;

=======
>>>>>>> 57adb4677409b630aade1dd6124c6aff810bf383
import com.erkprog.zensofthrcrm.data.entity.Interview;

import java.util.List;

public interface InterviewsContract {

<<<<<<< HEAD
    interface View {

        void showInterviews(List<Interview> interviews);

        void showToast();

        void showToast(Throwable t);

        void showNoInterviews();

        void showProgress();

        void hideProgress();


    }

    interface Presenter {

        void onDestroy();

        // ?? void onRefreshData();

        void getInterviews(Context mContext);

    }

    interface Repository {

        interface OnFinishedListener {
            void onFinished(List<Interview> interviews);

            void onFailure(Throwable t);
        }

        void getInterviewsList(OnFinishedListener onFinishedListener, Context mContext);
=======
    interface View{

        void showCandidates(List<Interview> candidates);

        void showNoCandidates();

    }

    interface Presenter{

        void getInterviews();

>>>>>>> 57adb4677409b630aade1dd6124c6aff810bf383
    }

}
