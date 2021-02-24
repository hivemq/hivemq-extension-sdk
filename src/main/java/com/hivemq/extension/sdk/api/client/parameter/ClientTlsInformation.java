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

import java.security.cert.X509Certificate;
import java.util.Optional;

/**
 * The TLS information contains specific data about the TLS connection, should the client use TLS.
 *
 * @author Christoph Schäbel
 * @since 4.6.0, CE 2021.1
 */
@DoNotImplement
public interface ClientTlsInformation {

    /**
     * @return The certificate presented by the client.
     * @since 4.6.0, CE 2021.1
     */
    @NotNull Optional<X509Certificate> getClientCertificate();

    /**
     * @return The certificate chain presented by the client.
     * @since 4.6.0, CE 2021.1
     */
    @NotNull Optional<X509Certificate[]> getClientCertificateChain();

    /**
     * @return The cipher suite that is used for this client.
     * @since 4.6.0, CE 2021.1
     */
    @NotNull String getCipherSuite();

    /**
     * @return The protocol that is used for this client.
     * @since 4.6.0, CE 2021.1
     */
    @NotNull String getProtocol();

    /**
     * @return The hostname as sent by the client via SNI.
     * @since 4.6.0, CE 2021.1
     */
    @NotNull Optional<String> getHostname();
}
