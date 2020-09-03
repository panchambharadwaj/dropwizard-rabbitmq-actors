/*
 * Copyright (c) 2019 Santanu Sinha <santanu.sinha@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.clojars.pancham.dropwizard.actors.retry;

import org.clojars.pancham.dropwizard.actors.retry.config.CountLimitedExponentialWaitRetryConfig;
import org.clojars.pancham.dropwizard.actors.retry.config.CountLimitedFixedWaitRetryConfig;
import org.clojars.pancham.dropwizard.actors.retry.config.CountLimitedIncrementalWaitRetryConfig;
import org.clojars.pancham.dropwizard.actors.retry.config.NoRetryConfig;
import org.clojars.pancham.dropwizard.actors.retry.config.RetryConfig;
import org.clojars.pancham.dropwizard.actors.retry.config.TimeLimitedExponentialWaitRetryConfig;
import org.clojars.pancham.dropwizard.actors.retry.config.TimeLimitedFixedWaitRetryConfig;
import org.clojars.pancham.dropwizard.actors.retry.config.TimeLimitedIncrementalWaitRetryConfig;
import org.clojars.pancham.dropwizard.actors.retry.impl.CountLimitedExponentialWaitRetryStrategy;
import org.clojars.pancham.dropwizard.actors.retry.impl.CountLimitedFixedWaitRetryStrategy;
import org.clojars.pancham.dropwizard.actors.retry.impl.CountLimitedIncrementalWaitRetryStrategy;
import org.clojars.pancham.dropwizard.actors.retry.impl.NoRetryStrategy;
import org.clojars.pancham.dropwizard.actors.retry.impl.TimeLimitedExponentialWaitRetryStrategy;
import org.clojars.pancham.dropwizard.actors.retry.impl.TimeLimitedFixedWaitRetryStrategy;
import org.clojars.pancham.dropwizard.actors.retry.impl.TimeLimitedIncrementalWaitRetryStrategy;

/**
 * Creates strategy based on config
 */
public class RetryStrategyFactory {
    public RetryStrategy create(RetryConfig config) {
        switch (config.getType()) {
            case NO_RETRY:
                return new NoRetryStrategy(NoRetryConfig.class.cast(config));
            case TIME_LIMITED_EXPONENTIAL_BACKOFF:
                return new TimeLimitedExponentialWaitRetryStrategy(TimeLimitedExponentialWaitRetryConfig.class.cast(config));
            case TIME_LIMITED_INCREMENTAL_WAIT:
                return new TimeLimitedIncrementalWaitRetryStrategy(TimeLimitedIncrementalWaitRetryConfig.class.cast(config));
            case TIME_LIMITED_FIXED_WAIT:
                return new TimeLimitedFixedWaitRetryStrategy(TimeLimitedFixedWaitRetryConfig.class.cast(config));
            case COUNT_LIMITED_EXPONENTIAL_BACKOFF:
                return new CountLimitedExponentialWaitRetryStrategy(CountLimitedExponentialWaitRetryConfig.class.cast(config));
            case COUNT_LIMITED_INCREMENTAL_WAIT:
                return new CountLimitedIncrementalWaitRetryStrategy(CountLimitedIncrementalWaitRetryConfig.class.cast(config));
            case COUNT_LIMITED_FIXED_WAIT:
                return new CountLimitedFixedWaitRetryStrategy(CountLimitedFixedWaitRetryConfig.class.cast(config));
        }
        return null;
    }
}
