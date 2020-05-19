package net.wen.page.network;

import java.util.Objects;

public class FriendModel {
    public int id;
    public String name;
    public String number;
    public String userToken;

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        if(!(other instanceof FriendModel)) {
            return false;
        }

        FriendModel friend = (FriendModel) other;
        return id == friend.id &&
                Objects.equals(name, friend.name) &&
                Objects.equals(number, friend.number) &&
                Objects.equals(userToken, friend.userToken);
    }
}
