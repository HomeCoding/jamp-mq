package com.jamp.app.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jamp.app.config.JsonDateSerializer;
import com.jamp.app.config.JsonDateDeSerializer;

public class PageLoadEvent {
	private String url;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date date;
	
	private User user;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PageLoadEvent [url=" + url + ", date=" + date + ", user=" + user + "]";
	}
	
}
