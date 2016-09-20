package com.xiechanglei.code.db;

public interface FilterChain<T> {
	public boolean dofilter(T t);
}
