/*
 * Cardinal-Components-API
 * Copyright (C) 2019-2021 OnyxStudios
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package dev.onyxstudios.cca.internal.base.asm;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;

import java.util.Set;

public final class QualifiedComponentFactory<I> {
    private final I factory;
    private final Class<? extends Component> impl;
    private final Set<ComponentKey<?>> dependencies;
    SortingState sortingState = SortingState.UNSORTED;

    public QualifiedComponentFactory(I factory, Class<? extends Component> impl, Set<ComponentKey<?>> dependencies) {
        this.factory = factory;
        this.impl = impl;
        this.dependencies = dependencies;
    }

    public I factory() {
        return factory;
    }

    public Class<? extends Component> impl() {
        return impl;
    }

    public Set<ComponentKey<?>> dependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return "QualifiedComponentFactory[" +
            "factory=" + factory + ", " +
            "impl=" + impl + ", " +
            "dependencies=" + dependencies + ']';
    }

    enum SortingState {
        UNSORTED, SORTING, SORTED
    }
}
