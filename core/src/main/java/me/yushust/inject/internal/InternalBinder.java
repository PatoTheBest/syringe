package me.yushust.inject.internal;

import me.yushust.inject.bind.Binder;
import me.yushust.inject.bind.Binding;
import me.yushust.inject.identity.Key;

import java.util.Collection;

public interface InternalBinder extends Binder {

    <T> void removeBinding(Key<T> key);

    <T> void setBinding(Binding<T> binding);

    <T> Binding<T> findBinding(Key<T> key);

    Collection<Binding<?>> getBindings();

}