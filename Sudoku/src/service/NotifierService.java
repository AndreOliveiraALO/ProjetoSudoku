package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static service.EventEnum.LIMPAR_AREA;

public class NotifierService {

    private final Map<EventEnum, List<EventListener>> listeners = new HashMap<>(){{
        put(LIMPAR_AREA, new ArrayList<>());
    }};

    public void subscribe(final EventEnum eventType, EventListener listener){
        var selectedListeners = listeners.get(eventType);
        selectedListeners.add(listener);
    }

    public void notify(final EventEnum eventType){
        listeners.get(eventType).forEach(l -> l.update(eventType));
    }

}
