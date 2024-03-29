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

import java.io.File;
import java.util.Set;

/**
 * The server information contains specific data about HiveMQ instance the extensions runs in.
 *
 * @author Christoph Schäbel
 * @since 4.0.0, CE 2019.1
 */
@DoNotImplement
public interface ServerInformation {

    /**
     * The version syntax differs depending on the HiveMQ edition in use.
     * <p>
     * For the community edition it's "year.release-number", so for example 2019.1 (first release in 2019).
     * <p>
     * For the enterprise edition it's "major.minor.patch", so for example 4.5.10.
     *
     * @return The version string of the HiveMQ instance.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull String getVersion();

    /**
     * The location of the 'home' folder where HiveMQ is installed.
     *
     * @return The home folder of HiveMQ.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull File getHomeFolder();

    /**
     * The location of the 'data' folder containing the data for the HiveMQ instance.
     *
     * @return The data folder of HiveMQ.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull File getDataFolder();

    /**
     * The location of the 'log' folder containing everything related to logs.
     *
     * @return The log folder of HiveMQ.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull File getLogFolder();

    /**
     * The location of the 'extensions' folder that contains all extension.
     *
     * @return The extension folder of HiveMQ.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull File getExtensionsFolder();

    /**
     * All configured listeners.
     *
     * @return A set which contains a {@link Listener} instance for each configured listener.
     * @since 4.2.0, CE 2020.1
     */
    @NotNull Set<Listener> getListener();
}
