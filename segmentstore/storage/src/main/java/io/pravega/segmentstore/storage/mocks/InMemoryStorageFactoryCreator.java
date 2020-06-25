/**
 * Copyright (c) Dell Inc., or its subsidiaries. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package io.pravega.segmentstore.storage.mocks;

import io.pravega.segmentstore.storage.ConfigSetup;
import io.pravega.segmentstore.storage.StorageFactory;
import io.pravega.segmentstore.storage.StorageFactoryCreator;
import io.pravega.segmentstore.storage.StorageFactoryInfo;
import io.pravega.segmentstore.storage.StorageManagerLayoutType;
import io.pravega.segmentstore.storage.StorageManagerType;
import lombok.val;

import java.util.concurrent.ScheduledExecutorService;

public class InMemoryStorageFactoryCreator implements StorageFactoryCreator {

    @Override
    public StorageFactory createFactory(StorageFactoryInfo storageFactoryInfo, ConfigSetup setup, ScheduledExecutorService executor) {
        if (storageFactoryInfo.getStorageManagerType().equals(StorageManagerType.CHUNK_MANAGER)) {
            val factory = new InMemorySimpleStorageFactory(executor, true);
            return factory;
        } else {
            InMemoryStorageFactory factory = new InMemoryStorageFactory(executor);
            return factory;
        }

    }

    @Override
    public StorageFactoryInfo[] getStorageFactories() {
        return new StorageFactoryInfo[]{
                StorageFactoryInfo.builder()
                        .name("INMEMORY")
                        .storageManagerLayoutType(StorageManagerLayoutType.LEGACY)
                        .storageManagerType(StorageManagerType.NONE)
                        .build(),
                StorageFactoryInfo.builder()
                        .name("INMEMORY")
                        .storageManagerLayoutType(StorageManagerLayoutType.TABLE_BASED)
                        .storageManagerType(StorageManagerType.CHUNK_MANAGER)
                        .build()
        };
    }

}
