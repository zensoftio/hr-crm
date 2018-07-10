package com.erkprog.zensofthrcrm.data.entity.status;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;

public class RequestStatus {

  public static final int NOT_COSIDERED = 0;
  public static final int APPROVED = 1;
  public static final int REJECTED = 2;

  public static String getStringStatus (int intStatus) {
    switch (intStatus){
      case NOT_COSIDERED:
        return CRMApplication.resources.getString(R.string.not_considered);

      case APPROVED:
        return CRMApplication.resources.getString(R.string.approved);

      case REJECTED:
        return CRMApplication.resources.getString(R.string.rejected);

      default:
        return CRMApplication.resources.getString(R.string.not_defined);
    }

  }

}
