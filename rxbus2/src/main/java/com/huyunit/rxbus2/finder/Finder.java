package com.huyunit.rxbus2.finder;

import com.huyunit.rxbus2.event.EventType;
import com.huyunit.rxbus2.event.PublisherEvent;
import com.huyunit.rxbus2.event.SubscriberEvent;

import java.util.Map;
import java.util.Set;

/**
 * Finds publish and subscriber methods.
 *
 * author: bobo
 * create time: 2016/12/15 11:02
 * Email: jqbo84@163.com
 */
public interface Finder {

    Map<EventType, PublisherEvent> findAllProducers(Object listener);

    Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener);


    Finder ANNOTATED = new Finder() {
        @Override
        public Map<EventType, PublisherEvent> findAllProducers(Object listener) {
            return AnnotatedFinder.findAllProducers(listener);
        }

        @Override
        public Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener) {
            return AnnotatedFinder.findAllSubscribers(listener);
        }
    };
}
