package shop.iamhyunjun.tunatalk.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.user.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomUsers {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long chatRoomUsersId;
    @ManyToOne
    private ChatRoom chatRoom;
    @ManyToOne
    private User roomUser;

}
