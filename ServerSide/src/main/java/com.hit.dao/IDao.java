package com.hit.dao;

import java.io.IOException;
import java.util.List;

public interface IDao<K, V> {
    public boolean delete(K key) throws IOException;
    public V find(K key) throws IOException;
    public void save(V entity) throws IOException;
    public List<V> readAll() throws IOException;
}
