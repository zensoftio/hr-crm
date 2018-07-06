package com.erkprog.zensofthrcrm.data.entity.status;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;

public class InterviewStatus {

  public static final int SCHEDULED = 0;
  public static final int TOOK_PLACE = 1;
  public static final int CANCELED = 2;

  public static String getStringStatus(int intStatus) {
    switch (intStatus) {
      case SCHEDULED:
        return CRMApplication.resources.getString(R.string.scheduled);

      case TOOK_PLACE:
        return CRMApplication.resources.getString(R.string.took_place);

      case CANCELED:
        return CRMApplication.resources.getString(R.string.canceled);

      default:
        return null;
    }
  }
}
