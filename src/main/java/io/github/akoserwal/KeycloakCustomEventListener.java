package io.github.akoserwal;

import org.keycloak.events.Event;
import org.keycloak.events.EventType;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.OperationType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;

import org.keycloak.broker.provider.util.SimpleHttp;

public class KeycloakCustomEventListener implements EventListenerProvider {

  private final KeycloakSession session;

  public KeycloakCustomEventListener(KeycloakSession session) {
    this.session = session;
  }

	@Override
	public void onEvent(Event event) {
    if (EventType.REGISTER.equals(event.getType()) ) {
      System.out.println("User register:-"+event.getUserId());
      Producer.publishEvent("user-registration-events", event.getUserId());

    }
    if (EventType.DELETE_ACCOUNT.equals(event.getType()) ) {
      System.out.println("User delete:-"+event.getUserId());
      Producer.publishEvent("user-delete-events", event.getUserId());
    }
  }

	@Override
	public void onEvent(AdminEvent adminEvent, boolean b) {
    if (OperationType.CREATE.equals(adminEvent.getOperationType()) ) {
      System.out.println("User register from admin:-"+adminEvent.getAuthDetails().getUserId());
    }

    if(OperationType.DELETE.equals(adminEvent.getOperationType()) ) {
      System.out.println("User delete from admin:-"+adminEvent.getAuthDetails().getUserId());
    }

	}

	@Override
	public void close() {

	}
}
