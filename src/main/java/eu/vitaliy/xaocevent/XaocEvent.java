package eu.vitaliy.xaocevent;

import org.springframework.context.ApplicationEvent;

/**
 * @author Vitaliy Oliynyk
 */
public class XaocEvent extends ApplicationEvent {

    private Object eventData;
    private String eventID;
    private Metadata metadata = new Metadata(false);
    public XaocEvent(Object source, Object eventData, String eventID) {
        super(source);
        this.eventData = eventData;
        this.eventID   = eventID;
    }

    public Object getEventData() {
        return eventData;
    }

    public void setEventData(Object eventData) {
        this.eventData = eventData;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    protected static class Metadata
    {
        private boolean fromAnnotatedObservable;

        public Metadata() {
        }

        public Metadata(boolean fromAnnotatedObservable) {
            this.fromAnnotatedObservable = fromAnnotatedObservable;
        }

        public boolean isFromAnnotatedObservable() {
            return fromAnnotatedObservable;
        }

        public void setFromAnnotatedObservable(boolean fromAnnotatedObservable) {
            this.fromAnnotatedObservable = fromAnnotatedObservable;
        }
    }
}
