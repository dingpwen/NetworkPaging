package net.wen.page.network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FriendListAdapter extends PagedListAdapter<FriendModel, FriendListAdapter.FriendViewHolder> {
    private Context mContext;
    protected FriendListAdapter(@NonNull Context context, @NonNull DiffUtil.ItemCallback<FriendModel> diffCallback) {
        super(diffCallback);
        mContext = context;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(mContext).inflate(R.layout.friend_list_item, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView number;
        ImageView image;

        FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            image = itemView.findViewById(R.id.header_img);
        }

        void bind(FriendModel friend) {
            number.setVisibility(View.GONE);
            if(!friend.name.isEmpty()) {
                name.setText(friend.name);
                if(!friend.number.isEmpty()) {
                    number.setText(friend.number);
                    number.setVisibility(View.VISIBLE);
                }
            } else if(!friend.number.isEmpty()) {
                name.setText(friend.number);
            } else {
                name.setText(friend.userToken);
            }
        }
    }
}
