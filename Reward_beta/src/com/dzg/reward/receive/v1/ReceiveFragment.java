package com.dzg.reward.receive.v1;

import java.util.ArrayList;
import java.util.List;
import com.dzg.reward.R;
import com.dzg.reward.abs.BaseFragment;
import com.dzg.reward.custom.V1Header;
import com.dzg.reward.model.local.ReceiveInfo;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.ta.annotation.TAInjectView;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author yufeng.dzg
 * @version:2014-11-20
 */

public class ReceiveFragment extends BaseFragment implements OnRefreshListener<ListView>{
	
	@TAInjectView(id = R.id.pull_listview)
	PullToRefreshListView plv;
	@TAInjectView(id = R.id.header)
	V1Header header;
	static MyAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		setContentView(R.layout.v1_main);
		loadData();
		return mainView;
	}
	
	private void loadData(){
		header.loadView(activity);
		header.tv_title_name.setText(R.string.main_receive);
		adapter = new MyAdapter(mContext);
		// TODO test
		for (int i = 0; i < 1; i++) {
			adapter.data.add(i);
		}
		plv.setAdapter(adapter);
		plv.setOnRefreshListener(ReceiveFragment.this);
		plv.setVisibility(View.VISIBLE);
	}
	
	
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		new GetDataTask(refreshView).execute();
	}
	
	private static class GetDataTask extends AsyncTask<Void, Void, Void> {

		PullToRefreshBase<?> mRefreshedView;

		public GetDataTask(PullToRefreshBase<?> refreshedView) {
			mRefreshedView = refreshedView;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Simulates a background job.
			for (int i = 0; i < 1; i++) {
				adapter.data.add(i);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			adapter.notifyDataSetChanged();
			mRefreshedView.onRefreshComplete();
			super.onPostExecute(result);
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
			if(convertView == null){
				convertView = inflater.inflate(R.layout.v1_receive_listitem, null);
			}
			convertView.setTag(position);
			/********************悬赏记录********************/
//			ViewHolder holder = (ViewHolder)convertView.getTag(R.id.reward_info);
//			
//			if(holder == null){
//				holder = new ViewHolder();
//				holder.mIcon = (ImageView) convertView.findViewById(R.id.icon);
//				holder.mTitle = (TextView) convertView.findViewById(R.id.title);
//				holder.mCount = (TextView) convertView.findViewById(R.id.count);
//				holder.mSelledCount = (TextView) convertView.findViewById(R.id.selled_count);
//				holder.mAddress = (TextView) convertView.findViewById(R.id.address);
//				convertView.setTag(R.id.reward_info, holder);
//			}
			convertView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(activity, ReceiveDetailActivity.class);
					activity.startActivity(intent);
				}
			});
			return convertView;
		}
	}
	
	static class ViewHolder{
		
		private ImageView mIcon;
		private TextView mTitle;
		private TextView mCount;
		private TextView mSelledCount;
		private TextView mAddress;
		public void update(ReceiveInfo Receive){
			mTitle.setText(Receive.getName());
			mCount.setText(""+Receive.getCount());
			mSelledCount.setText(""+Receive.getSelledCount());
			mAddress.setText(Receive.getAddress());
//			loadImageForView(Receive.getIconUrl()==null?"":Receive.getIconUrl(),mIcon);
		}
	}
	
}
