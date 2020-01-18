package com.searchitems.services;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class SearchCriteria {

	private String searchText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public boolean isValid() {
		return (StringUtils.hasText(searchText));
	}

	public boolean validate(Errors errors) {

		if (!StringUtils.hasText(searchText)) {
			errors.rejectValue("searchText", "empty", "Please enter search text");
			

		} else if (StringUtils.hasText(searchText)) {
		}
		return errors.hasErrors();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (StringUtils.hasText(searchText) ? " text: " + searchText : "");
	}
}
