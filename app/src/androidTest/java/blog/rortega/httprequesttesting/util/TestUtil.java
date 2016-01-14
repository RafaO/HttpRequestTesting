/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package blog.rortega.httprequesttesting.util;

import android.support.annotation.NonNull;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class TestUtil {

    public static <T> Call<T> createCall(T response) {
        return new MockCall<>(response);
    }

    public static <T> Call<T> createCall(int responseCode, T response) {
        return new MockCall<>(response, responseCode);
    }

    private static class MockCall<T> implements Call<T> {

        final T mResponse;

        final int mCode;

        public MockCall(T response, int code) {
            mResponse = response;
            mCode = code;
        }

        public MockCall(T response) {
            this(response, 200);
        }

        @Override
        public Response<T> execute() throws IOException {
            return buildResponse();
        }

        @NonNull
        private Response<T> buildResponse() {
            if (mCode > 199 && mCode < 300) {
                return Response.success(mResponse);
            } else {
                return Response.error(mCode, null);
            }
        }

        @Override
        public void enqueue(Callback<T> callback) {
            callback.onResponse(buildResponse(), null);
        }

        @Override
        public void cancel() {

        }

        @SuppressWarnings("CloneDoesntCallSuperClone")
        @Override
        public Call<T> clone() {
            return new MockCall<>(mResponse);
        }
    }

}
