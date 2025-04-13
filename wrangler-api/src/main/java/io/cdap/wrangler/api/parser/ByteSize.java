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

public class ByteSize implements Token {
    private final String rawValue;
    private final long bytes;

    public ByteSize(String value) {
        this.rawValue = value;
        this.bytes = parseBytes(value);
    }

    private long parseBytes(String input) {
        String unit = input.replaceAll("[^A-Za-z]", "").toUpperCase();
        double num = Double.parseDouble(input.replaceAll("[A-Za-z]", ""));
        switch (unit) {
            case "KB": return (long) (num * 1024);
            case "MB": return (long) (num * 1024 * 1024);
            case "GB": return (long) (num * 1024 * 1024 * 1024);
            default: return (long) num; // Assume bytes
        }
    }

    @Override
    public String value() { return rawValue; }

    public long getBytes() { return bytes; }

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
