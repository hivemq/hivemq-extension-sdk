/*
 * Copyright 2019 dc-square GmbH
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

package com.hivemq.extension.sdk.api.interceptor.puback.parameter;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.interceptor.puback.PubackOutboundInterceptor;
import com.hivemq.extension.sdk.api.packets.puback.PubackPacket;
import com.hivemq.extension.sdk.api.parameter.ClientBasedInput;

/**
 * This is the input parameter for any {@link PubackOutboundInterceptor} providing PUBACK, connection and client based
 * information.
 *
 * @author Yannick Weber
 * @since 4.3.0
 */
@DoNotImplement
public interface PubackOutboundInput extends ClientBasedInput {

    /**
     * The unmodifiable PUBACK packet that was intercepted.
     *
     * @return An unmodifiable {@link PubackPacket}.
     * @since 4.3.0
     */
    @Immutable @NotNull PubackPacket getPubackPacket();
}
