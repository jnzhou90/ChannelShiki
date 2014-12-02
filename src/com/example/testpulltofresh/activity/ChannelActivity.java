package com.example.testpulltofresh.activity;

import java.util.ArrayList;
import java.util.List;
import com.example.testpulltofresh.Constants;
import com.example.testpulltofresh.R;
import com.example.testpulltofresh.ScheduleData;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
//import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ChannelActivity extends Activity {
	private PullToRefreshListView pullToRefreshListView;

	private List<ScheduleData> listItems;
	private ChannelListAdapter channelListAdapter;

	private String[] user_names = Constants.USER_NAMES;
	private String[] send_times = Constants.SEND_TIMES;
	private String[] schedule_contents = Constants.SCHEDULE_CONTENTS;
	private String[] remind_dates = Constants.REMIND_DATES;
	private String[] attention_amounts = Constants.ATTENTION_AMOUNTS;
	private String[] record_amounts = Constants.RECORD_AMOUNTS;
	String[] imageUrls = Constants.IMAGES;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel);

		pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.ptflv_test);
		View headView = LayoutInflater.from(this).inflate( // 添加listview头部
				R.layout.listitem_schedule_headview, null);

		// 设置监听器
		pullToRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						String label = DateUtils.formatDateTime(
								getApplicationContext(),
								System.currentTimeMillis(),
								DateUtils.FORMAT_SHOW_TIME
										| DateUtils.FORMAT_SHOW_DATE
										| DateUtils.FORMAT_ABBREV_ALL);

						// Update the LastUpdatedLabel
						refreshView.getLoadingLayoutProxy()
								.setLastUpdatedLabel(label);

						// Do work to refresh the list here.
						new GetDataTask().execute();
					}
				});
		pullToRefreshListView
				.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

					@Override
					public void onLastItemVisible() {
						// TODO Auto-generated method stub
						Toast.makeText(ChannelActivity.this, "End of List!",
								Toast.LENGTH_SHORT).show();
					}
				});
		pullToRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(ChannelActivity.this, "第" + (position) + "幅图",
						Toast.LENGTH_SHORT).show();
			}
		});

		listItems = this.getData();
		channelListAdapter = new ChannelListAdapter(this, listItems);// 创建适配器

		// 绑定Adapter
		ListView listView = pullToRefreshListView.getRefreshableView();
		listView.setAdapter(channelListAdapter);
		listView.addHeaderView(headView);
	}

	public List<ScheduleData> getData() {
		List<ScheduleData> listItems = new ArrayList<ScheduleData>();
		for (int i = 0; i < user_names.length; i++) {
			ScheduleData sData = new ScheduleData();
			sData.setImageUrl(imageUrls[i]);
			sData.setUserName(user_names[i]);
			sData.setSendTime(send_times[i]);
			sData.setScheduleContent(schedule_contents[i]);
			sData.setRemindDate(remind_dates[i]);
			sData.setAttentionAmount(attention_amounts[i]);
			sData.setRecordAmount(record_amounts[i]);
			
			listItems.add(sData);
		}
		return listItems;
	}

	private class GetDataTask extends AsyncTask<Void, Void, ScheduleData> {

		@Override
		protected ScheduleData doInBackground(Void... params) {
			// TODO Auto-generated method stub
			// Simulates a background job.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			ScheduleData sData = new ScheduleData();
			try {

				sData = new ScheduleData();
				sData.setImageUrl(imageUrls[2]);
				sData.setUserName("下拉后更新后的名字111");
				sData.setSendTime("几分钟前");
				sData.setScheduleContent("下拉后更新后的内容");
				sData.setRemindDate("改后的时间");
				sData.setAttentionAmount("999人关注");
				sData.setRecordAmount("9999条记录");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				setTitle("出错了啊");
			}

			return sData;
		}

		// 这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
		// 根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
		@Override
		protected void onPostExecute(ScheduleData result) {
			// 在头部增加新添内容
			listItems.add(0, result);

			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
			channelListAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			pullToRefreshListView.onRefreshComplete();

			super.onPostExecute(result);// 这句是必有的，AsyncTask规定的格式
		}
	}

}
