package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.editCandidate;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.ui.candidates.candidateDetail.CandidateDetail;

import rx.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import rx.functions.Func1;

public class EditCandidateFragment extends Fragment implements EditCandidateContract.View {

  private EditCandidateContract.Presenter mPresenter;
  private Candidate mCandidate;
  private static final String NO_INTERNET_MESSAGE = "Cannot save candidate due to your internet " +
      "connection";

  @BindView(R.id.ec_progress_bar)
  ProgressBar mProgressBar;

  @BindView(R.id.ec_first_name)
  EditText mFirstName;

  @BindView(R.id.ec_last_name)
  EditText mLastName;

  @BindView(R.id.ec_department)
  EditText mDepartment;

  @BindView(R.id.ec_email)
  EditText mEmail;

  @BindView(R.id.ec_exp)
  EditText mExp;

  @BindView(R.id.ec_phone)
  EditText mPhone;

  @BindView(R.id.ec_save)
  Button mButton;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = new EditCandidatePresenter(this, CRMApplication.getInstance(requireContext())
        .getApiService(), CRMApplication.getInstance(requireContext()).getSQLiteHelper());
    mPresenter.bind(this);


  }


  @Override
  public void onDestroy() {
    super.onDestroy();
    mPresenter.unbind();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_edit_candidate, container, false);
    Bundle bundle = getArguments();

    ButterKnife.bind(this, v);

    if (bundle != null)
      mCandidate = (Candidate) bundle.getSerializable("candidate");

    setViews();

    mButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        showProgress();
        if (hasInternetConnection(getContext())) {
          mPresenter.updateCadidate(mCandidate);
        } else {
          showMessage(NO_INTERNET_MESSAGE);
        }
      }
    });

    dismissProgress();


    return v;
  }

  private void setViews() {
    if (mCandidate != null) {
      mFirstName.setText(mCandidate.getFirstName());
      if (mCandidate.getPosition() != null && mCandidate.getPosition().getDepartment() != null) {
        mDepartment.setText(mCandidate.getPosition().getDepartment().getName());
      }
      mLastName.setText(mCandidate.getLastName());
      mEmail.setText(mCandidate.getEmail());
      mExp.setText(String.valueOf(mCandidate.getExperience()));
      mPhone.setText(mCandidate.getPhone());

    }


    mButton.setEnabled(false);

    subscribeObs();

  }

  private void subscribeObs() {
    Observable<String> firstNameObs = RxEditText.getTextWatcherObservable(mFirstName);
    Observable<String> lastNameObs = RxEditText.getTextWatcherObservable(mLastName);
    Observable<String> departmentObs = RxEditText.getTextWatcherObservable(mDepartment);
    Observable<String> emailObs = RxEditText.getTextWatcherObservable(mEmail);
    Observable<String> expObs = RxEditText.getTextWatcherObservable(mExp);
    Observable<String> phoneObs = RxEditText.getTextWatcherObservable(mPhone);

    firstNameObs.map(new Func1<String, Boolean>() {
      @Override
      public Boolean call(String s) {
        if (s.equals(mCandidate.getFirstName())) {
          return false;
        } else {
          mCandidate.setFirstName(s);
          return true;
        }
      }
    }).subscribe(new Action1<Boolean>() {
      @Override
      public void call(Boolean o) {
        mButton.setEnabled(o);
      }
    });

    lastNameObs.map(new Func1<String, Boolean>() {
      @Override
      public Boolean call(String s) {
        if (s.equals(mCandidate.getLastName())) {
          return false;
        } else {
          mCandidate.setLastName(s);
          return true;
        }
      }
    }).subscribe(new Action1<Boolean>() {
      @Override
      public void call(Boolean o) {
        mButton.setEnabled(o);
      }
    });

    departmentObs.map(new Func1<String, Boolean>() {
      @Override
      public Boolean call(String s) {
        if (s.equals(mCandidate.getPosition().getDepartment().getName())) {
          return false;
        } else {
          mCandidate.getPosition().getDepartment().setName(s);
          return true;
        }
      }
    }).subscribe(new Action1<Boolean>() {
      @Override
      public void call(Boolean o) {
        mButton.setEnabled(o);
      }
    });

    emailObs.map(new Func1<String, Boolean>() {
      @Override
      public Boolean call(String s) {
        if (s.equals(mCandidate.getEmail())) {
          return false;
        } else {
          mCandidate.setEmail(s);
          return true;
        }
      }
    }).subscribe(new Action1<Boolean>() {
      @Override
      public void call(Boolean o) {
        mButton.setEnabled(o);
      }
    });

    expObs.map(new Func1<String, Boolean>() {
      @Override
      public Boolean call(String s) {
        if (s.equals(String.valueOf(mCandidate.getExperience()))) {
          return false;
        } else {
          mCandidate.setExperience(Float.valueOf(s));
          return true;
        }
      }
    }).subscribe(new Action1<Boolean>() {
      @Override
      public void call(Boolean o) {
        mButton.setEnabled(o);
      }
    });

    phoneObs.map(new Func1<String, Boolean>() {
      @Override
      public Boolean call(String s) {
        if (s.equals(mCandidate.getPhone())) {
          return false;
        } else {
          mCandidate.setPhone(s);
          return true;
        }
      }
    }).subscribe(new Action1<Boolean>() {
      @Override
      public void call(Boolean o) {
        mButton.setEnabled(o);
      }
    });

  }

  @Override
  public void dismissProgress() {
    mProgressBar.setVisibility(View.GONE);
  }


  @Override
  public void showMessage(String message) {
    dismissProgress();
    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public boolean hasInternetConnection(Context context) {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (connectivityManager != null) {
      NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
      return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    return false;
  }

  @Override
  public void showProgress() {

    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void onFinishedRequest(int candidateId) {
    dismissProgress();
    Intent intent = CandidateDetail.getIntent(getActivity(), candidateId);
    startActivity(intent);
  }

}