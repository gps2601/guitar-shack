package com.guitarshack;

import retrofit2.Call;

import java.io.IOException;

public interface Requester<T> {
    T execute(Call<T> call) throws IOException;
}
