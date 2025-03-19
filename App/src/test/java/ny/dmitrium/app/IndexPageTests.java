package ny.dmitrium.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest(classes = ApplicationRunner.class)
@AutoConfigureMockMvc
class IndexPageTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void loadIndexTest() throws Exception {
		mockMvc.perform(get("/")).
				andExpect(status().isOk());
	}

	@Test
	void testHealthHerbsSection() throws Exception {
		mockMvc.perform(get("/")).
				andExpect(xpath("//section[@class='herb']").exists());
	}


	@Test
	void testMintImage() throws Exception {
		mockMvc.perform(get("/")).
				andExpect(xpath("//img[@alt='Мята']").exists());
	}


}
