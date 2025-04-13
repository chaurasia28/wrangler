/*
 * Copyright © 2017-2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;

public class TimeDuration implements Token {
    private final String rawValue;
    private final long nanoseconds;

    public TimeDuration(String value) {
        this.rawValue = value;
        this.nanoseconds = parseDuration(value);
    }

    private long parseDuration(String input) {
        String unit = input.replaceAll("[^A-Za-z]", "");
        double num = Double.parseDouble(input.replaceAll("[A-Za-z]", ""));
        switch (unit) {
            case "ms": return (long) (num * 1_000_000);
            case "s": return (long) (num * 1_000_000_000);
            case "min": return (long) (num * 60 * 1_000_000_000);
            default: return (long) num; // Assume nanoseconds
        }
    }

    @Override
    public String value() { return rawValue; }

    public long getNanoSeconds() { return nanoseconds; }

    @Override
    public TokenType type() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'type'");
    }

    @Override
    public JsonElement toJson() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toJson'");
    }
}
