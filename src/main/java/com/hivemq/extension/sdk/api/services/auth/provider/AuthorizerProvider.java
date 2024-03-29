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

package com.hivemq.extension.sdk.api.services.auth.provider;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.Nullable;
import com.hivemq.extension.sdk.api.auth.Authorizer;
import com.hivemq.extension.sdk.api.auth.PublishAuthorizer;
import com.hivemq.extension.sdk.api.auth.SubscriptionAuthorizer;
import com.hivemq.extension.sdk.api.auth.parameter.AuthorizerProviderInput;

/**
 * The authorizer provider allows to implement custom logic for the authorization for specific actions (i.e subscribing,
 * publishing etc.) of MQTT clients. For each client an {@link Authorizer} for the specific action can be provided that
 * contains the authorization logic.
 *
 * @author Christoph Schäbel
 * @since 4.0.0, CE 2019.1
 */
@FunctionalInterface
public interface AuthorizerProvider {

    /**
     * This method is called for each client by HiveMQ.
     * <p>
     * Either the same {@link Authorizer} (stateless or must be thread-safe) or a new one (stateful, must not be
     * thread-safe) can be supplied on each call.
     * <p>
     * <code>null</code> can be returned if no authorization for the client is necessary.
     *
     * @param authorizerProviderInput The {@link AuthorizerProviderInput}.
     * @return Either an implementation of {@link SubscriptionAuthorizer} or an implementation of
     *         {@link PublishAuthorizer}. <code>null</code> is ignored and has the same effect as if this provider would
     *         had not been set for the connecting client. Returning any other implementation of the {@link Authorizer}
     *         interface is an error.
     * @since 4.0.0, CE 2019.1
     */
    @Nullable Authorizer getAuthorizer(@NotNull AuthorizerProviderInput authorizerProviderInput);
}
