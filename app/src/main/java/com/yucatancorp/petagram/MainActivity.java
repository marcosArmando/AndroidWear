package com.yucatancorp.petagram;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.firebase.iid.FirebaseInstanceId;
import com.yucatancorp.petagram.RestApi2.EndPoints2;
import com.yucatancorp.petagram.RestApi2.adapter2.RestApiAdapter2;
import com.yucatancorp.petagram.RestApi2.model2.UsuarioResponse;
import com.yucatancorp.petagram.adapter.PageAdapter;
import com.yucatancorp.petagram.fragment.PerfilFragment;
import com.yucatancorp.petagram.fragment.RecyclerViewFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


        private Toolbar toolbar;
        private TabLayout tabLayout;
        private ViewPager viewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            agregarFab();

            toolbar   = (Toolbar)   findViewById(R.id.toolbar);
            tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            viewPager = (ViewPager) findViewById(R.id.viewPager);
            setUpViewPager();


            if(toolbar != null)
            {
                setSupportActionBar(toolbar);
            }


        }

        private ArrayList<Fragment> agregarFragment(){
            ArrayList<Fragment> fragments = new ArrayList<>();
            fragments.add(new RecyclerViewFragment());
            fragments.add(new PerfilFragment());

            return fragments;
        }

        private void setUpViewPager(){
            viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
            tabLayout.setupWithViewPager(viewPager);

            tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_perfil);
        }

        public void agregarFab(){
            FloatingActionButton miBotonFA = (FloatingActionButton) findViewById(R.id.miActionButton);
            miBotonFA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_opciones, menu);
            return true;
        }

        public void enviarTokenDeRegistro(String token, String user) {
            Log.d("TOKEN", token);
            RestApiAdapter2 restApiAdapter2 = new RestApiAdapter2();
            EndPoints2 endPoints2 = restApiAdapter2.establecerConexionRestAPI();
            Call<UsuarioResponse> usuarioResponseCall = endPoints2.registrarTokenID(token, user);

            usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                @Override
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                    UsuarioResponse usuarioResponse = response.body();
                    Log.d("ID_FIREBASE", usuarioResponse.getId());
                    Log.d("TOKEN_FIREBASE", usuarioResponse.getToken());
                    Log.d("USUARIO", usuarioResponse.getUser());
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                }
            });
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.acercaDe:
                    Intent intent = new Intent(MainActivity.this, Programador.class);
                    startActivity(intent);
                    break;

                case R.id.mensaje:
                    Intent intent1 = new Intent(MainActivity.this, Mensaje.class);
                    startActivity(intent1);
                    break;

                case R.id.login:
                    Intent intent2 = new Intent(MainActivity.this, LogIn_Activity.class);
                    startActivity(intent2);
                    break;

                case R.id.notificacion:
                    String token = FirebaseInstanceId.getInstance().getToken();
                    String user = getIntent().getStringExtra("cuenta");
                    enviarTokenDeRegistro(token, user);
                    break;

            }

            return super.onOptionsItemSelected(item);
        }
    }
