package net.wen.page.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FriendViewModel extends ViewModel {
    LiveData<PagedList<FriendModel> > friendList;
    private DataSource<Integer, FriendModel> mostRecentDataSource;

    public FriendViewModel() {
        FriendDataSourceFactory fatory = new FriendDataSourceFactory();
        mostRecentDataSource = fatory.create();
        Executor myExecutor = Executors.newFixedThreadPool(5);
        friendList = new LivePagedListBuilder<>(fatory, 20)
                .setFetchExecutor(myExecutor)
                .build();
    }

    public void invalidateDataSource() {
        mostRecentDataSource.invalidate();
    }
}
