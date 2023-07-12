/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.reliability.infinispan.proto;

import java.util.Map;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.types.protobuf.AnySchema;

public class EntryImpl implements Map.Entry<String, Object> {

    private String key;

    private Object value;

    public EntryImpl(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @ProtoFactory
    public EntryImpl(String key, AnySchema.Any protoValue) {
        this.key = key;
        this.value = ProtoStreamUtils.fromAnySchema(protoValue);
    }

    @ProtoField(number = 1, required = true)
    @Override
    public String getKey() {
        return key;
    }

    @ProtoField(number = 2, required = true)
    public AnySchema.Any getProtoValue() {
        return ProtoStreamUtils.toAnySchema(value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Object setValue(Object value) {
        Object oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public String toString() {
        return "EntryImpl{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}