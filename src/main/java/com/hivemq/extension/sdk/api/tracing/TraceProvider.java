package com.hivemq.extension.sdk.api.tracing;

import com.hivemq.extension.sdk.api.tracing.parameter.TraceFilterInput;
import com.hivemq.extension.sdk.api.tracing.parameter.TraceHeaderInput;
import com.hivemq.extension.sdk.api.tracing.parameter.TraceHeaderOutput;
import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.Nullable;

@DoNotImplement
public interface TraceProvider<T, C> {

    @Nullable TraceContext<T, C> getTraceContext(final @Nullable TraceHeaderInput traceHeaderInput, final @Nullable TraceFilterInput traceFilterInput);

    void setTraceHeader(final @NotNull C context, final @NotNull TraceHeaderOutput traceHeaderOutput);
}
