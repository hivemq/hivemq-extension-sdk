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

package com.hivemq.extension.sdk.api.services;

import com.codahale.metrics.MetricRegistry;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.Nullable;
import com.hivemq.extension.sdk.api.events.EventRegistry;
import com.hivemq.extension.sdk.api.events.client.ClientLifecycleEventListenerProvider;
import com.hivemq.extension.sdk.api.services.admin.AdminService;
import com.hivemq.extension.sdk.api.services.auth.SecurityRegistry;
import com.hivemq.extension.sdk.api.services.auth.provider.AuthenticatorProvider;
import com.hivemq.extension.sdk.api.services.auth.provider.AuthorizerProvider;
import com.hivemq.extension.sdk.api.services.cluster.ClusterService;
import com.hivemq.extension.sdk.api.services.interceptor.GlobalInterceptorRegistry;
import com.hivemq.extension.sdk.api.services.intializer.ClientInitializer;
import com.hivemq.extension.sdk.api.services.intializer.InitializerRegistry;
import com.hivemq.extension.sdk.api.services.publish.PublishService;
import com.hivemq.extension.sdk.api.services.publish.RetainedMessageStore;
import com.hivemq.extension.sdk.api.services.session.ClientService;
import com.hivemq.extension.sdk.api.services.subscription.SubscriptionStore;

import java.util.Map;

/**
 * Services provide a convenient way for extensions to interact with the HiveMQ core.
 * <p>
 * The following services are available:
 *
 * <ul>
 *   <li>{@link InitializerRegistry}
 *   <li>{@link GlobalInterceptorRegistry}
 *   <li>{@link SecurityRegistry}
 *   <li>{@link ManagedExtensionExecutorService}
 *   <li>{@link SubscriptionStore}
 *   <li>{@link PublishService}
 *   <li>{@link ClusterService}
 *   <li>{@link RetainedMessageStore}
 *   <li>{@link MetricRegistry}
 *   <li>{@link EventRegistry}
 *   <li>{@link ClientService}
 *   <li>{@link AdminService}
 * </ul>
 *
 * @author Christoph Schäbel
 * @author Florian Limpöck
 * @since 4.0.0, CE 2019.1
 */
public class Services {

    private static final String NO_ACCESS_MESSAGE =
            "Static class Services cannot be called from a thread \"%s\" which" +
                    " does not have a HiveMQ extension classloader as its context classloader.";

    //this map is filled by HiveMQ with implementations for all services
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static @Nullable Map<String, @NotNull Object> services;

    /**
     * @return A service to set a {@link ClientInitializer}.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull InitializerRegistry initializerRegistry() {
        return getClassObject(InitializerRegistry.class);
    }

    /**
     * @return A service to set a {@link GlobalInterceptorRegistry}.
     * @since 4.2.0, CE 2020.1
     */
    public static @NotNull GlobalInterceptorRegistry interceptorRegistry() {
        return getClassObject(GlobalInterceptorRegistry.class);
    }

    /**
     * @return A service to register {@link AuthenticatorProvider} and {@link AuthorizerProvider}.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull SecurityRegistry securityRegistry() {
        return getClassObject(SecurityRegistry.class);
    }

    /**
     * @return A service to execute tasks in a HiveMQ managed thread pool.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull ManagedExtensionExecutorService extensionExecutorService() {
        return getClassObject(ManagedExtensionExecutorService.class);
    }

    /**
     * @return A service to add, get and remove subscriptions.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull SubscriptionStore subscriptionStore() {
        return getClassObject(SubscriptionStore.class);
    }

    /**
     * @return A service to publish messages to topics and clients.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull PublishService publishService() {
        return getClassObject(PublishService.class);
    }

    /**
     * @return A service to create a custom cluster discovery.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull ClusterService clusterService() {
        return getClassObject(ClusterService.class);
    }

    /**
     * @return A service to add, get and remove retained messages.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull RetainedMessageStore retainedMessageStore() {
        return getClassObject(RetainedMessageStore.class);
    }

    /**
     * @return A service to get HiveMQ metrics.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull MetricRegistry metricRegistry() {
        return getClassObject(MetricRegistry.class);
    }

    /**
     * @return A service to register a {@link ClientLifecycleEventListenerProvider}.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull EventRegistry eventRegistry() {
        return getClassObject(EventRegistry.class);
    }

    /**
     * @return A service to get client session information, disconnect clients and remove sessions.
     * @since 4.0.0, CE 2019.1
     */
    public static @NotNull ClientService clientService() {
        return getClassObject(ClientService.class);
    }

    /**
     * @return A service to get general information about the state of this HiveMQ instance.
     * @since 4.2.0, CE 2020.1
     */
    public static @NotNull AdminService adminService() {
        return getClassObject(AdminService.class);
    }

    private static <T> @NotNull T getClassObject(final @NotNull Class<T> clazz) {

        if (services == null) {
            throw new RuntimeException(String.format(NO_ACCESS_MESSAGE, Thread.currentThread().getName()));
        }

        final Object object = services.get(clazz.getCanonicalName());
        if (object == null) {
            //don't cache this exception to keep the stacktrace, this is not expected to happen very often
            throw new RuntimeException(String.format(NO_ACCESS_MESSAGE, Thread.currentThread().getName()));
        }
        if (clazz.isInstance(object)) {
            return (T) object;
        }
        throw new RuntimeException(String.format(NO_ACCESS_MESSAGE, Thread.currentThread().getName()));
    }
}
