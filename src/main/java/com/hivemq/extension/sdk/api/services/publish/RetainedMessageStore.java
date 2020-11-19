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

package com.hivemq.extension.sdk.api.services.publish;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.services.ManagedExtensionExecutorService;
import com.hivemq.extension.sdk.api.services.exception.DoNotImplementException;
import com.hivemq.extension.sdk.api.services.exception.IncompatibleHiveMQVersionException;
import com.hivemq.extension.sdk.api.services.exception.IterationFailedException;
import com.hivemq.extension.sdk.api.services.exception.RateLimitExceededException;
import com.hivemq.extension.sdk.api.services.general.IterationCallback;
import com.hivemq.extension.sdk.api.services.general.IterationContext;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * The retained message store allows the management of retained messages from within extensions.
 *
 * @author Lukas Brandl
 * @author Christoph Schäbel
 * @since 4.0.0, CE 2019.1
 */
@DoNotImplement
public interface RetainedMessageStore {

    /**
     * Get the retained message for a topic, if it exist.
     * <p>
     * {@link CompletableFuture} fails with a {@link RateLimitExceededException} if the extension service rate limit was
     * exceeded.
     *
     * @param topic The topic.
     * @return A {@link CompletableFuture} which contains the retained message for the specific topic or
     *         <code>null</code>.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull CompletableFuture<Optional<RetainedPublish>> getRetainedMessage(@NotNull String topic);

    /**
     * Removes the retained message for the given topic. If there isn't any retained message on the topic yet, nothing
     * will happen.
     * <p>
     * {@link CompletableFuture} fails with a {@link RateLimitExceededException} if the extension service rate limit was
     * exceeded.
     *
     * @param topic The topic from which the retained message should be removed.
     * @return A {@link CompletableFuture} which returns after removal.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull CompletableFuture<Void> remove(@NotNull String topic);

    /**
     * Removes all retained messages from the message store.
     * <p>
     * {@link CompletableFuture} fails with a {@link RateLimitExceededException} if the extension service rate limit was
     * exceeded.
     *
     * @return A {@link CompletableFuture} which returns after removal.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull CompletableFuture<Void> clear();

    /**
     * This method adds or replaces a retained message.
     * <p>
     * {@link CompletableFuture} fails with a {@link RateLimitExceededException} if the extension service rate limit was
     * exceeded.
     * <p>
     * {@link CompletableFuture} fails with a {@link DoNotImplementException} if the retained publish is implemented by
     * the extension.
     *
     * @param retainedPublish Retained publish which should be added or replaced.
     * @return A {@link CompletableFuture} which returns after adding or replacing the retained publish.
     * @since 4.0.0, CE 2019.1
     */
    @NotNull CompletableFuture<Void> addOrReplace(@NotNull RetainedPublish retainedPublish);

    /**
     * Iterate over all retained messages in the HiveMQ cluster.
     * <p>
     * The callback is called once for each retained message. Passed to each execution of the callback is all
     * information about the retained message, such as its payload and its metadata. Retained messages that have
     * exceeded their message expiry interval are not included.
     * <p>
     * The callback is executed in the {@link ManagedExtensionExecutorService} per default. Use the overloaded methods
     * to pass a custom executor for the callback. If you want to collect the results of each execution of the callback
     * in a collection please make sure to use a concurrent collection (thread-safe), as the callback might be executed
     * in another thread as the calling thread of this method.
     * <p>
     * The results are not sorted in any way, no ordering of any kind is guaranteed.
     * <p>
     * CAUTION: This method can be used in large scale deployments, but it is a very expensive operation. Do not call
     * this method in short time intervals.
     * <p>
     * If you are searching for a specific entry in the results and have found what you are looking for, you can abort
     * further iteration and save resources by calling {@link IterationContext#abortIteration()}.
     * <p>
     * {@link CompletableFuture} fails with an {@link IncompatibleHiveMQVersionException} if not all HiveMQ nodes in the
     * cluster have at least version 4.4.0. {@link CompletableFuture} fails with a {@link RateLimitExceededException} if
     * the extension service rate limit was exceeded. {@link CompletableFuture} fails with a {@link
     * IterationFailedException} if the cluster topology changed during the iteration (e.g. a network-split, node leave
     * or node join)
     *
     * @param callback An {@link IterationCallback} that is called for every returned result.
     * @return A {@link CompletableFuture} that is completed after all iterations are executed, no match is found or the
     *         iteration is aborted manually with the {@link IterationContext}.
     * @throws NullPointerException if the passed callback is null.
     * @since 4.4.0, CE 2020.4
     */
    @NotNull CompletableFuture<Void> iterateAllRetainedMessages(@NotNull IterationCallback<RetainedPublish> callback);

    /**
     * Iterate over all retained messages in the HiveMQ cluster.
     * <p>
     * The callback is called once for each retained message. Passed to each execution of the callback is all
     * information about the retained message, such as its payload and its metadata. Retained messages that have
     * exceeded their message expiry interval are not included.
     * <p>
     * The callback is executed in the passed {@link Executor}. If you want to collect the results of each execution of
     * the callback in a collection please make sure to use a concurrent collection (thread-safe), as the callback might
     * be executed in another thread as the calling thread of this method.
     * <p>
     * The results are not sorted in any way, no ordering of any kind is guaranteed.
     * <p>
     * CAUTION: This method can be used in large scale deployments, but it is a very expensive operation. Do not call
     * this method in short time intervals.
     * <p>
     * If you are searching for a specific entry in the results and have found what you are looking for, you can abort
     * further iteration and save resources by calling {@link IterationContext#abortIteration()}.
     * <p>
     * {@link CompletableFuture} fails with an {@link IncompatibleHiveMQVersionException} if not all HiveMQ nodes in the
     * cluster have at least version 4.4.0. {@link CompletableFuture} fails with a {@link RateLimitExceededException} if
     * the extension service rate limit was exceeded. {@link CompletableFuture} fails with a {@link
     * IterationFailedException} if the cluster topology changed during the iteration (e.g. a network-split, node leave
     * or node join)
     *
     * @param callback         An {@link IterationCallback} that is called for every returned result.
     * @param callbackExecutor An {@link Executor} that the {@link IterationCallback} is executed in.
     * @return A {@link CompletableFuture} that is completed after all iterations are executed, no match is found or the
     *         iteration is aborted manually with the {@link IterationContext}.
     * @throws NullPointerException if the passed callback or callbackExecutor are null.
     * @since 4.4.0, CE 2020.4
     */
    @NotNull CompletableFuture<Void> iterateAllRetainedMessages(
            @NotNull IterationCallback<RetainedPublish> callback, @NotNull Executor callbackExecutor);
}
