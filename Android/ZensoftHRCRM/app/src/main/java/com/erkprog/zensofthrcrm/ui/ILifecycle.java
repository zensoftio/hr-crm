package com.erkprog.zensofthrcrm.ui;

public interface ILifecycle<V> {
  void bind(V view);

  void unbind();
}
