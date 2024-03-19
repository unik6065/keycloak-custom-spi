package io.github.akoserwal;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class KeycloakCustomEventListener implements EventListenerProvider {

  // private static final Logger log = Logger.getLogger(MyListenerProvider.class.getName());

	@Override
	public void onEvent(Event event) {
			// log.info("Event:-"+event.getUserId());
      throw new RuntimeException("Intentional exception for testing logging");

	}

	@Override
	public void onEvent(AdminEvent adminEvent, boolean b) {
			// log.info("Admin Event:-"+adminEvent.getResourceType().name());
      throw new RuntimeException("Intentional exception for testing logging");

	}

	@Override
	public void close() {

	}
}
