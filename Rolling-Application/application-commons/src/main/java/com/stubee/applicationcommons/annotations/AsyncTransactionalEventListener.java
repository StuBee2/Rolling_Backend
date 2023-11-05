package com.stubee.applicationcommons.annotations;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.lang.annotation.*;

@Async
@Transactional(propagation = Propagation.NEVER)
@TransactionalEventListener
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AsyncTransactionalEventListener {
}