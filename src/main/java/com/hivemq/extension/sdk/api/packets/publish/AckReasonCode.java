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

package com.hivemq.extension.sdk.api.packets.publish;

/**
 * MQTT 5 Reason codes for PUBACK and PUBREC.
 * <p>
 * MQTT 3 does not support reason codes for the above mentioned MQTT packets.
 *
 * @author Christoph Schäbel
 * @since 4.0.0, CE 2019.1
 */
public enum AckReasonCode {

    /**
     * @since 4.0.0, CE 2019.1
     */
    SUCCESS,
    /**
     * @since 4.0.0, CE 2019.1
     */
    NO_MATCHING_SUBSCRIBERS,
    /**
     * @since 4.0.0, CE 2019.1
     */
    UNSPECIFIED_ERROR,
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
    TOPIC_NAME_INVALID,
    /**
     * @since 4.0.0, CE 2019.1
     */
    PACKET_IDENTIFIER_IN_USE,
    /**
     * @since 4.0.0, CE 2019.1
     */
    QUOTA_EXCEEDED,
    /**
     * @since 4.0.0, CE 2019.1
     */
    PAYLOAD_FORMAT_INVALID
}
