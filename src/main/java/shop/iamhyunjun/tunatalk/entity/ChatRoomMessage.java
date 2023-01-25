package shop.iamhyunjun.tunatalk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomMessage extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long chatRoomMessageId;
    /*@ManyToOne
    private User sender;*/
    private String message;
    @ManyToOne
    private ChatRoom chatRoom;
}
