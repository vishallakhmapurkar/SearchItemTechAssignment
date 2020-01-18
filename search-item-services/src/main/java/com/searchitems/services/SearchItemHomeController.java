package com.searchitems.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.searchitems.services.books.BooksService;
import com.searchitems.services.books.GBItemsWrapper;
import com.searchitems.services.books.GBWrapper;
import com.searchitems.services.books.SearchItem;

@Controller
public class SearchItemHomeController {
	protected Logger logger = Logger.getLogger(SearchItemHomeController.class.getName());

	@Autowired
	private BooksService booksService;

	@Autowired
	private Environment env;

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/searchitem", method = RequestMethod.GET)
	public String searchForm(Model model) {
		model.addAttribute("searchCriteria", new SearchCriteria());
		return "searchitem";
	}

	@RequestMapping(value = "/searchitem/dosearch")
	public String doSearch(Model model, SearchCriteria criteria, BindingResult result) {
		logger.info("web-service search() invoked: " + criteria);
		GBWrapper res = null;
		criteria.validate(result);

		if (result.hasErrors())
			return "searchitem";

		String searchtext = criteria.getSearchText();
		if (StringUtils.hasText(searchtext)) {
			res = booksService.byNameAndMaxLimit(searchtext, Integer.parseInt(env.getProperty("maxResults")));
		}
		if (res != null)
			model.addAttribute("searchItems", getSearchItems(res));

		return "searchitems";
	}

	@RequestMapping("/searchitems")
	public String goHome() {
		return "index";
	}
	@RequestMapping("/books")
	public ResponseEntity<GBWrapper> byNameAndMaxLimit(@RequestParam(value = "q") String q,
			@RequestParam(value = "maxResults") Integer maxResults) {
		String url = UriComponentsBuilder.fromHttpUrl(env.getProperty("google.bookapiurl"))
				.queryParam("q", q).queryParam("maxResults", maxResults).queryParam("key", env.getProperty("google.apikey")).toUriString();
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity(url, GBWrapper.class);
	}
	private List<SearchItem> getSearchItems(GBWrapper res) {
		List<SearchItem> books = new ArrayList<SearchItem>();

		for (GBItemsWrapper item : res.getItems()) {
			SearchItem book = new SearchItem();
			book.setKind(res.getKind());
			book.setTitle(item.getVolumeInfo().getTitle());
			book.setAuthors_artists(String.join(",", item.getVolumeInfo().getAuthors()));
			books.add(book);
		}
		return books;
	}
}
