package events;

import java.util.ArrayList;

public class EventManager {

	ArrayList<Event> events;

	public EventManager() {
		events = new ArrayList<Event>();
	}

	public void registerEvent(Event event) {
		// TODO check that no double add is make
		events.add(event);
	}

	public Event findEvent(String url) {

		for (Event pointer : this.events) {
			if (pointer.getURL().equals(url)) {
				return pointer;
			}
		}

		return new EventText("404");
	}
}
