/*
 * Copyright 2018 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.extension.sdk.api.packets.publish;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.packets.general.ModifiableUserProperties;
import com.hivemq.extension.sdk.api.packets.general.Qos;
import com.hivemq.extension.sdk.api.packets.general.UserProperties;

import java.nio.ByteBuffer;

/**
 * A copy of an {@link PublishPacket} that can be modified for onward delivery.
 *
 * @author Christoph Schäbel
 * @author Florian Limpöck
 * @since 4.0.0
 */
@DoNotImplement
public interface ModifiablePublishPacket extends PublishPacket {

    /**
     * This does not change the QoS to the publisher.
     * Only for onward delivery.
     *
     * @param qos The QoS for the onward publish to subscribers.
     * @throws NullPointerException     If qos is null.
     * @throws IllegalArgumentException If qos is greater than the configured maximum.
     * @since 4.0.0
     */
    void setQos(@NotNull Qos qos);

    /**
     * Sets the retain flag.
     *
     * @param retain The new retain flag for the publish.
     * @throws IllegalArgumentException If set to true and retained messages are disabled by HiveMQ.
     * @since 4.0.0
     */
    void setRetain(boolean retain);

    /**
     * Sets the topic.
     *
     * @param topic The new topic for the publish.
     * @throws NullPointerException     If topic is null.
     * @throws IllegalArgumentException If topic is an empty string or an invalid UTF-8 string.
     * @since 4.0.0
     */
    void setTopic(@NotNull String topic);

    /**
     * Sets the payload format indicator.
     *
     * @param payloadFormatIndicator The new payload format indicator for the publish.
     * @throws NullPointerException If payload format indicator is null.
     * @since 4.0.0
     */
    void setPayloadFormatIndicator(@NotNull PayloadFormatIndicator payloadFormatIndicator);

    /**
     * Sets the message expiry interval.
     *
     * @param messageExpiryInterval The new message expiry interval for the publish.
     * @throws IllegalArgumentException If the message expiry interval is less than zero or more than the configured
     *                                  maximum by HiveMQ.
     * @since 4.0.0
     */
    void setMessageExpiryInterval(long messageExpiryInterval);

    /**
     * Sets the response topic.
     *
     * @param responseTopic The new response topic for the publish.
     * @throws NullPointerException If response topic is null.
     * @since 4.0.0
     */
    void setResponseTopic(@NotNull String responseTopic);

    /**
     * Sets the correlation data.
     *
     * @param correlationData The new correlation data for the publish.
     * @throws NullPointerException If correlation data is null.
     * @since 4.0.0
     */
    void setCorrelationData(@NotNull ByteBuffer correlationData);

    /**
     * Sets the content type.
     *
     * @param contentType The new content type for the publish.
     * @throws NullPointerException If content type is null.
     * @since 4.0.0
     */
    void setContentType(@NotNull String contentType);

    /**
     * Sets the payload.
     *
     * @param payload The new payload for the publish.
     * @throws NullPointerException If payload is null.
     * @since 4.0.0
     */
    void setPayload(@NotNull ByteBuffer payload);

    /**
     * Get the modifiable {@link UserProperties} of the PUBLISH packet.
     *
     * @return Modifiable user properties.
     * @since 4.0.0
     */
    @NotNull
    ModifiableUserProperties getUserProperties();

}
