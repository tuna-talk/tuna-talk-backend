package shop.iamhyunjun.tunatalk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    /*@ManyToOne
    private User roomUser;*/

}
