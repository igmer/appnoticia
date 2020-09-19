package com.traikers.appnoticia;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.traikers.appnoticia.models.Noticia;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ListView lvNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvNoticia = findViewById(R.id.lvNoticia);
        getNoticia("http://newsapi.org/v2/everything?q=bitcoin&from=2020-08-19&sortBy=publishedAt&apiKey=f23f1461e4e547a18717a05eea7070f9&language=es");


    }

    private void getNoticia(String url) { //metodo que pide las noticias al servidor
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        MediaType mediaType = MediaType.parse("text/json");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("content-type", "text/json")
                .build();
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.i("vererror", e.getMessage());


                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    final String dataResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setearNoticias(dataResponse);
                        }

                    });
                }


            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void setearNoticias(String dataResponse) {
        Gson gson = new Gson();
        Noticia m = gson.fromJson(dataResponse, Noticia.class);
        System.out.println("aqui"+m.getTotalResults());

    }
}