package routes;

import java.util.ArrayList;

public class RouterManager {

	ArrayList<Router> events;

	public RouterManager() {
		events = new ArrayList<Router>();
	}

	public void registerEvent(Router event) {
		// TODO check that no double add is make
		events.add(event);
	}

	public Router findEvent(String url) {

		for (Router pointer : this.events) {
			if (pointer.getURL().equals(url)) {
				return pointer;
			}
		}

		return new RouterText("404");
	}
}
