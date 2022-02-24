package com.hivemq.extension.sdk.api.tracing.parameter;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Nullable;

@DoNotImplement
public interface TraceHeaderInput {

    @Nullable String getTraceparent();

    @Nullable String getTracestate();
}
