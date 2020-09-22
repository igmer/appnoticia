package com.traikers.appnoticia;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.traikers.appnoticia.models.Noticia;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        c.add(Calendar.DATE, -5);
        Date nowMinus15 = c.getTime();
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(nowMinus15);
       //usando la clase Calendar obtenemos la fecha y luego le restamos 5 dias, para que nos traiga resultados de los ultmimos 5 dias

        String tema = getIntent().getExtras().getString("tema");
        String url = "http://newsapi.org/v2/everything?q="+tema+"&from="+fechaActual+"&sortBy=publishedAt&apiKey=f23f1461e4e547a18717a05eea7070f9&language=es";
        getNoticia(url);
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
                    System.out.println(dataResponse);
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
        AdaptadorNoticia  adaptadorNoticia = new AdaptadorNoticia(m,getApplicationContext());
        lvNoticia.setAdapter(adaptadorNoticia);

    }
}