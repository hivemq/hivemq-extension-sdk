package com.hivemq.extension.sdk.api.tracing;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;

@DoNotImplement
public interface TraceContext<T, C> {

    @NotNull String W3C_TRACEPARENT = "traceparent";
    @NotNull String W3C_TRACESTATE = "tracestate";

    @NotNull T unwrapTracer(final @NotNull Class<? extends T> tracerClass) throws IllegalStateException;

    @NotNull C unwrapContext(final @NotNull Class<? extends C> contextClass) throws IllegalStateException;
}
