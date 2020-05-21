package net.wen.page.network;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private FriendListAdapter mAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListView();

        final FriendViewModel viewModel = new ViewModelProvider(this).get(FriendViewModel.class);
        viewModel.friendList.observe(this, mAdaper::submitList);
        //viewModel.invalidateDataSource();
        viewModel.loadStatus.observe(this, loadStatus -> mAdaper.updateLoadStatus(loadStatus));
    }

    private void initListView() {
        mAdaper = new FriendListAdapter(this, new DiffUtil.ItemCallback<FriendModel>(){

            @Override
            public boolean areItemsTheSame(@NonNull FriendModel oldItem, @NonNull FriendModel newItem) {
                return (oldItem.id == newItem.id);
            }

            @Override
            public boolean areContentsTheSame(@NonNull FriendModel oldItem, @NonNull FriendModel newItem) {
                return oldItem.equals(newItem);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(mAdaper);
    }
}
