package cn.ac.sec.entity.watch;

import java.util.List;

public class FriendsResp extends BaseResp {

    public List<Friend> friendsList;

    /**
     * @return the friendsList
     */
    public List<Friend> getFriendsList() {
        return friendsList;
    }

    /**
     * @param friendsList the friendsList to set
     */
    public void setFriendsList(List<Friend> friendsList) {
        this.friendsList = friendsList;
    }


}
