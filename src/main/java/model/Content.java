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
    public Key<Room> theRoom;
    @Id
    public Long id;
    public String content;
    public String sender;
    public String rmName;
    @Index
    public Date date ;
    

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

    public void setTheRoom(Key<Room> theRoom) {
        this.theRoom = theRoom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Key<Room> getTheRoom() {
        return theRoom;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getRmName() {
        return rmName;
    }

    public Date getDate() {
        return date;
    }

    

    /**
     * Takes all important fields
     *
     * @param rooms
     * @param content
     *
     */
    
}