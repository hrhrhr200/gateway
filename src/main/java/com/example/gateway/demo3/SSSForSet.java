package com.example.gateway.demo3;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * Created by hrhrh on 2020/7/17 17:04
 */
public class SSSForSet<E> extends ForwardSet<E>{

    private int addcouNT = 0;

    public SSSForSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addcouNT++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addcouNT+=c.size();
        return super.addAll(c);
    }

    public int getAddcouNT() {
        return addcouNT;
    }


    public static <E extends Comparable<? super E>> E max(Collection<? extends E> c) throws IllegalAccessException {
        if(CollectionUtils.isEmpty(c)) {
            throw new IllegalAccessException("Empty Collection");
        }
        E result = null;

        for(E e : c) {
            if(null == result || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }

    public static<E> void swap(List<E> list, E i ,E j) {
        //list.set(i, list.set(j, list.get(i)));

    }

}
