package net.wen.page.network;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private FriendViewModel viewModel;
    private FriendListAdapter mAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListView();

        viewModel = new ViewModelProvider(this).get(FriendViewModel.class);
        viewModel.friendList.observe(this, mAdaper::submitList);
        //viewModel.invalidateDataSource();
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
