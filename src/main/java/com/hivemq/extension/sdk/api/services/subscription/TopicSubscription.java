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

package com.hivemq.extension.sdk.api.services.subscription;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.packets.general.Qos;
import com.hivemq.extension.sdk.api.packets.subscribe.RetainHandling;
import com.hivemq.extension.sdk.api.services.builder.Builders;
import com.hivemq.extension.sdk.api.services.builder.TopicSubscriptionBuilder;

import java.util.Optional;

/**
 * Represents a Subscription.
 * <p>
 * Contains all values (except {@link RetainHandling}) of an MQTT 5 Subscription, but is also used to represent MQTT 3
 * Subscriptions.
 * <p>
 *
 * @author Christoph Schäbel
 * @since 4.0.0, CE 2019.1
 */
@DoNotImplement
public interface TopicSubscription {

    /**
     * @return A new {@link TopicSubscriptionBuilder} to create a topic subscription.
     * @since 4.0.0, CE 2019.1
     * @deprecated Use {@link Builders#topicSubscription()} instead
     */
    static TopicSubscriptionBuilder builder() {
        return Builders.topicSubscription();
    }

    /**
     * The topic filter of the subscription.
     *
     * @return The topic filter of the subscription.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull String getTopicFilter();

    /**
     * The quality of service level of the subscription.
     *
     * @return The quality of service level of the subscription.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Qos getQos();

    /**
     * The retain as published flag indicates if the client wants the retain flag preserved for received messages to the
     * topic filter of the subscription.
     * <p>
     * If <code>true</code> the retain flag is preserved. If <code>false</code> it isn't.
     *
     * @return The retain as published flag of the subscription.
     * @since 4.0.0, CE 2019.1
     */
    boolean getRetainAsPublished();

    /**
     * The no local flag indicates if the client wants to receive messages published by itself to the topic filter of
     * the subscription.
     * <p>
     * If <code>false</code> the client also receives it's own messages. If <code>true</code> it doesn't.
     *
     * @return The no local flag of the subscription.
     * @since 4.0.0, CE 2019.1
     */
    boolean getNoLocal();

    /**
     * The current subscription identifier that is associated with the subscription.
     *
     * @return An {@link Optional} containing the subscription identifier if present.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<Integer> getSubscriptionIdentifier();
}
