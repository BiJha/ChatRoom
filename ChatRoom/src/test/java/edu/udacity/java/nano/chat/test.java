package edu.udacity.java.nano.chat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(test.class)
public class test {
    @Autowired
    private MockMvc mvc;

    // Test case for login
    @Test
    public void login() throws Exception {
        this.mvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("/login"));

    }

    // Test case for chat
    @Test
    public void chat() throws Exception {
        this.mvc.perform(get("/index")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("/chat"));

    }

    // Test case for userJoin
    @Test
    public void userJoin() throws Exception {
        this.mvc.perform(get("/index?username=Bikash")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("/chat")).andExpect(content().string(containsString("Bikash")));

    }


    // Test case for leave
    @Test
    public void leave() throws Exception {
        this.mvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("/login"));
        this.mvc.perform(get("/index")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("/chat"));


    }
}


