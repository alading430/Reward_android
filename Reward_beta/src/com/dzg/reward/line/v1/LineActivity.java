package com.dzg.reward.line.v1;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.dzg.reward.R;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-11-3
 */

public class LineActivity extends TAActivity implements OnClickListener {

	private TextView btn_back = null;
	
	private TextView tv_title_name = null;
	
	private ListView line_listview = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_line_body);
		loadView();
		loadData();
	}
	
	private void loadView(){
		
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		
		tv_title_name = (TextView)findViewById(R.id.tv_title_name);
		tv_title_name.setText("春运排队不用忙");
		tv_title_name.setVisibility(View.VISIBLE);
		
		line_listview = (ListView)findViewById(R.id.line_listview);
		
	}

	private void loadData(){
		
		MyAdapter adapter = new MyAdapter(this);
		// TODO test
		for (int i = 0; i < 20; i++) {
			adapter.data.add(i);
		}
		line_listview.setAdapter(adapter);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;

		default:
			break;
		}
	}
	
	class MyAdapter extends BaseAdapter{
		
		private LayoutInflater inflater;
		
		public List<Integer> data = new ArrayList<Integer>();
		
		public MyAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.v1_line_listview_item, null);
			ViewHolder holder = (ViewHolder)convertView.getTag(R.id.reward_line_tag);
			if(holder == null){
				holder = new ViewHolder();
				holder.txt_1 = (TextView)convertView.findViewById(R.id.txt_1);
				holder.txt_2 = (TextView)convertView.findViewById(R.id.txt_2);
				holder.txt_3 = (TextView)convertView.findViewById(R.id.txt_3);
				holder.txt_4 = (TextView)convertView.findViewById(R.id.txt_4);
				holder.txt_5 = (TextView)convertView.findViewById(R.id.txt_5);
				convertView.setTag(R.id.reward_line_tag, holder);
			}
			// TODO add info
			return convertView;
		}
	}
	
	static class ViewHolder{
		TextView txt_1;
		TextView txt_2;
		TextView txt_3;
		TextView txt_4;
		TextView txt_5;
	}
	
}
