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

package com.hivemq.extension.sdk.api.packets.general;

/**
 * MQTT 5 CONNACK and DISCONNECT reason codes are listed here.
 *
 * @author Florian Limpöck
 * @since 4.0.0, CE 2019.1
 */
public enum DisconnectedReasonCode {

    /* **************************
     *  CONNACK and DISCONNECT  *
     ************************** */

    /**
     * @since 4.0.0, CE 2019.1
     */
    UNSPECIFIED_ERROR,
    /**
     * @since 4.0.0, CE 2019.1
     */
    MALFORMED_PACKET,
    /**
     * @since 4.0.0, CE 2019.1
     */
    PROTOCOL_ERROR,
    /**
     * @since 4.0.0, CE 2019.1
     */
    IMPLEMENTATION_SPECIFIC_ERROR,
    /**
     * @since 4.0.0, CE 2019.1
     */
    NOT_AUTHORIZED,
    /**
     * @since 4.0.0, CE 2019.1
     */
    SERVER_BUSY,
    /**
     * @since 4.0.0, CE 2019.1
     */
    BAD_AUTHENTICATION_METHOD,
    /**
     * @since 4.0.0, CE 2019.1
     */
    TOPIC_NAME_INVALID,
    /**
     * @since 4.0.0, CE 2019.1
     */
    PACKET_TOO_LARGE,
    /**
     * @since 4.0.0, CE 2019.1
     */
    QUOTA_EXCEEDED,
    /**
     * @since 4.0.0, CE 2019.1
     */
    PAYLOAD_FORMAT_INVALID,
    /**
     * @since 4.0.0, CE 2019.1
     */
    RETAIN_NOT_SUPPORTED,
    /**
     * @since 4.0.0, CE 2019.1
     */
    QOS_NOT_SUPPORTED,
    /**
     * @since 4.0.0, CE 2019.1
     */
    USE_ANOTHER_SERVER,
    /**
     * @since 4.0.0, CE 2019.1
     */
    SERVER_MOVED,
    /**
     * @since 4.0.0, CE 2019.1
     */
    CONNECTION_RATE_EXCEEDED,

    /* ***********
     *  CONNACK  *
     *********** */

    /**
     * @since 4.0.0, CE 2019.1
     * @deprecated since 4.3.0, because SUCCESS is never a reason code for a disconnect.
     */
    @Deprecated SUCCESS,
    /**
     * @since 4.0.0, CE 2019.1
     */
    UNSUPPORTED_PROTOCOL_VERSION,
    /**
     * @since 4.0.0, CE 2019.1
     */
    CLIENT_IDENTIFIER_NOT_VALID,
    /**
     * @since 4.0.0, CE 2019.1
     */
    BAD_USER_NAME_OR_PASSWORD,
    /**
     * @since 4.0.0, CE 2019.1
     */
    SERVER_UNAVAILABLE,
    /**
     * @since 4.0.0, CE 2019.1
     */
    BANNED,

    /* **************
     *  DISCONNECT  *
     ************** */

    /**
     * @since 4.0.0, CE 2019.1
     */
    NORMAL_DISCONNECTION,
    /**
     * @since 4.0.0, CE 2019.1
     */
    DISCONNECT_WITH_WILL_MESSAGE,
    /**
     * @since 4.0.0, CE 2019.1
     */
    SERVER_SHUTTING_DOWN,
    /**
     * @since 4.0.0, CE 2019.1
     */
    KEEP_ALIVE_TIMEOUT,
    /**
     * @since 4.0.0, CE 2019.1
     */
    SESSION_TAKEN_OVER,
    /**
     * @since 4.0.0, CE 2019.1
     */
    TOPIC_FILTER_INVALID,
    /**
     * @since 4.0.0, CE 2019.1
     */
    RECEIVE_MAXIMUM_EXCEEDED,
    /**
     * @since 4.0.0, CE 2019.1
     */
    TOPIC_ALIAS_INVALID,
    /**
     * @since 4.0.0, CE 2019.1
     */
    MESSAGE_RATE_TOO_HIGH,
    /**
     * @since 4.0.0, CE 2019.1
     */
    ADMINISTRATIVE_ACTION,
    /**
     * @since 4.0.0, CE 2019.1
     */
    SHARED_SUBSCRIPTION_NOT_SUPPORTED,
    /**
     * @since 4.0.0, CE 2019.1
     */
    MAXIMUM_CONNECT_TIME,
    /**
     * @since 4.0.0, CE 2019.1
     */
    SUBSCRIPTION_IDENTIFIERS_NOT_SUPPORTED,
    /**
     * @since 4.0.0, CE 2019.1
     */
    WILDCARD_SUBSCRIPTION_NOT_SUPPORTED
}
