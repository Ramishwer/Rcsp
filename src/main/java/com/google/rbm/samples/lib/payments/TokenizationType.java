/*
 * Copyright (C) 2023 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.rbm.samples.lib.payments;

/**
 * Enum values for the tokenization type for the payment processing provider.
 * https://developers.google.com/rcs-business-messaging/rbm/rest/v1/phones.agentMessages#tokenizationtype
 */
public enum TokenizationType {
    PAYMENT_GATEWAY,
    NETWORK_TOKEN,
    DIRECT
}
