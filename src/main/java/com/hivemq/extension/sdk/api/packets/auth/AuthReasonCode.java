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

package com.hivemq.extension.sdk.api.packets.auth;

/**
 * @author Florian Limpöck
 * @since 4.3.0, CE 2020.1
 */
public enum AuthReasonCode {

    /**
     * SUCCESS reason code is sent when client authentication completed successfully.
     *
     * @since 4.3.0, CE 2020.1
     */
    SUCCESS,

    /**
     * CONTINUE_AUTHENTICATION reason code is sent when client authentication needs further authentication.
     *
     * @since 4.3.0, CE 2020.1
     */
    CONTINUE_AUTHENTICATION,

    /**
     * REAUTHENTICATE reason code is sent when re-authentication is needed.
     *
     * @since 4.3.0, CE 2020.1
     */
    REAUTHENTICATE
}
