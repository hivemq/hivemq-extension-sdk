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

package com.hivemq.extension.sdk.api.auth.parameter;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.async.AsyncOutput;
import com.hivemq.extension.sdk.api.auth.SubscriptionAuthorizer;
import com.hivemq.extension.sdk.api.packets.auth.ModifiableDefaultPermissions;
import com.hivemq.extension.sdk.api.packets.disconnect.DisconnectReasonCode;
import com.hivemq.extension.sdk.api.packets.subscribe.SubackReasonCode;

/**
 * This is the output parameter of any {@link SubscriptionAuthorizer} providing methods to define the outcome of the
 * subscription authorization.
 * <p>
 * It can be used to
 * <ul>
 *   <li>Authorize a subscription successfully
 *   <li>Let the authorization of the subscription fail
 *   <li>Disconnect the sender of the subscription
 *   <li>Delegate the decision to the next extension
 * </ul>
 * <p>
 * Exactly one of the decisive methods must be called:
 * <ul>
 *   <li>{@link #authorizeSuccessfully()}
 *   <li>{@link #failAuthorization()}
 *   <li>{@link #failAuthorization(SubackReasonCode)}
 *   <li>{@link #failAuthorization(SubackReasonCode, String)}
 *   <li>{@link #disconnectClient()}
 *   <li>{@link #disconnectClient(DisconnectReasonCode)}
 *   <li>{@link #disconnectClient(DisconnectReasonCode, String)}
 *   <li>{@link #nextExtensionOrDefault()}
 * </ul>
 * Subsequent calls will fail with an {@link UnsupportedOperationException}.
 *
 * @author Christoph Schäbel
 * @since 4.0.0, CE 2019.1
 */
@DoNotImplement
public interface SubscriptionAuthorizerOutput extends AsyncOutput<SubscriptionAuthorizerOutput> {

    /**
     * Successfully authorizes the subscription.
     * <p>
     * This is a final decision, other extensions or default permissions are ignored.
     *
     * @throws UnsupportedOperationException When authorizeSuccessfully, failAuthorization, disconnectClient or
     *                                       nextExtensionOrDefault has already been called.
     * @since 4.0.0, CE 2019.1
     */
    void authorizeSuccessfully();

    /**
     * Fails the authorization of the subscription. The outcome depends on the MQTT version specified by the subscribing
     * client.
     * <ul>
     *   <li>For an MQTT 3.1 client the connection is closed.
     *   <li>For an MQTT 3.1.1 client the return code for the subscription in the SUBACK packet is 'Failure'.
     *   <li>For an MQTT 5 client the reason code for the subscription in the SUBACK packet is
     *     {@link SubackReasonCode#NOT_AUTHORIZED NOT_AUTHORIZED}.
     * </ul>
     * <p>
     * This is a final decision, other extensions or default permissions are ignored.
     *
     * @throws UnsupportedOperationException When authorizeSuccessfully, failAuthorization, disconnectClient or
     *                                       nextExtensionOrDefault has already been called.
     * @since 4.0.0, CE 2019.1
     */
    void failAuthorization();

    /**
     * Fails the authorization of the subscription. The outcome depends on the MQTT version specified by the subscribing
     * client.
     * <ul>
     *   <li>For an MQTT 3.1 client the connection is closed.
     *   <li>For an MQTT 3.1.1 client the return code for the subscription in the SUBACK packet is 'Failure'.
     *   <li>For an MQTT 5 client the specified reason code is used for the subscription in the SUBACK packet.
     * </ul>
     * <p>
     * This is a final decision, other extensions or default permissions are ignored.
     *
     * @param reasonCode Used as the reason code for the subscription in the SUBACK packet.
     * @throws IllegalArgumentException      If the specified reason code is not an error code.
     * @throws UnsupportedOperationException When authorizeSuccessfully, failAuthorization, disconnectClient or
     *                                       nextExtensionOrDefault has already been called.
     * @since 4.0.0, CE 2019.1
     */
    void failAuthorization(@NotNull SubackReasonCode reasonCode);

    /**
     * Fails the authorization of the subscription. The outcome depends on the MQTT version specified by the subscribing
     * client.
     * <ul>
     *   <li>For an MQTT 3.1 client the connection is closed.
     *   <li>For an MQTT 3.1.1 client the return code for the subscription in the SUBACK packet is 'Failure'.
     *   <li>For an MQTT 5 client the specified reason code is used for the subscription in the SUBACK packet and the
     *     SUBACK packet will contain the specified reason string. If this method is called for more than one
     *     subscription, the reason strings are combined.
     * </ul>
     * <p>
     * This is a final decision, other extensions or default permissions are ignored.
     *
     * @param reasonCode   Used as the reason code for the subscription in the SUBACK packet.
     * @param reasonString Used as the reason string for the SUBACK packet.
     * @throws IllegalArgumentException      If the specified reason code is not an error code.
     * @throws UnsupportedOperationException When authorizeSuccessfully, failAuthorization, disconnectClient or
     *                                       nextExtensionOrDefault has already been called.
     * @since 4.0.0, CE 2019.1
     */
    void failAuthorization(@NotNull SubackReasonCode reasonCode, @NotNull String reasonString);

    /**
     * Disconnects the client that sent the subscription. The outcome depends on the MQTT version specified by the
     * subscribing client.
     * <ul>
     *   <li>For an MQTT 3 client the connection is closed.
     *   <li>An MQTT 5 client receives a DISCONNECT packet with reason code {@link DisconnectReasonCode#NOT_AUTHORIZED
     *     NOT_AUTHORIZED}, then the connection is closed.
     * </ul>
     * <p>
     * All subscriptions from the same SUBSCRIBE packet are ignored, independent of the outcome for the other
     * subscriptions in this packet.
     * <p>
     * This is a final decision, other extensions or default permissions are ignored.
     *
     * @throws UnsupportedOperationException When authorizeSuccessfully, failAuthorization, disconnectClient or
     *                                       nextExtensionOrDefault has already been called.
     * @since 4.0.0, CE 2019.1
     */
    void disconnectClient();

    /**
     * Disconnects the client that sent the subscription. The outcome depends on the MQTT version specified by the
     * subscribing client.
     * <ul>
     *   <li>For an MQTT 3 client the connection is closed.
     *   <li>An MQTT 5 client receives a DISCONNECT packet with the specified reason code, then the connection is
     *     closed.
     * </ul>
     * <p>
     * All subscriptions from the same SUBSCRIBE packet are ignored, independent of the outcome for the other
     * subscriptions in this packet.
     * <p>
     * This is a final decision, other extensions or default permissions are ignored.
     *
     * @param reasonCode Used as the reason code for the DISCONNECT packet.
     * @throws UnsupportedOperationException When authorizeSuccessfully, failAuthorization, disconnectClient or
     *                                       nextExtensionOrDefault has already been called.
     * @since 4.0.0, CE 2019.1
     */
    void disconnectClient(@NotNull DisconnectReasonCode reasonCode);

    /**
     * Disconnects the client that sent the subscription. The outcome depends on the MQTT version specified by the
     * subscribing client.
     * <ul>
     *   <li>For an MQTT 3 client the connection is closed.
     *   <li>An MQTT 5 client receives a DISCONNECT packet with the specified reason code and reason string, then the
     *     connection is closed.
     * </ul>
     * <p>
     * All subscriptions from the same SUBSCRIBE packet are ignored, independent of the outcome for the other
     * subscriptions in this packet.
     * <p>
     * This is a final decision, other extensions or default permissions are ignored.
     *
     * @param reasonCode   Used as the reason code for the DISCONNECT packet.
     * @param reasonString Used as the reason string for the DISCONNECT packet.
     * @throws UnsupportedOperationException When authorizeSuccessfully, failAuthorization, disconnectClient or
     *                                       nextExtensionOrDefault has already been called.
     * @since 4.0.0, CE 2019.1
     */
    void disconnectClient(@NotNull DisconnectReasonCode reasonCode, @NotNull String reasonString);

    /**
     * The outcome of the authorization is determined by the next extension with a {@link SubscriptionAuthorizer}.
     * <p>
     * If no extension with a SubscriptionAuthorizer is left the default permissions (see
     * {@link ModifiableDefaultPermissions}) are used. If no default permissions are set, then the authorization is
     * denied.
     *
     * @throws UnsupportedOperationException When authorizeSuccessfully, failAuthorization, disconnectClient or
     *                                       nextExtensionOrDefault has already been called.
     * @since 4.0.0, CE 2019.1
     */
    void nextExtensionOrDefault();
}
