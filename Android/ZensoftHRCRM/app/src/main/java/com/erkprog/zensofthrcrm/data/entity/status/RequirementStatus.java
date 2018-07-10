package com.erkprog.zensofthrcrm.data.entity.status;

import com.erkprog.zensofthrcrm.CRMApplication;
import com.erkprog.zensofthrcrm.R;

public class RequirementStatus {

  public static final int REQUIRED = 0;
  public static final int OPTIONAL = 1;
  public static final int GENERAL = 2;
  public static final int COMPETENCY = 3;

  public static String getStringStatus(int intStatus) {
    switch (intStatus) {
      case REQUIRED:
        return CRMApplication.resources.getString(R.string.required);

      case OPTIONAL:
        return CRMApplication.resources.getString(R.string.optional);

      case GENERAL:
        return CRMApplication.resources.getString(R.string.general);

      case COMPETENCY:
        return CRMApplication.resources.getString(R.string.competency);

      default:
        return CRMApplication.resources.getString(R.string.not_defined);
    }
  }
}