package net.wen.page.network;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FriendDataSource extends PageKeyedDataSource<Integer, FriendModel> {
    private static final String BASE_URL = "http://172.16.200.206:5000/friend/pagelist";
    private static final String TOKEN = "8+B30mCUJ8AbOyO3ZYz7lWG+zBUEIHHxS3nknBJNn/s=";
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, FriendModel> callback) {
        //List<FriendModel> items = fetchItems(0, params.requestedLoadSize);
        HashMap<String, String> map = new HashMap<>();
        map.put("start", "0");
        map.put("pagesize", "" + params.requestedLoadSize);
        map.put("token", TOKEN);
        OkHttpUtil.baseGet(BASE_URL, map, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = Objects.requireNonNull(response.body()).string();
                Log.d("wenpd", "result" + result);
                try{
                    JSONObject object = new JSONObject(result);
                    List<FriendModel> items = new ArrayList<>();
                    if(object.getInt("status") == 200) {
                        JSONArray friends = object.getJSONArray("friends");
                        for(int i=0; i < friends.length(); ++i) {
                            JSONObject friend = friends.getJSONObject(i);
                            FriendModel user = new FriendModel();
                            user.id = friend.getInt("id");
                            user.name = friend.getString("name");
                            user.number = friend.getString("number");
                            user.userToken = friend.getString("user_token");
                            items.add(user);
                        }
                    }
                    if(items.size() > 0) {
                        callback.onResult(items, 0, 1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FriendModel> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FriendModel> callback) {
        //List<FriendModel> items = fetchItemsAfter(params.key, params.requestedLoadSize);
        //callback.onResult(items, params.key + 1);
        HashMap<String, String> map = new HashMap<>();
        map.put("start", "" + params.key);
        map.put("pagesize", "" + params.requestedLoadSize);
        map.put("token", TOKEN);
        OkHttpUtil.baseGet(BASE_URL, map, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //todo
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = Objects.requireNonNull(response.body()).string();
                try{
                    JSONObject object = new JSONObject(result);
                    List<FriendModel> items = new ArrayList<>();
                    if(object.getInt("status") == 200) {
                        JSONArray friends = object.getJSONArray("friends");
                        for(int i=0; i < friends.length(); ++i) {
                            JSONObject friend = friends.getJSONObject(i);
                            FriendModel user = new FriendModel();
                            user.id = friend.getInt("id");
                            user.name = friend.getString("name");
                            user.number = friend.getString("number");
                            user.userToken = friend.getString("user_token");
                            items.add(user);
                        }
                    }
                    if(items.size() > 0) {
                        callback.onResult(items, params.key + 1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<FriendModel> fetchItems(Integer key, int requestedLoadSize) {
        return null;
    }

    private List<FriendModel> fetchItemsAfter(Integer key, int requestedLoadSize) {
        return fetchItems(key, requestedLoadSize);
    }
}
