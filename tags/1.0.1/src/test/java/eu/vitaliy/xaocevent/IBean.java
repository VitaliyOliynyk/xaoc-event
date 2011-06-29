package eu.vitaliy.xaocevent;

/**
 *
 * @author Vitaliy Oliynyk
 */
public interface IBean {
    String eventSender(String s);
    String getSenderArgument();
    boolean isReceiveNamedEventWithoutArgument();
}
