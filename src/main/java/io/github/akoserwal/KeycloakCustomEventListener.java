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
        System.out.println("User register as:-"+event.getUserId());

        String url = "http://swaplit.default.svc.cluster.local/user";

        try {
          SimpleHttp.Response resp =
            SimpleHttp.doPost(url, session)
            .param("userId", event.getUserId())
            .asResponse();
          // if (resp.asStatus != 200) {
          //   System.out.println("Error while calling the API");
          // }
        } catch (Exception e) {
          e.printStackTrace();
        // if (resp.asStatus != 200) {
        //   System.out.println("Error while calling the API");
        // }

      }

      if (EventType.DELETE_ACCOUNT.equals(event.getType()) ) {
        System.out.println("User delete as:-"+event.getUserId());
      }

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
