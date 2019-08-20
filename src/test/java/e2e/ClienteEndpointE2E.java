package e2e;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteEndpointE2E {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql("classpath:e2e/insere_fulano.sql")
    public void obtemClienteFulanoComSucesso() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(CoreMatchers.equalTo(01)));
    }
}
