package com.searchitems.services.books;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GBWrapper {
	private String kind;
	private GBItemsWrapper[] items;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public GBItemsWrapper[] getItems() {
		return items;
	}

	public void setItems(GBItemsWrapper[] items) {
		this.items = items;
	}
}
