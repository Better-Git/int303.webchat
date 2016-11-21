/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import java.util.Date;

/**
 *
 * @author OWNER
 */
@Entity
public class MyUser {
    @Parent
    public Key<Room> theRoom;
    @Id
    public Long userId;
    public String uName;
    public String rmName;
    @Index
    public Date date;
    
    /**
     * Simple constructor just sets the date
     */
    public MyUser() {
        date = new Date();
    }

    /**
     * A convenience constructor
     */
    public MyUser( String uname,String rooms) {
        this();
        
        if (rooms != null) {
            theRoom = Key.create(Room.class, rooms);  // Creating the Ancestor key
        } else {
            theRoom = Key.create(Room.class, "default");
        }
        
        this.rmName = rooms;
        
        this.uName = uname;

    }

    public void setTheRoom(Key<Room> theRoom) {
        this.theRoom = theRoom;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public Key<Room> getTheRoom() {
        return theRoom;
    }

    public Long getUserId() {
        return userId;
    }

    public String getuName() {
        return uName;
    }

    public String getRmName() {
        return rmName;
    }
    
}
