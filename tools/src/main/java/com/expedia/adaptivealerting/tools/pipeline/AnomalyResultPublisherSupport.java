/*
 * Copyright 2018 Expedia Group, Inc.
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
package com.expedia.adaptivealerting.tools.pipeline;

import com.expedia.adaptivealerting.core.anomaly.AnomalyResult;

import java.util.LinkedList;
import java.util.List;

import static com.expedia.adaptivealerting.core.util.AssertUtil.notNull;

/**
 * @author Willie Wheeler
 */
public final class AnomalyResultPublisherSupport {
    private final List<AnomalyResultSubscriber> subscribers = new LinkedList<>();
    
    public void addSubscriber(AnomalyResultSubscriber subscriber) {
        notNull(subscriber, "subscriber can't be null");
        subscribers.add(subscriber);
    }
    
    public void removeSubscriber(AnomalyResultSubscriber subscriber) {
        notNull(subscriber, "subscriber can't be null");
        subscribers.remove(subscriber);
    }
    
    public void publish(AnomalyResult anomalyResult) {
        notNull(anomalyResult, "anomalyResult can't be null");
        subscribers.stream().forEach(subscriber -> subscriber.next(anomalyResult));
    }
}