package com.ltln.modules.ni.omc.framework.locator;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class SoftHashMap<K, V> extends AbstractMap<K, V> {

    private Map<K, SoftValue<V>> hash = new HashMap<K, SoftValue<V>>();
    private final ReferenceQueue<V> queue = new ReferenceQueue<V>();

    public SoftHashMap() {
    }

    @Override
    public V get(Object key) {
        V res = null;
        SoftReference<V> sr = (SoftReference<V>) hash.get(key);
        if (sr != null) {
            res = sr.get();
            if (res == null) {
                hash.remove(key);
            }
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    private void processQueue() {
        for (;;) {
            SoftValue<V> sv = (SoftValue<V>) queue.poll();
            if (sv != null) {
                hash.remove(sv.key);
            } else {
                return;
            }
        }
    }

    @Override
    public V put(K key, V value) {
        processQueue();
        hash.put(key, new SoftValue<V>(value, key, queue));
        return value;
    }

    @Override
    public V remove(Object key) {
        processQueue();
        SoftValue<V> sr = hash.remove(key);
        return sr.get();
    }

    @Override
    public void clear() {
        processQueue();
        hash.clear();
    }

    @Override
    public int size() {
        processQueue();
        return hash.size();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * SoftValue
     */
    private static class SoftValue<T> extends SoftReference<T> {

        private final Object key;

        private SoftValue(T k, Object key, ReferenceQueue<T> q) {
            super(k, q);
            this.key = key;
        }
    }
}
