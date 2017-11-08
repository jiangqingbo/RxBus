package com.huyunit.rxbus2.event;

import com.huyunit.rxbus2.thread.EventThread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Wraps a 'publisher' method on a specific object.
 * This class only verifies the suitability of the method and event type if something fails.  Callers are expected
 * to verify their uses of this class.
 * <p>
 * author: bobo
 * create time: 2016/12/15 10:54
 * Email: jqbo84@163.com
 */
public class PublisherEvent extends Event {
    /**
     * Object sporting the publisher method.
     */
    private final Object target;
    /**
     * Publisher method.
     */
    private final Method method;
    /**
     * Publisher thread
     */
    private final EventThread thread;
    /**
     * Object hash code.
     */
    private final int hashCode;
    /**
     * Should this producer publish events
     */
    private boolean valid = true;

    public PublisherEvent(Object target, Method method, EventThread thread) {
        if (target == null) {
            throw new NullPointerException("EventPublisher target cannot be null.");
        }
        if (method == null) {
            throw new NullPointerException("EventPublisher method cannot be null.");
        }

        this.target = target;
        this.thread = thread;
        this.method = method;
        method.setAccessible(true);

        // Compute hash code eagerly since we know it will be used frequently and we cannot estimate the runtime of the
        // target's hashCode call.
        final int prime = 31;
        hashCode = (prime + method.hashCode()) * prime + target.hashCode();
    }

    public boolean isValid() {
        return valid;
    }

    /**
     * If invalidated, will subsequently refuse to publish events.
     * <p/>
     * Should be called when the wrapped object is unregistered from the Bus.
     */
    public void invalidate() {
        valid = false;
    }

    /**
     * Invokes the wrapped publisher method and publish a {@link Observable}.
     */
    public Observable publish() {
        return Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> subscriber) throws Exception {
                try {
                    subscriber.onNext(publishEvent());
                    subscriber.onComplete();
                } catch (InvocationTargetException e) {
                    throwRuntimeException("Publisher " + PublisherEvent.this + " threw an exception.", e);
                }
            }
        }).subscribeOn(EventThread.getScheduler(thread));
    }

    /**
     * Invokes the wrapped publisher method.
     *
     * @throws IllegalStateException     if previously invalidated.
     * @throws InvocationTargetException if the wrapped method throws any {@link Throwable} that is not
     *                                   an {@link Error} ({@code Error}s are propagated as-is).
     */
    private Object publishEvent() throws InvocationTargetException {
        if (!valid) {
            throw new IllegalStateException(toString() + " has been invalidated and can no longer publisher events.");
        }
        try {
            return method.invoke(target);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof Error) {
                throw (Error) e.getCause();
            }
            throw e;
        }
    }

    @Override
    public String toString() {
        return "[EventPublisher " + method + "]";
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PublisherEvent other = (PublisherEvent) obj;
        return method.equals(other.method) && target == other.target;
    }

    public Object getTarget() {
        return target;
    }
}
