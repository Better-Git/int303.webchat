package model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Content {

    @Parent
    Key<Room> theRoom;
    @Id
    public Long id;
    public String content;

    /**
     * Simple constructor just sets the date
     */
    public Content() {
    }

    /**
     * A convenience constructor
     */
    public Content(String rooms, String content) {
        this();
        if (rooms != null) {
            theRoom = Key.create(Room.class, rooms);  // Creating the Ancestor key
        } else {
            theRoom = Key.create(Room.class, "default");
        }
        this.content = content;
    }

    /**
     * Takes all important fields
     *
     * @param rooms
     * @param content
     *
     */
}