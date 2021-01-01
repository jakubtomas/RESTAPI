package sk.itsovy.adnroid.restapi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class copymain extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("skusobna sprava");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);


        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://jsonplaceholder.typicode.com/")
                .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //Call<List<Post>> call = jsonPlaceHolderApi.getPosts(); // list postov
        Call<List<Word>> call = jsonPlaceHolderApi.getWord(); // list postov


        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                System.out.println("dalsia funkcia");

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Word> responseFromServer = response.body();
                System.out.println(responseFromServer);

                System.out.println("data from Server magor");

                for (int i = 0; i < responseFromServer.size() ; i++) {
                    System.out.println("vysledok");
                    System.out.println(responseFromServer);

                    String content = "";
                    content += " vypis" + responseFromServer.get(i).getWord();
                    textViewResult.append(content);
                }

                //System.out.println(response.body());
                //System.out.println(dataFromServer);
                //System.out.println(dataFromServer[1]);
             //   List<Post> posts = response.body();
                /*System.out.println("vysledok post" + posts);

                for (Post post : posts) {
                    System.out.println(post);

                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    textViewResult.append(content);
                }*/

                //textViewResult.append(String.valueOf(dataFromServer));
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
/*
            @Override
            public void onFailure(Call<Array<Word>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }*/

           /* @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }*/
        });
    }


}