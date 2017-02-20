package com.hikvision.mdp.web.common.interfaces;

public interface Function<T, E> {
    public T callback(E e);

}
