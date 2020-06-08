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

package com.hivemq.extension.sdk.api.client;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.interceptor.Interceptor;
import com.hivemq.extension.sdk.api.interceptor.disconnect.DisconnectInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.disconnect.DisconnectOutboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.pingreq.PingReqInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.pingresp.PingRespOutboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.puback.PubackInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.puback.PubackOutboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.pubcomp.PubcompInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.pubcomp.PubcompOutboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.publish.PublishInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.publish.PublishOutboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.pubrec.PubrecInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.pubrec.PubrecOutboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.pubrel.PubrelInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.pubrel.PubrelOutboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.suback.SubackOutboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.subscribe.SubscribeInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.unsuback.UnsubackOutboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.unsubscribe.UnsubscribeInboundInterceptor;
import com.hivemq.extension.sdk.api.packets.auth.ModifiableDefaultPermissions;

import java.util.List;

/**
 * The client context is used to set up all interceptors for a client.
 * <p>
 * The client context is only valid until the initialize methods have returned.
 *
 * @author Christoph Schäbel
 * @author Florian Limpöck
 * @since 4.0.0, CE 2019.1
 */
@DoNotImplement
public interface ClientContext {

    /**
     * Adds an {@link PublishInboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param publishInboundInterceptor The implementation of an PublishInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.0.0, CE 2019.1
     */
    void addPublishInboundInterceptor(@NotNull PublishInboundInterceptor publishInboundInterceptor);

    /**
     * Adds an {@link PublishOutboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param publishOutboundInterceptor The implementation of an PublishOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.2.0, CE 2020.1
     */
    void addPublishOutboundInterceptor(@NotNull PublishOutboundInterceptor publishOutboundInterceptor);

    /**
     * Adds an {@link PubackInboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pubackInboundInterceptor The implementation of an PubackInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPubackInboundInterceptor(@NotNull PubackInboundInterceptor pubackInboundInterceptor);

    /**
     * Adds an {@link PubackOutboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pubackOutboundInterceptor The implementation of an PubackOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPubackOutboundInterceptor(@NotNull PubackOutboundInterceptor pubackOutboundInterceptor);

    /**
     * Adds an {@link PubrecInboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pubrecInboundInterceptor The implementation of an PubrecInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPubrecInboundInterceptor(@NotNull PubrecInboundInterceptor pubrecInboundInterceptor);

    /**
     * Adds an {@link PubrecOutboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pubrecOutboundInterceptor The implementation of an PubrecOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPubrecOutboundInterceptor(@NotNull PubrecOutboundInterceptor pubrecOutboundInterceptor);

    /**
     * Adds an {@link PubrelInboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pubrelInboundInterceptor The implementation of an PubrelInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPubrelInboundInterceptor(@NotNull PubrelInboundInterceptor pubrelInboundInterceptor);

    /**
     * Adds an {@link PubrelOutboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pubrelOutboundInterceptor The implementation of an PubrelOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPubrelOutboundInterceptor(@NotNull PubrelOutboundInterceptor pubrelOutboundInterceptor);

    /**
     * Adds an {@link PubcompInboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pubcompInboundInterceptor The implementation of an PubcompInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPubcompInboundInterceptor(@NotNull PubcompInboundInterceptor pubcompInboundInterceptor);

    /**
     * Adds an {@link PubcompOutboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pubcompOutboundInterceptor The implementation of an PubcompOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPubcompOutboundInterceptor(@NotNull PubcompOutboundInterceptor pubcompOutboundInterceptor);

    /**
     * Adds an {@link SubscribeInboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param subscribeInboundInterceptor The implementation of an SubscribeInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.2.0, CE 2020.1
     */
    void addSubscribeInboundInterceptor(@NotNull SubscribeInboundInterceptor subscribeInboundInterceptor);

    /**
     * Adds a {@link SubackOutboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param subAckOutboundInterceptor The implementation of a SubackOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addSubackOutboundInterceptor(@NotNull SubackOutboundInterceptor subAckOutboundInterceptor);

    /**
     * Adds an {@link UnsubscribeInboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param unsubscribeInboundInterceptor The implementation of an UnsubscribeInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addUnsubscribeInboundInterceptor(@NotNull UnsubscribeInboundInterceptor unsubscribeInboundInterceptor);

    /**
     * Adds an {@link UnsubackOutboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param unsubackOutboundInterceptor The implementation of an UnsubackOutboundInterceptors.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addUnsubackOutboundInterceptor(@NotNull UnsubackOutboundInterceptor unsubackOutboundInterceptor);

    /**
     * Adds an {@link DisconnectInboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param disconnectInboundInterceptor The implementation of a DisconnectInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addDisconnectInboundInterceptor(@NotNull DisconnectInboundInterceptor disconnectInboundInterceptor);

    /**
     * Adds an {@link DisconnectOutboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param disconnectOutboundInterceptor The implementation of a DisconnectOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addDisconnectOutboundInterceptor(@NotNull DisconnectOutboundInterceptor disconnectOutboundInterceptor);

    /**
     * Adds an {@link PingReqInboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pingReqInboundInterceptor The implementation of a PingReqInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPingReqInboundInterceptor(@NotNull PingReqInboundInterceptor pingReqInboundInterceptor);

    /**
     * Adds an {@link PingRespOutboundInterceptor} for this client.
     * <p>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param pingRespOutboundInterceptor The implementation of a PingRespOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void addPingRespOutboundInterceptor(@NotNull PingRespOutboundInterceptor pingRespOutboundInterceptor);

    /**
     * Removes an {@link PublishInboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param publishInboundInterceptor The implementation of an PublishInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.0.0, CE 2019.1
     */
    void removePublishInboundInterceptor(@NotNull PublishInboundInterceptor publishInboundInterceptor);

    /**
     * Removes an {@link PublishOutboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param publishOutboundInterceptor The implementation of an PublishOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.2.0, CE 2020.1
     */
    void removePublishOutboundInterceptor(@NotNull PublishOutboundInterceptor publishOutboundInterceptor);

    /**
     * Removes an {@link PubackInboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pubackInboundInterceptor The implementation of an PubackInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePubackInboundInterceptor(@NotNull PubackInboundInterceptor pubackInboundInterceptor);

    /**
     * Removes an {@link PubackOutboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pubackOutboundInterceptor The implementation of an PubackOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePubackOutboundInterceptor(@NotNull PubackOutboundInterceptor pubackOutboundInterceptor);

    /**
     * Removes an {@link PubrecInboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pubrecInboundInterceptor The implementation of an PubrecInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePubrecInboundInterceptor(@NotNull PubrecInboundInterceptor pubrecInboundInterceptor);

    /**
     * Removes an {@link PubrecOutboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pubrecOutboundInterceptor The implementation of an PubrecOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePubrecOutboundInterceptor(@NotNull PubrecOutboundInterceptor pubrecOutboundInterceptor);

    /**
     * Removes an {@link PubrelInboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pubrelInboundInterceptor The implementation of an PubrelInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePubrelInboundInterceptor(@NotNull PubrelInboundInterceptor pubrelInboundInterceptor);

    /**
     * Removes an {@link PubrelOutboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pubrelOutboundInterceptor The implementation of an PubrelOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePubrelOutboundInterceptor(@NotNull PubrelOutboundInterceptor pubrelOutboundInterceptor);

    /**
     * Removes an {@link PubcompInboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pubcompInboundInterceptor The implementation of an PubcompInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePubcompInboundInterceptor(@NotNull PubcompInboundInterceptor pubcompInboundInterceptor);

    /**
     * Removes an {@link PubcompOutboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pubcompOutboundInterceptor The implementation of an PubcompOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePubcompOutboundInterceptor(@NotNull PubcompOutboundInterceptor pubcompOutboundInterceptor);

    /**
     * Removes an {@link SubscribeInboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param subscribeInboundInterceptor The implementation of an SubscribeInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.2.0, CE 2020.1
     */
    void removeSubscribeInboundInterceptor(@NotNull SubscribeInboundInterceptor subscribeInboundInterceptor);

    /**
     * Removes a {@link SubackOutboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param subackOutboundInterceptor The implementation of a SubackOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removeSubackOutboundInterceptor(@NotNull SubackOutboundInterceptor subackOutboundInterceptor);

    /**
     * Removes an {@link UnsubscribeInboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param unsubscribeInboundInterceptor The implementation of an UnsubscribeInboundInterceptor.
     * @throws NullPointerException If the interceptors is null.
     * @since 4.3.0, CE 2020.1
     */
    void removeUnsubscribeInboundInterceptor(@NotNull UnsubscribeInboundInterceptor unsubscribeInboundInterceptor);

    /**
     * Removes an {@link UnsubackOutboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param unsubackOutboundInterceptor The implementation of an UnsubackOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removeUnsubackOutboundInterceptor(@NotNull UnsubackOutboundInterceptor unsubackOutboundInterceptor);

    /**
     * Removes an {@link DisconnectInboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param disconnectInboundInterceptor The implementation of an DisconnectInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removeDisconnectInboundInterceptor(@NotNull DisconnectInboundInterceptor disconnectInboundInterceptor);

    /**
     * Removes an {@link DisconnectOutboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param disconnectOutboundInterceptor The implementation of an DisconnectOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removeDisconnectOutboundInterceptor(@NotNull DisconnectOutboundInterceptor disconnectOutboundInterceptor);

    /**
     * Removes a {@link PingReqInboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pingReqInboundInterceptor The implementation of a PingReqInboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePingReqInboundInterceptor(@NotNull PingReqInboundInterceptor pingReqInboundInterceptor);

    /**
     * Removes a {@link PingRespOutboundInterceptor} for this client.
     * <p>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param pingRespOutboundInterceptor The implementation of a PingRespOutboundInterceptor.
     * @throws NullPointerException If the interceptor is null.
     * @since 4.3.0, CE 2020.1
     */
    void removePingRespOutboundInterceptor(@NotNull PingRespOutboundInterceptor pingRespOutboundInterceptor);

    /**
     * Returns all {@link Interceptor} which are registered for this client.
     *
     * @return List of Interceptors for this client.
     * @since 4.0.0, CE 2019.1
     */
    @Immutable @NotNull List<@NotNull Interceptor> getAllInterceptors();

    /**
     * Returns all {@link PublishInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of PublishInboundInterceptors for this client.
     * @since 4.0.0, CE 2019.1
     */
    @Immutable @NotNull List<@NotNull PublishInboundInterceptor> getPublishInboundInterceptors();

    /**
     * Returns all {@link PublishOutboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of PublishOutboundInterceptors for this client.
     * @since 4.2.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PublishOutboundInterceptor> getPublishOutboundInterceptors();

    /**
     * Returns all {@link PubackInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PubackInboundInterceptor} for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PubackInboundInterceptor> getPubackInboundInterceptors();

    /**
     * Returns all {@link PubackOutboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PubackOutboundInterceptor} for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PubackOutboundInterceptor> getPubackOutboundInterceptors();

    /**
     * Returns all {@link PubrecInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PubrecInboundInterceptor} for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PubrecInboundInterceptor> getPubrecInboundInterceptors();

    /**
     * Returns all {@link PubrecOutboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PubrecOutboundInterceptor} for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PubrecOutboundInterceptor> getPubrecOutboundInterceptors();

    /**
     * Returns all {@link PubrelInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PubrelInboundInterceptor} for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PubrelInboundInterceptor> getPubrelInboundInterceptors();

    /**
     * Returns all {@link PubrelOutboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PubrelOutboundInterceptor} for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PubrelOutboundInterceptor> getPubrelOutboundInterceptors();

    /**
     * Returns all {@link PubcompInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PubcompInboundInterceptor} for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PubcompInboundInterceptor> getPubcompInboundInterceptors();

    /**
     * Returns all {@link PubcompOutboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PubcompOutboundInterceptor} for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PubcompOutboundInterceptor> getPubcompOutboundInterceptors();

    /**
     * Returns all {@link SubscribeInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of SubscribeInboundInterceptors for this client.
     * @since 4.2.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull SubscribeInboundInterceptor> getSubscribeInboundInterceptors();

    /**
     * Returns all {@link SubackOutboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of SubackOutboundInterceptor for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull SubackOutboundInterceptor> getSubackOutboundInterceptors();

    /**
     * Returns all {@link UnsubscribeInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of UnsubscribeInboundInterceptors for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull UnsubscribeInboundInterceptor> getUnsubscribeInboundInterceptors();

    /**
     * Returns all {@link UnsubackOutboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of UnsubackOutboundInterceptors for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull UnsubackOutboundInterceptor> getUnsubackOutboundInterceptors();

    /**
     * Returns all {@link DisconnectInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of DisconnectInboundInterceptors for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull DisconnectInboundInterceptor> getDisconnectInboundInterceptors();

    /**
     * Returns all {@link DisconnectOutboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of DisconnectOutboundInterceptors for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull DisconnectOutboundInterceptor> getDisconnectOutboundInterceptors();

    /**
     * Returns all {@link PingReqInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PingReqInboundInterceptor}s for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PingReqInboundInterceptor> getPingReqInboundInterceptors();

    /**
     * Returns all {@link PingRespOutboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of {@link PingRespOutboundInterceptor}s for this client.
     * @since 4.3.0, CE 2020.1
     */
    @Immutable @NotNull List<@NotNull PingRespOutboundInterceptor> getPingRespOutboundInterceptors();

    /**
     * The default permissions for this client. Default permissions are automatically applied by HiveMQ for every MQTT
     * PUBLISH and SUBSCRIBE packet sent by this client.
     *
     * @return The {@link ModifiableDefaultPermissions}.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull ModifiableDefaultPermissions getDefaultPermissions();
}
