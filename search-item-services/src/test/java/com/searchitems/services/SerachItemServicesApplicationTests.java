package com.searchitems.services;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchitems.services.books.BooksService;
import com.searchitems.services.books.GBItemsWrapper;
import com.searchitems.services.books.GBVolumeInfoWrapper;
import com.searchitems.services.books.GBWrapper;

@RunWith(SpringRunner.class)
@RestClientTest(BooksService.class)
@TestPropertySource(locations = "classpath:/search-items.yml")
public class SerachItemServicesApplicationTests {
	@Autowired
	private BooksService booksService;

	private MockRestServiceServer server;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		server = MockRestServiceServer.createServer(restTemplate);
		GBWrapper testdata = new GBWrapper();

		GBVolumeInfoWrapper bookdata = new GBVolumeInfoWrapper();
		bookdata.setTitle("Java");
		GBItemsWrapper itemwrapper = new GBItemsWrapper();
		itemwrapper.setVolumeInfo(bookdata);
		GBItemsWrapper[] itmAry = { itemwrapper };
		testdata.setItems(itmAry);

		String detailsString = objectMapper.writeValueAsString(testdata);

		this.server.expect(requestTo("/books?q=java&maxResults=5"))
				.andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
	}

	@Test
	public void whenCallingGetBookDetails_thenClientMakesCorrectCall() throws Exception {

		GBWrapper details = this.booksService.byNameAndMaxLimit("java", 5);

		assertTrue(details.getItems()[0].getVolumeInfo().getTitle().contains("Java"));
	}

}
