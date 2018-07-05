package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.data.entity.User;

public class ViewBuilder {

  public static View createCvView(Context context, Cv cv, int position) {
    View v = LayoutInflater.from(context).inflate(R.layout.cd_cvs_item, null, false);
    TextView attachment = v.findViewById(R.id.cv_item_attachment);
    TextView createdDate = v.findViewById(R.id.cv_item_created_text);

    String attachmentName = String.format("%s %s", context.getString(R.string.attachment),
        position + 1);

    attachment.setText(attachmentName);
    createdDate.setText(cv.getCreated());

    return v;
  }

  public static View createCommentView(Context context, Comment comment) {
    View v = LayoutInflater.from(context).inflate(R.layout.cd_comment_item, null, false);
    TextView createdBy = v.findViewById(R.id.cd_comment_username);
    TextView date = v.findViewById(R.id.cd_comment_date);
    TextView text = v.findViewById(R.id.cd_comment_text);

    User user = comment.getCreatedBy();
    if (user != null){
      createdBy.setText(getDisplayName(user));
    } else {
      createdBy.setText(context.getString(R.string.user_not_found));
    }

    String createdDate = comment.getCreated() != null ? comment.getCreated() : "";
    date.setText(createdDate);

    String commentText = comment.getText() != null ? comment.getText() : "";
    text.setText(commentText);

    return v;
  }

  private static String getDisplayName (User user) {
    String firstName = user.getFirstName() != null ? user.getFirstName() : "";
    String lastName = user.getLastName() != null ? user.getLastName() : "";
    return String.format("%s %s", firstName, lastName);
  }
}
