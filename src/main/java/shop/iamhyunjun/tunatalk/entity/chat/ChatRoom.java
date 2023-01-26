package shop.iamhyunjun.tunatalk.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.BaseTimeEntity;
import shop.iamhyunjun.tunatalk.entity.user.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long chatRoomId;
    @ManyToOne
    private User user1;
    @ManyToOne
    private User user2;
    private String lastMessage = "";

    public ChatRoom(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public void ChatRoomLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void ChatRoomUser1Delete() {
        this.user1 = null;
    }

    public void ChatRoomUser2Delete() {
        this.user2 = null;
    }

    //    public ChatRoom(String roomName) {
//        this.roomName = roomName;
//    }
}
