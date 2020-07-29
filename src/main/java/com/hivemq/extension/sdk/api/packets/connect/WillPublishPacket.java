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

package com.hivemq.extension.sdk.api.packets.connect;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.packets.publish.PublishPacket;
import com.hivemq.extension.sdk.api.services.builder.Builders;
import com.hivemq.extension.sdk.api.services.builder.WillPublishBuilder;

/**
 * Contains all information for the Will Publish that is part of a CONNECT packet.
 *
 * @author Christoph Schäbel
 * @since 4.0.0, CE 2019.1
 */
@DoNotImplement
public interface WillPublishPacket extends PublishPacket {

    /**
     * A builder that can be used to create an MQTT 5 {@link WillPublishPacket}.
     *
     * @return A new {@link WillPublishBuilder} to create a Will Publish.
     * @since 4.0.0, CE 2019.1
     */
    static @NotNull WillPublishBuilder builder() {
        return Builders.willPublish();
    }

    /**
     * Delay in seconds before the Will Publish is sent.
     *
     * @return The will delay.
     * @since 4.0.0, CE 2019.1
     */
    long getWillDelay();

    /**
     * The creation timestamp of the Will Publish.
     * <p>
     * For a will publish the timestamp is set to the moment that the CONNECT message arrived at the broker.
     * <p>
     * When the Will Publish is sent to subscribers (after the client disconnects ungracefully and the Will delay has
     * passed) the created PUBLISH packet will carry the timestamp of the delivery start.
     * <p>
     * The returned timestamp is a UNIX timestamp since midnight, January 1, 1970 UTC in milliseconds.
     *
     * @return The timestamp.
     * @since 4.4.0, CE 2020.4
     */
    @Override
    long getTimestamp();
}
