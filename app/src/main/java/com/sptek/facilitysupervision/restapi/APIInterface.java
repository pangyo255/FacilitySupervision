package com.sptek.facilitysupervision.restapi;

import com.sptek.facilitysupervision.restapi.pojo.CreateUserResponse;
import com.sptek.facilitysupervision.restapi.pojo.MultipleResource;
import com.sptek.facilitysupervision.restapi.pojo.User;
import com.sptek.facilitysupervision.restapi.pojo.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("api/unknown")
    Call<MultipleResource> doGetListResources();

    @POST("api/users")
    Call<User> createUser(@Body User user);

    @GET("api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("api/users?")
    Call<CreateUserResponse> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
