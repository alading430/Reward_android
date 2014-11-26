package com.dzg.reward.mine.v1;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.dzg.reward.R;
import com.dzg.reward.reward.v1.RewardWaitActivity;
import com.ta.TAActivity;

/**
 * @author yufeng.dzg
 * @version:2014-11-8
 */

public class MineRewardActivity extends TAActivity implements OnClickListener {
	private TextView btn_back = null;
	private TextView tv_title_name = null;
	private Context context;
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.v1_mine_reward);
		context = this;
		loadView();
		loadData();
	}
	
	private void loadView(){
		
		btn_back = (TextView)findViewById(R.id.btn_back);
		btn_back.setVisibility(View.VISIBLE);
		btn_back.setOnClickListener(this);
		
		tv_title_name = (TextView)findViewById(R.id.tv_title_name);
		tv_title_name.setText("我悬赏的");
		tv_title_name.setVisibility(View.VISIBLE);
		
		listView = (ListView)findViewById(R.id.listview);
		
	}
	
	private void loadData(){
		
		MyAdapter adapter = new MyAdapter(this);
		// TODO test
		for (int i = 0; i < 20; i++) {
			adapter.data.add(i);
		}
		listView.setAdapter(adapter);
	}
	
	class MyAdapter extends BaseAdapter{
		
		public List data = new ArrayList();
		private LayoutInflater inflater;
		
		public MyAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = inflater.inflate(R.layout.v1_mine_reward_listitem, null);
			}
			convertView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context,RewardWaitActivity.class);
					context.startActivity(intent);
				}
			});
			return convertView;
		}
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		}
	}
}
