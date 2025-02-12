package com.xabber.android.data.xaccount;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xabber.android.BuildConfig;
import com.xabber.android.data.SettingsManager;
import com.xabber.android.data.http.ICrowdfundingApi;
import com.xabber.android.data.http.IPushApi;
import com.xabber.android.data.http.IXabberCom;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by valery.miller on 17.07.17.
 */

public class HttpApiManager {

    public static final String XABBER_FORGOT_PASS_URL = "https://www.qtune.io/account/auth/forgot-password/";
    public static final String XABBER_SIGNUP_URL      = "https://www.qtune.io/account/auth/signup/";
    private static final String XABBER_EMAIL_CONFIRM_URL = "https://www.qtune.io/account/emails/confirmation/";
    private static final String XABBER_DEV_EMAIL_CONFIRM_URL = "http://dev.qtune.io/account/emails/confirmation/";

    public static final String XABBER_API_URL = "https://api.qtune.io/api/v2/";
    public static final String XABBER_DEV_API_URL = "https://api.dev.qtune.io/api/v2/";
    private static final String XABBER_COM_URL = "https://www.qtune.io/";
    private static final String XABBER_PUSH_API_URL = "https://push.qtune.io/api/";
    private static final String CROWDFUNDING_URL = "https://crowdfunding.qtune.io/api/v1/";
    private static final String CROWDFUNDING_DEV_URL = "https://crowdfunding.dev.qtune.io/api/v1/";

    private static IXabberApi xabberApi;
    private static IXabberCom xabberCom;
    private static ICrowdfundingApi crowdfundingApi;
    private static IPushApi pushApi;

    private static Retrofit retrofit;
    private static Retrofit retrofitXabberCom;
    private static Retrofit retrofitCrowdfunding;
    private static Retrofit retrofitPush;

    public static IXabberApi getXabberApi() {
        if (xabberApi == null)
            xabberApi = getRetrofit().create(IXabberApi.class);
        return xabberApi;
    }

    public static IXabberCom getXabberCom() {
        if (xabberCom == null)
            xabberCom = getRetrofitXabberCom().create(IXabberCom.class);
        return xabberCom;
    }

    public static ICrowdfundingApi getCrowdfundingApi() {
        if (crowdfundingApi == null)
            crowdfundingApi = getCrowdfundingRetrofit().create(ICrowdfundingApi.class);
        return crowdfundingApi;
    }

    public static IPushApi getPushApi() {
        if (pushApi == null)
            pushApi = getPushRetrofit().create(IPushApi.class);
        return pushApi;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

            // if debug enable http logging
            if (BuildConfig.DEBUG)
                httpClientBuilder.addInterceptor(loggingInterceptor);

            OkHttpClient httpClient = httpClientBuilder.build();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(AuthManager.ListClientSettingsDTO.class, new ClientSettingsDeserializer())
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                .baseUrl(SettingsManager.useDevelopAPI() ? XABBER_DEV_API_URL : XABBER_API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitXabberCom() {
        if (retrofitXabberCom == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

            // if debug enable http logging
            if (BuildConfig.DEBUG)
                httpClientBuilder.addInterceptor(loggingInterceptor);

            OkHttpClient httpClient = httpClientBuilder.build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofitXabberCom = new Retrofit.Builder()
                    .baseUrl(XABBER_COM_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofitXabberCom;
    }

    public static String getXabberEmailConfirmUrl() {
        return SettingsManager.useDevelopAPI() ? XABBER_DEV_EMAIL_CONFIRM_URL : XABBER_EMAIL_CONFIRM_URL;
    }

    private static Retrofit getCrowdfundingRetrofit() {
        if (retrofitCrowdfunding == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

            // if debug enable http logging
            if (BuildConfig.DEBUG)
                httpClientBuilder.addInterceptor(loggingInterceptor);

            OkHttpClient httpClient = httpClientBuilder.build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofitCrowdfunding = new Retrofit.Builder()
                    .baseUrl(SettingsManager.useDevelopAPI() ? CROWDFUNDING_DEV_URL : CROWDFUNDING_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofitCrowdfunding;
    }

    private static Retrofit getPushRetrofit() {
        if (retrofitPush == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

            // if debug enable http logging
            if (BuildConfig.DEBUG)
                httpClientBuilder.addInterceptor(loggingInterceptor);

            OkHttpClient httpClient = httpClientBuilder.build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofitPush = new Retrofit.Builder()
                    .baseUrl(XABBER_PUSH_API_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofitPush;
    }
}

