package com.github.bicoco.collections.functions;

public interface ReduceFunction<T, R> {
	public R reduce(R r, T t);
}
