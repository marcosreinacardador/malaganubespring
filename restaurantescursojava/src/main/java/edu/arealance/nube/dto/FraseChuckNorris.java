package edu.arealance.nube.dto;

/*
 * {
"categories": [],
"created_at": "2020-01-05 13:42:24.142371",
"icon_url": "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
"id": "NmpZrZWORyml3Uh_bJmZqQ",
"updated_at": "2020-01-05 13:42:24.142371",
"url": "https://api.chucknorris.io/jokes/NmpZrZWORyml3Uh_bJmZqQ",
"value": "Most folks that eat a bowl of Chuck Norris' Texas style ham & beans immediately blow out thier O-ring."
	}
 */

public class FraseChuckNorris {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FraseChuckNorris(String value) {
		super();
		this.value = value;
	}

	public FraseChuckNorris() {
		
	}

	@Override
	public String toString() {
		return "FraseChuckNorris [value=" + value + "]";
	}
	
	

}
