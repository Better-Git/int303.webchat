package model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;
import java.util.Date;

@Entity
public class Content {

    @Parent
    Key<Room> theRoom;
    @Id
    public Long id;
    public String content;
    public String sender;
    public String rmName;
    @Index
    public Date date;

    /**
     * Simple constructor just sets the date
     */
    public Content() {
        date = new Date();
    }

    /**
     * A convenience constructor
     */
    public Content(String rooms, String content, String sender) {
        this();
        if (rooms != null) {
            theRoom = Key.create(Room.class, rooms);  // Creating the Ancestor key
        } else {
            theRoom = Key.create(Room.class, "default");
        }
        this.rmName = rooms;
        this.content = content;
        this.sender = sender;

    }

    /**
     * Takes all important fields
     *
     * @param rooms
     * @param content
     *
     */
}