package com.alura.literatura.service;

public interface IConversor {
    <T> T getData(String json, Class<T> classe);
}
