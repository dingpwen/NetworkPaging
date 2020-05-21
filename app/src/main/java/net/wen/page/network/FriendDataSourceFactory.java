package net.wen.page.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class FriendDataSourceFactory extends DataSource.Factory<Integer, FriendModel> {
    MutableLiveData<FriendDataSource> friendDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, FriendModel> create() {
        final FriendDataSource latestSource = new FriendDataSource();
        friendDataSource.postValue(latestSource);
        return latestSource;
    }
}
