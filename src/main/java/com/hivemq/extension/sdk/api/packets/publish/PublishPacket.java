/*
 * Copyright 2018-present HiveMQ GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.extension.sdk.api.packets.publish;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.packets.general.Qos;
import com.hivemq.extension.sdk.api.packets.general.UserProperties;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;

/**
 * Represents a PUBLISH packet.
 * <p>
 * Contains all values of an MQTT 5 PUBLISH, but will also used to represent MQTT 3 publishes.
 *
 * @author Christoph Schäbel
 * @author Florian Limpöck
 * @since 4.0.0, CE 2019.1
 */
@Immutable
@DoNotImplement
public interface PublishPacket {

    /**
     * If <code>false</code> this is the first occasion the message is sent to the receiver. If <code>true</code> the
     * message has already been sent once to the receiver.
     *
     * @return The duplicate delivery flag.
     * @since 4.0.0, CE 2019.1
     */
    boolean getDupFlag();

    /**
     * The quality of service level of the publish.
     *
     * @return The qos.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Qos getQos();

    /**
     * If <code>true</code> this message is a retained message, for <code>false</code> this is just a normal publish.
     *
     * @return The retain flag.
     * @since 4.0.0, CE 2019.1
     */
    boolean getRetain();

    /**
     * The topic filter the message is published to.
     *
     * @return The topic.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull String getTopic();

    /**
     * The packet identifier of the publish.
     *
     * @return The packet identifier.
     * @since 4.0.0, CE 2019.1
     */
    int getPacketId();

    /**
     * If this property is present, this is the payload format indicator.
     * <p>
     * For an MQTT 3 PUBLISH this MQTT 5 property will always be empty.
     *
     * @return An {@link Optional} that contains the payload format indicator if present.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<PayloadFormatIndicator> getPayloadFormatIndicator();

    /**
     * If this property is present, this is the message expiry interval.
     * <p>
     * For an MQTT 3 PUBLISH this MQTT 5 property will always have the value from the configured message expiry.
     *
     * @return An {@link Optional} that contains the message expiry interval if present.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<Long> getMessageExpiryInterval();

    /**
     * If this property is present, this is the response topic.
     * <p>
     * For an MQTT 3 PUBLISH this MQTT 5 property will always be empty.
     *
     * @return An {@link Optional} that contains the response topic if present.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<String> getResponseTopic();

    /**
     * If this property is present, this is the correlation data.
     * <p>
     * For an MQTT 3 PUBLISH this MQTT 5 property will always be empty.
     * <p>
     * The ByteBuffer returned by this method is {@link ByteBuffer#asReadOnlyBuffer() read only} and will throw a
     * {@link java.nio.ReadOnlyBufferException ReadOnlyBufferException} if handled incorrectly.
     *
     * @return An {@link Optional} that contains the correlation data if present.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<@Immutable ByteBuffer> getCorrelationData();

    /**
     * The list of subscription identifiers for PUBLISH.
     * <p>
     * For an MQTT 3 PUBLISH this MQTT 5 property will always be an empty list.
     *
     * @return The subscription identifiers.
     * @since 4.0.0, CE 2019.1
     */
    @Immutable @NotNull List<@NotNull Integer> getSubscriptionIdentifiers();

    /**
     * If this property is present, this is the content type.
     * <p>
     * For an MQTT 3 PUBLISH this MQTT 5 property will always be empty.
     *
     * @return An {@link Optional} that contains the content type if present.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<String> getContentType();

    /**
     * If this property is present, this is the payload.
     * <p>
     * The ByteBuffer returned by this method is {@link ByteBuffer#asReadOnlyBuffer() read only} and will throw a
     * {@link java.nio.ReadOnlyBufferException ReadOnlyBufferException} if handled incorrectly.
     *
     * @return An {@link Optional} that contains the payload if present.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<@Immutable ByteBuffer> getPayload();

    /**
     * The {@link UserProperties} of the PUBLISH.
     * <p>
     * For an MQTT 3 PUBLISH this MQTT 5 property will always be an empty list.
     *
     * @return The user properties.
     * @since 4.0.0, CE 2019.1
     */
    @Immutable @NotNull UserProperties getUserProperties();

    /**
     * The arrival timestamp of the PUBLISH.
     * <p>
     * For an incoming PUBLISH message the timestamp represents the moment when the message arrived at the broker.
     * <p>
     * For a message created via the Extension SDK the timestamp represents the moment when the message is passed to the
     * PublishService.
     * <p>
     * For a Will Publish that is sent to subscribers, the timestamp represents the moment when the delivery started
     * (after the client disconnected ungracefully and the Will delay has passed).
     * <p>
     * The returned timestamp is a UNIX timestamp since midnight, January 1, 1970 UTC in milliseconds.
     *
     * @return The timestamp.
     * @since 4.4.0, CE 2020.4
     */
    long getTimestamp();
}
