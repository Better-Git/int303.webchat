package model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Room {

    @Id
    public String name;
    public String room;

    public Room(String roomName, String content) {
    }
}