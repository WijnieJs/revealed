package com.example.two.security.response;


// Started as a response too the jwt service, now also using in other controllers. Though this
// might seem like a bit of a misplaced location,
public class MessageResponse {
	private String message;

	public MessageResponse( String message) {
	    this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
