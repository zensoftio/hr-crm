package com.erkprog.zensofthrcrm.ui.candidates.candidateDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.erkprog.zensofthrcrm.R;
import com.erkprog.zensofthrcrm.data.entity.Cv;

import java.util.List;

class CvsAdapter extends BaseAdapter {
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private List<Cv> mCvsList;

  CvsAdapter(Context context, List<Cv> cvsList) {
    mContext = context;
    mCvsList = cvsList;
    mLayoutInflater = LayoutInflater.from(mContext);
  }

  public void setData(List<Cv> newData) {
    mCvsList = newData;
    this.notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return mCvsList.size();
  }

  @Override
  public Object getItem(int position) {
    return mCvsList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View v = mLayoutInflater.inflate(R.layout.cd_cvs_item, parent, false);
    TextView attachment = v.findViewById(R.id.cv_item_attachment);
    TextView createdDate = v.findViewById(R.id.cv_item_created_text);

    Cv cvItem = (Cv) getItem(position);
    attachment.setText("attachment" + String.valueOf(position + 1));
    createdDate.setText(cvItem.getCreated().toString());

    return v;
  }
}