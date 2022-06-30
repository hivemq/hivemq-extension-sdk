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

package com.hivemq.extension.sdk.api.client.parameter;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.packets.general.MqttVersion;

import java.net.InetAddress;
import java.util.Optional;

/**
 * The connection information contains specific data for the established connection of a client.
 *
 * @author Christoph Schäbel
 * @since 4.0.0, CE 2019.1
 */
@DoNotImplement
public interface ConnectionInformation {

    /**
     * The MQTT version of the client.
     *
     * @return The {@link MqttVersion} of the client. V_3_1, V_3_1_1 or V_5.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull MqttVersion getMqttVersion();

    /**
     * The client's IP address.
     *
     * @return An {@link Optional} of the {@link InetAddress} of the client.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<InetAddress> getInetAddress();

    /**
     * The listener of HiveMQ the client is connected to.
     *
     * @return An {@link Optional} of the {@link Listener} of the client.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<Listener> getListener();

    /**
     * The proxy protocol information for this connection. Only available if the proxy protocol is enabled.
     *
     * @return An {@link Optional} of the {@link ProxyInformation} of the client.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull Optional<ProxyInformation> getProxyInformation();

    /**
     * A store where client specific information can be stored for the duration of the connection.
     *
     * @return The {@link ConnectionAttributeStore} of the client.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull ConnectionAttributeStore getConnectionAttributeStore();

    /**
     * Information about TLS, should the client be connected to HiveMQ via an TLS listener. This information is only
     * present if the client connected with mutual TLS.
     *
     * @return An {@link Optional} of the {@link TlsInformation} of the client.
     * @since 4.0.0, CE 2019.1
     * @deprecated Use {@link #getClientTlsInformation()} instead.
     */
    @Deprecated
    @NotNull Optional<TlsInformation> getTlsInformation();

    /**
     * Information about TLS, should the client be connected to HiveMQ via an TLS listener. This information is only
     * present if the client connected with mutual TLS.
     *
     * @return An {@link Optional} of the {@link ClientTlsInformation} of the client.
     * @since 4.6.0, CE 2021.1
     */
    @NotNull Optional<ClientTlsInformation> getClientTlsInformation();
}
