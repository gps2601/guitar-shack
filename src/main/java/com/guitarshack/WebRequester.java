package com.guitarshack;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class WebRequester<T> implements Requester<T> {
    public WebRequester() {
    }

    @Override
    public T execute(Call<T> call) throws IOException {
        Response<T> execute = call.execute();
        return execute.body();
    }
}
