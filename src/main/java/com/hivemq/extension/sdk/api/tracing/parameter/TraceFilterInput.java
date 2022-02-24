package com.hivemq.extension.sdk.api.tracing.parameter;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.packets.general.Qos;
import com.hivemq.extension.sdk.api.parameter.ClientBasedInput;

@DoNotImplement
public interface TraceFilterInput extends ClientBasedInput {

    /**
     * The quality of service level for this trace.
     *
     * @return The qos.
     */
    @NotNull Qos getQos();

    /**
     * The topic for this trace.
     *
     * @return The topic.
     */
    @NotNull String getTopic();
}
