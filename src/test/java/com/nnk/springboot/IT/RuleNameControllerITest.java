package com.nnk.springboot.IT;


import com.nnk.springboot.domain.RuleName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RuleNameControllerITest extends AllTestITest {

    @DisplayName("Home")
    @Nested //allows to have an inner test class and group several test classes under the same parent
    class Home {

        @Test
        void getHome_withNoAuth() throws Exception {
            mockMvc.perform(get("/ruleName/list").with(anonymous()))
                    .andExpect(status().isUnauthorized());
            //.andExpect(redirectedUrl("/login"));
        }

        @Test
        void getHome_withAdmin() throws Exception {
            mockMvc.perform(get("/ruleName/list").with(httpBasic("admin","admin")))
                    .andExpect(status().isOk());
        }

        @Test
        void getHome_withUser() throws Exception {
            mockMvc.perform(get("/ruleName/list").with(httpBasic("user","user")))
                    .andExpect(status().isOk());
        }

    }

    @DisplayName("Add form")
    @Nested
    class AddForm {

        @Test
        void addForm_withNoAuth() throws Exception {
            mockMvc.perform(get("/ruleName/add").with(anonymous()))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void addForm_withAdmin() throws Exception {
            mockMvc.perform(get("/ruleName/add").with(httpBasic("admin","admin")))
                    .andExpect(status().isOk());
        }

        @Test
        void addForm_withUser() throws Exception {
            mockMvc.perform(get("/ruleName/add").with(httpBasic("user","user")))
                    .andExpect(status().isOk());
        }

    }

    @DisplayName("Validate")
    @Nested
    @Transactional //allows setting propagation, isolation, read-only, and rollback conditions for the transaction
    class Validate {

        @Test
        void validate_withNoAuth() throws Exception {
            mockMvc.perform(post("/ruleName/validate").with(anonymous()))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @Rollback
        void validate_withAdmin() throws Exception {
            RuleName ruleName = new RuleName("test","test","test","test","test","test");
            mockMvc.perform(post("/ruleName/validate").with(httpBasic("admin","admin")).flashAttr("ruleName",ruleName))
                    .andExpect(status().is3xxRedirection());
        }

        @Test
        @Rollback
        void validate_withUser() throws Exception {
            RuleName ruleName = new RuleName("test","test","test","test","test","test");
            mockMvc.perform(post("/ruleName/validate").with(httpBasic("user","user")).flashAttr("ruleName",ruleName))
                    .andExpect(status().is3xxRedirection());
        }

        @Test
        void validate_withInvalidField() throws Exception {
            RuleName ruleName = new RuleName(null,"test","test","test","test","test");
            mockMvc.perform(post("/ruleName/validate").with(httpBasic("user","user")).flashAttr("ruleName",ruleName))
                    .andExpect(status().isOk())
                    .andExpect(model().hasErrors());
        }


    }

    @DisplayName("Show update Form")
    @Nested
    class  ShowUpdateForm {

        @Test
        void showUpdateForm_withNoAuth() throws Exception {
            mockMvc.perform(get("/ruleName/update/1").with(anonymous()))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void showUpdateForm_withAdmin() throws Exception {
            mockMvc.perform(get("/ruleName/update/1").with(httpBasic("admin","admin")))
                    .andExpect(status().isOk());
        }

        @Test
        void showUpdateForm_withUser() throws Exception {
            mockMvc.perform(get("/ruleName/update/1").with(httpBasic("user","user")))
                    .andExpect(status().isOk());
        }

    }

    @DisplayName("Update")
    @Nested
    @Transactional
    class Update {

        @Test
        void update_withNoAuth() throws Exception {
            mockMvc.perform(post("/ruleName/update/1").with(anonymous()))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @Rollback
        void update_withAdmin() throws Exception {
            RuleName ruleName = new RuleName("test","test","test","test","test","test");
            mockMvc.perform(post("/ruleName/update/1").with(httpBasic("admin","admin")).flashAttr("ruleName",ruleName))
                    .andExpect(status().is3xxRedirection());
        }

        @Test
        @Rollback
        void update_withUser() throws Exception {
            RuleName ruleName = new RuleName("test","test","test","test","test","test");
            mockMvc.perform(post("/ruleName/update/1").with(httpBasic("user","user")).flashAttr("ruleName",ruleName))
                    .andExpect(status().is3xxRedirection());
        }

        @Test
        void update_withInvalidField() throws Exception {
            RuleName ruleName = new RuleName(null,"test","test","test","test","test");
            mockMvc.perform(post("/ruleName/update/1").with(httpBasic("user","user")).flashAttr("ruleName",ruleName))
                    .andExpect(status().isOk())
                    .andExpect(model().hasErrors());
        }

    }

    @DisplayName("Delete")
    @Nested
    @Transactional
    class Delete {

        @Test
        void delete_withNoAuth() throws Exception {
            mockMvc.perform(get("/ruleName/delete/1").with(anonymous()))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void delete_withAdmin() throws Exception {
            mockMvc.perform(get("/ruleName/delete/1").with(httpBasic("admin","admin")))
                    .andExpect(status().is3xxRedirection());
        }

        @Test
        void delete_withUser() throws Exception {
            mockMvc.perform(get("/ruleName/delete/1").with(httpBasic("user","user")))
                    .andExpect(status().is3xxRedirection());
        }

    }
}
