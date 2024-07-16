package com.aos.utils;

import org.openqa.selenium.By;

public class WebElementInfo {
	private By locator;
	private String description;

	public WebElementInfo(By locator, String description) {
		this.locator = locator;
		this.description = description;
	}

	public By getLocator() {
		return locator;
	}

	public String getDescription() {
		return description;
	}
}
