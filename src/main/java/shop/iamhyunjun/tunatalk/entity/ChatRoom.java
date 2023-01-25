package shop.iamhyunjun.tunatalk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long chatRoomId;
    private String roomName;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }
}
