package com.erkprog.zensofthrcrm.data.entity.status;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;

public class CandidateStatus {

  public static final int NOT_CONSIDERED = 0;
  public static final int CONSIDERED = 1;
  public static final int SUITABLE = 2;
  public static final int NOT_SUITABLE = 3;
  public static final int TESTING = 4;
  public static final int INTERVIEW_SCHEDULED = 5;
  public static final int INTERVIEW_TOOK_PLACE = 6;
  public static final int WORKER = 7;
  public static final int RESERVED = 8;
  public static final int INTERN = 9;
  public static final int INTERVIEW_FAILED = 10;

  public static String getStringStatus (int intStatus){
    switch (intStatus){
      case NOT_CONSIDERED:
        return CRMApplication.resources.getString(R.string.not_considered);

      case CONSIDERED:
        return CRMApplication.resources.getString(R.string.considered);

      case SUITABLE:
        return CRMApplication.resources.getString(R.string.suitable);

      case NOT_SUITABLE:
        return CRMApplication.resources.getString(R.string.not_suitable);

      case TESTING:
        return CRMApplication.resources.getString(R.string.testing);

      case INTERVIEW_SCHEDULED:
        return CRMApplication.resources.getString(R.string.interview_scheduled);

      case INTERVIEW_TOOK_PLACE:
        return CRMApplication.resources.getString(R.string.interview_took_place);

      case WORKER:
        return CRMApplication.resources.getString(R.string.worker);

      case RESERVED:
        return CRMApplication.resources.getString(R.string.reserved);

      case INTERN:
        return CRMApplication.resources.getString(R.string.intern);

      case INTERVIEW_FAILED:
        return CRMApplication.resources.getString(R.string.interview_failed);

      default:
        return CRMApplication.resources.getString(R.string.not_defined);
    }
  }



}
