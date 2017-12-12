package json;

import java.util.ArrayList;

public class JSONBuilder {

	private ArrayList<JSONObject> content;

	public JSONBuilder() {

	}

	public void addContent(JSONObject e) {
		this.content.add(e);
	}

	public String buildJSON() {
		String json = "{";
		for (int i = 0; i < this.content.size(); i += 1) {
			json += this.content.get(i).buildJSON();
			if (i + 1 < this.content.size()) {
				json += ",";
			}
		}
		return json += "";
	}

}
