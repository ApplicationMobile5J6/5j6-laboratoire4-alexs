package com.example.alexsoliman_labo4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class InterfaceCommande extends AppCompatActivity {

    private RepasViewModel repasViewModel;
    private String phone;
    private int noCommande;
    NotificationManager mng_notification;
    private Repas selectedRepas;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.interface_commande);
        mng_notification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        repasViewModel = new ViewModelProvider(this).get(RepasViewModel.class);
        ArrayList<Repas> repasList = getIntent().getParcelableArrayListExtra("repasList");
        MainFragment fragment = MainFragment.newInstance(repasList);
        SecondaryFragment otherFragment = new SecondaryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fr_fragment, fragment);
        fragmentTransaction.replace(R.id.sec_fragment, otherFragment);
        fragmentTransaction.commit();
        Button btn_commande = findViewById(R.id.btn_commander);
        TextView tv_nom = findViewById(R.id.tv_nomclient);
        TextView tv_phone = findViewById(R.id.tv_telephone);
        String nom = getIntent().getStringExtra("nom");
        phone = getIntent().getStringExtra("phone");

        repasViewModel.getSelectedRepas().observe(this, new Observer<Repas>() {
            @Override
            public void onChanged(Repas repas) {
                selectedRepas = repas;
            }
        });
        creationCanalNotification("com.example.notification",
                "Commande Notification",
                "Notification de commande");

        btn_commande.setOnClickListener(v -> {

            if (selectedRepas != null) {
            Commande commande = new Commande(noCommande, nom, phone, selectedRepas.getNoRepas(), selectedRepas.getNom(),
                    selectedRepas.getPrix()
            );
                    noCommande++;

                String message = "Votre commande : " + selectedRepas.getCategorie() + ", " + selectedRepas.getNom() + ".";
                envoyerMessage(phone, message);
            } else {
                Toast.makeText(this, "Aucune commande sélectionnée.", Toast.LENGTH_LONG).show();
            }
        });

        if (nom != null) {
            tv_nom.setText(nom);
        }
        if (phone != null) {
            tv_phone.setText(phone);
        }



    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void creationCanalNotification(String id, String nom, String description) {
        int priorite = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel canal = new NotificationChannel(id, nom, priorite);

        canal.setDescription(description);
        canal.enableLights(true);
        canal.setLightColor(Color.RED);

        mng_notification.createNotificationChannel(canal);
    }

    private void envoyerMessage(String phone, String message) {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(this, "Message envoyé.", Toast.LENGTH_LONG).show();
        }

    }
