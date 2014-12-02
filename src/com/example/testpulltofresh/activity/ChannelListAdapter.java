package com.example.testpulltofresh.activity;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testpulltofresh.R;
import com.example.testpulltofresh.ScheduleData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ChannelListAdapter extends BaseAdapter{
	private Context context;
	private List<ScheduleData> listItems;
	private LayoutInflater inflater;
	DisplayImageOptions options;	
	
	final class ViewHolder{							//自定义控件集合
		ImageView userHeadImg;
		TextView userNameText;
		TextView sendTimeText;
		TextView scheduleContentText;
		TextView remindDateText;
		TextView attentionAmountText;
		TextView recordAmountText;
		
		ViewHolder(View convertView) {
			//获取控件对象
			userHeadImg = (ImageView) convertView.findViewById(R.id.iv_user_head);
			userNameText=(TextView)convertView.findViewById(R.id.tv_user_name);
			sendTimeText=(TextView)convertView.findViewById(R.id.tv_send_time);
			scheduleContentText=(TextView)convertView.findViewById(R.id.tv_schedule_content);
			remindDateText=(TextView)convertView.findViewById(R.id.tv_remind_date);
			attentionAmountText=(TextView)convertView.findViewById(R.id.tv_attention_amount);
			recordAmountText=(TextView)convertView.findViewById(R.id.tv_record_amount);
			
			options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_stub)
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.displayer(new RoundedBitmapDisplayer(20))
			.build();
		}
	}
	
	public ChannelListAdapter(Context context,List<ScheduleData> listItems) {
		this.context=context;
		inflater=LayoutInflater.from(context);	//创建视图容器并设置上下文
		this.listItems=listItems;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * ListView Item设置
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup Parent) {
		// TODO Auto-generated method stub 
		//自定义视图
		ViewHolder holder=null;
		if(convertView==null){
			
			//获取listitem_schedule布局文件的视图
			convertView=inflater.inflate(R.layout.listitem_schedule, null);
			holder=new ViewHolder(convertView);
			//设置控件集到convertView
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		//设置文字和图片				
		ImageLoader.getInstance().displayImage(listItems.get(position).getImageUrl(), holder.userHeadImg, options);
		holder.userNameText.setText(listItems.get(position).getUserName());
		holder.sendTimeText.setText(listItems.get(position).getSendTime());
		holder.scheduleContentText.setText(listItems.get(position).getScheduleContent());
		holder.remindDateText.setText(listItems.get(position).getRemindDate());
		holder.attentionAmountText.setText(listItems.get(position).getAttentionAmount());
		holder.recordAmountText.setText(listItems.get(position).getRecordAmount());
		
		//点击事件
		holder.userHeadImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "您已点击图片",
						Toast.LENGTH_SHORT).show();
			}
		});
		
		return convertView;
	}
	
	

}
