package com.heng.springframework.context.event;

import com.heng.springframework.context.ApplicationEvent;

public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationEvent getApplicationContext(){
        return (ApplicationEvent) getSource();
    }
}
