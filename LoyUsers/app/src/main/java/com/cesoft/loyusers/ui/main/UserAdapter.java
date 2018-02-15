package com.cesoft.loyusers.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cesoft.loyusers.R;
import com.cesoft.loyusers.ui.model.UiUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ccasanova on 07/02/2018
 */
@Singleton
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

	private List<UiUser> items = new ArrayList<>();
	public void setUsers(List<UiUser> list) {
		items = list;
	}

	//______________________________________________________________________________________________
	@Inject
	public UserAdapter(){}


	////////////////////////////////////////////////////////////////////////////////////////////////
	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView firstName;
		public TextView lastName;
		public TextView city;
		public ImageView picture;

		public ViewHolder(View itemView) {
			super(itemView);
			firstName = itemView.findViewById(R.id.first_name);
			lastName = itemView.findViewById(R.id.last_name);
			city = itemView.findViewById(R.id.city);
			picture = itemView.findViewById(R.id.picture);
		}
	}

	//______________________________________________________________________________________________
	@Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
		return new ViewHolder(v);
	}

	//______________________________________________________________________________________________
	@Override public void onBindViewHolder(ViewHolder holder, int position) {
		UiUser item = items.get(position);
		holder.firstName.setText(item.getFirstName());
		holder.lastName.setText(item.getLastName());
		holder.city.setText(item.getCity());
		holder.picture.setImageBitmap(null);
		Glide
			.with(holder.picture.getContext())
			.load(item.getPicture())
			.apply(RequestOptions.circleCropTransform())
			.into(holder.picture);
	}

	//______________________________________________________________________________________________
	@Override public int getItemCount() {
		return items.size();
	}

}
