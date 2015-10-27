package com.kmji.nghbr.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silverjason on 27/10/15.
 */
public class MessageTest {

    @Test
    public void MessageUserTest(){
        User user = new User();
        user.setFirstName("Jason");
        user.setLastName("Silver");

        Message m = new Message();
        m.setUser(user);
        assertEquals(user, m.getUser());
    }

    @Test
    public void MessageTextTest(){

        String text = "hey my name is Jason";

        Message m = new Message();
        m.setText(text);
        assertEquals(text, m.getText());
    }

    @Test
    public void MessagePostCodeTest(){
        Message m = new Message();
        int postcode = 2036;
        m.setPostCode(postcode);
        assertEquals(postcode, m.getPostCode());
    }
}
