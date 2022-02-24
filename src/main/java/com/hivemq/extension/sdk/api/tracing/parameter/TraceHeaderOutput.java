package com.hivemq.extension.sdk.api.tracing.parameter;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;

@DoNotImplement
public interface TraceHeaderOutput {

    void setTraceParent(@NotNull String traceParent);

    void setTraceState(@NotNull String traceState);
}
