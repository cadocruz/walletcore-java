package org.cadocruz.walletcore.infrastructure.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class EventDispatcher {
    private final Map<String, List<EventHandler>> handlers = new HashMap<>();


    public void register(String eventName, EventHandler handler) {
        handlers.putIfAbsent(eventName, new ArrayList<>());

        List<EventHandler> handlerList = handlers.get(eventName);

        if (handlerList.contains(handler)) {
            return;
        }

        handlerList.add(handler);
    }

    public void dispatch(Event event) {
        List<EventHandler> handlerList = handlers.get(event.getName());

        if (handlerList != null) {
            List<CompletableFuture<Void>> futures = handlerList.stream()
                    .map(handler -> CompletableFuture.runAsync(() -> handler.accept(event)))
                    .toList();

            // Espera que todas as tarefas sejam concluídas
            for (CompletableFuture<Void> future : futures) {
                future.whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Event {} has sent a message to topic: {}", event.getName(),  event.getPayload());
                    } else {
                        log.error("Failed to send message to event {}", event.getName(),  ex);
                    }
                });

            }
        }
    }

    public void unregister(String eventName, EventHandler handler) {
        List<EventHandler> handlerList = handlers.get(eventName);

        if (handlerList != null) {
            handlerList.removeIf(h -> h.equals(handler));
        }
    }

    public void unregisterAll() {
        this.handlers.clear();
    }

    public boolean has(String eventName, EventHandler handler) {
        List<EventHandler> handlerList = handlers.get(eventName);

        if (handlerList != null) {
            return handlerList.contains(handler);
        }
        return false;
    }
}
