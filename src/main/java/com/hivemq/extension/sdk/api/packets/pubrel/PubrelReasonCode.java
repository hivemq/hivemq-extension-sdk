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

package com.hivemq.extension.sdk.api.packets.pubrel;

/**
 * MQTT 5 Reason codes for PUBREL.
 * <p>
 * MQTT 3 does not support reason codes for the above mentioned MQTT packet.
 *
 * @author Yannick Weber
 * @since 4.3.0, CE 2020.1
 */
public enum PubrelReasonCode {

    /**
     * @since 4.3.0, CE 2020.1
     */
    SUCCESS,

    /**
     * @since 4.3.0, CE 2020.1
     */
    PACKET_IDENTIFIER_NOT_FOUND
}