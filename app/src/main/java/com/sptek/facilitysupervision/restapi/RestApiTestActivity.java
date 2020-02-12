package com.sptek.facilitysupervision.restapi;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sptek.facilitysupervision.R;
import com.sptek.facilitysupervision.restapi.pojo.CreateUserResponse;
import com.sptek.facilitysupervision.restapi.pojo.MultipleResource;
import com.sptek.facilitysupervision.restapi.pojo.User;
import com.sptek.facilitysupervision.restapi.pojo.UserList;

import java.util.List;

public class RestApiTestActivity extends AppCompatActivity implements View.OnClickListener {
    TextView responseText;
    APIInterface apiInterface;

    Button btnMultipleResource;
    Button btnUser;
    Button btnUserList;
    Button btnUserResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api_test);
        responseText = findViewById(R.id.responseText);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        init();
    }

    private void init() {
        btnMultipleResource = findViewById(R.id.btn_multipleResources);
        btnUser = findViewById(R.id.btn_user);
        btnUserList = findViewById(R.id.btn_user_list);
        btnUserResponse = findViewById(R.id.btn_create_user_response);
        btnMultipleResource.setOnClickListener(this);
        btnUser.setOnClickListener(this);
        btnUserList.setOnClickListener(this);
        btnUserResponse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_multipleResources:
                Call<MultipleResource> call = apiInterface.doGetListResources();
                call.enqueue(new Callback<MultipleResource>() {
                    @Override
                    public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {
                        Log.d("TAG", response.code() + "");

                        String displayResponse = "";

                        MultipleResource resource = response.body();
                        Integer text = resource.page;
                        Integer total = resource.total;
                        Integer totalPages = resource.totalPages;
                        List<MultipleResource.Datum> datumList = resource.data;

                        displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

                        for (MultipleResource.Datum datum : datumList) {
                            displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
                        }

                        responseText.setText(displayResponse);
                    }

                    @Override
                    public void onFailure(Call<MultipleResource> call, Throwable t) {
                        call.cancel();
                    }
                });
                break;
            case R.id.btn_user:
                /**
                 Create new user
                 **/
                User user = new User("morpheus", "leader");
                Call<User> call1 = apiInterface.createUser(user);
                call1.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user1 = response.body();

                        Toast.makeText(getApplicationContext(), user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        call.cancel();
                    }
                });
                break;
            case R.id.btn_user_list:
                /**
                 GET List Users
                 **/
                Call<UserList> call2 = apiInterface.doGetUserList("2");
                call2.enqueue(new Callback<UserList>() {
                    @Override
                    public void onResponse(Call<UserList> call, Response<UserList> response) {

                        UserList userList = response.body();
                        Integer text = userList.page;
                        Integer perpage = userList.perpage;
                        Integer total = userList.total;
                        Integer totalPages = userList.totalPages;
                        List<UserList.Datum> datumList = userList.data;
                        Toast.makeText(getApplicationContext(), text + " page\n" + perpage + " perpage\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                        for (UserList.Datum datum : datumList) {
                            Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<UserList> call, Throwable t) {
                        call.cancel();
                    }
                });
                break;
            case R.id.btn_create_user_response:
                /**
                 POST name and job Url encoded.
                 **/
                Call<CreateUserResponse> call3 = apiInterface.doCreateUserWithField("morpheus","leader");
                call3.enqueue(new Callback<CreateUserResponse>() {
                    @Override
                    public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                        CreateUserResponse createUserResponse = response.body();
                        String name = createUserResponse.name;
                        String job = createUserResponse.job;
                        String id = createUserResponse.id;
                        String createdAt = createUserResponse.createdAt;

                        Toast.makeText(
                                RestApiTestActivity.this,
                                name +" name\n"
                                + job + " job\n"
                                + id + " id\n"
                                + createdAt, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                        call.cancel();
                    }
                });
                break;

        }
    }
}
