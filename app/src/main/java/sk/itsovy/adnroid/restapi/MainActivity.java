package sk.itsovy.adnroid.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private EditText editText;
    private String inputWord = "apple";
    private String inputParameter;
    private TextView searchParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        editText = findViewById(R.id.editTextLetter);
        searchParameter = findViewById(R.id.searchingParameter);


       // String inputParameter = editText.getText().toString();
        // inputParameter = editText.getText().toString();

       // System.out.println("input value " + inputParameter);

         Button button = findViewById(R.id.buttonRefresh);

        // editText

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.dictionaryapi.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputParameter = editText.getText().toString();

                    searchParameter.setText(inputParameter);
                    System.out.println("click click " + inputParameter);
                    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                    Call<ResponseBody> call = jsonPlaceHolderApi.getStringResponse2(inputParameter);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (!response.isSuccessful()) {
                                textViewResult.setText("Code: " + response.code());
                                return;
                            }

                            Object obj = null;
                            obj = response.body(); // try to string



                            try {
                                //      JSONArray jsonArray1 = new JSONArray(response.body().toString());
                                JSONArray jsonArray2 = new JSONArray(response.body().string());
//                                JSONArray jsonArray3 = new JSONArray(response.body());

                                Object word = jsonArray2.getJSONObject(0).get("meanings");

                                JSONArray jArray = (JSONArray) word;

                                System.out.println("jArray");
                                System.out.println(jArray);

                                System.out.println("jArray  partOfSpeech" + jArray.getJSONObject(0).get("partOfSpeech"));
                                System.out.println("jArray  definitions" + jArray.getJSONObject(0).get("definitions"));

                                JSONArray definition = (JSONArray) jArray.getJSONObject(0).get("definitions");

                                System.out.println("definition");
                                System.out.println(definition);
                                System.out.println("defintion 2 ");
                                System.out.println(definition.getJSONObject(0).get("definition"));
                                System.out.println(definition.getJSONObject(0).get("example"));

                                System.out.println("get Class" + word.getClass());
                                System.out.println("get Class" + word.getClass().getName());
                                System.out.println("get Class" + word.getClass().getName());
                                //  JSONObject json = new JSONObject(response.body().string());
                                System.out.println("json is "  + jsonArray2 );
                                System.out.println("json is word"  + jsonArray2.getJSONObject(0).get("word") );
                                System.out.println("json is phonetics "  + jsonArray2.getJSONObject(0).get("phonetics") );
                                System.out.println("json is meanings "  + jsonArray2.getJSONObject(0).get("meanings") );
                            //    System.out.println("json is meanings "  + jsonArray2.getJSONObject(0).get("text") );


                                System.out.println(" loto");
                                System.out.println();

                                textViewResult.setText(jsonArray2.toString());

                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            textViewResult.setText(t.getMessage());

                        }

                    });
                }
            });




       /* call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                String pole = response.body();
                //String pole2 = response.body();

                System.out.println("hello");
                System.out.println(pole);
                textViewResult.setText(pole);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
*/

        /*call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });*/
    }
}