package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.User;

import java.util.List;

class CommentsAdapter extends BaseAdapter {
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private List<Comment> mCommentsList;

  CommentsAdapter(Context context, List<Comment> data) {
    mContext = context;
    mCommentsList = data;
    mLayoutInflater = LayoutInflater.from(mContext);
  }

  public void setData(List<Comment> newData) {
    mCommentsList = newData;
    this.notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return mCommentsList.size();
  }

  @Override
  public Object getItem(int position) {
    return mCommentsList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View v = mLayoutInflater.inflate(R.layout.cd_comment_item, parent, false);
    TextView createdBy = v.findViewById(R.id.cd_comment_username);
    TextView date = v.findViewById(R.id.cd_comment_date);
    TextView text = v.findViewById(R.id.cd_comment_text);

    Comment comment = (Comment) getItem(position);
    User user = comment.getCreatedBy();
    createdBy.setText(user.getFirstName() + " " + user.getLastName());
    date.setText(comment.getCreated());
    text.setText(comment.getText());

    return v;
  }
}