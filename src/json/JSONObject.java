package json;

public class JSONObject implements JSONBuildable {

	String tag;
	String content;

	public JSONObject(String tag, int integer) {
		this.tag = tag;
		this.content = String.valueOf(integer);
	}

	public JSONObject(String tag, long longeinteger) {
		this.tag = tag;
		this.content = String.valueOf(longeinteger);
	}

	public JSONObject(String tag, double doubl) {
		this.tag = tag;
		this.content = String.valueOf(doubl);
	}

	public JSONObject(String tag, String string) {
		this.tag = tag;
		this.content = "\"" + string + "\"";
	}

	public JSONObject(JSONBuildable buildable) {
		this.tag = null;
		this.content = buildable.buildJSON();
	}

	public String buildJSON() {
		return this.tag + ": " + this.content;
	}
}
