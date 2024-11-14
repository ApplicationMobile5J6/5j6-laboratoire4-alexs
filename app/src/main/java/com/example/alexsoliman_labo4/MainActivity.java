package com.example.alexsoliman_labo4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class MainActivity extends AppCompatActivity {
    Button btn_choix;
    EditText et_nom, et_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_choix = findViewById(R.id.btn_choix);
        et_nom = findViewById(R.id.et_nom);
        et_phone = findViewById(R.id.et_phone);
        btn_choix.setOnClickListener(v -> {

            String nom = et_nom.getText().toString().trim();
            String phone = et_phone.getText().toString().trim();

            // Validate the input
            if (valider(nom, phone)) {
                try {
                    parseXML();  // Call the XML parsing method from previous code
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void parseXML() {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream in = getAssets().open("menu.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);

            lectureAffichageXML(parser);

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    private void lectureAffichageXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Repas> repasList = new ArrayList<>();
        int eventType = parser.getEventType();
        Repas repas_courant = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String element = null;
            if (eventType == XmlPullParser.START_TAG) {
                element = parser.getName();

                if ("repas".equals(element)) {
                    repas_courant = new Repas();
                    repasList.add(repas_courant);
                } else if (repas_courant != null) {
                    if ("noRepas".equals(element)) {
                        repas_courant.setNoRepas(Integer.parseInt(parser.nextText()));
                    } else if ("nom".equals(element)) {
                        repas_courant.setNom(parser.nextText());
                    } else if ("description".equals(element)) {
                        repas_courant.setDescription(parser.nextText());
                    } else if ("categorie".equals(element)) {
                        repas_courant.setCategorie(parser.nextText());
                    } else if ("prix".equals(element)) {
                        repas_courant.setPrix(Double.parseDouble(parser.nextText()));
                    }
                }
            }
            eventType = parser.next();
        }

        Intent intent = new Intent(MainActivity.this, InterfaceCommande.class);
        intent.putParcelableArrayListExtra("repasList", repasList);
        startActivity(intent);

    }


    private boolean valider(String nom, String phone) {
        if (nom.isEmpty()) {
            et_nom.setError("Nom doit etre rempli");
            return false;
        }

        if (phone.isEmpty()) {
            et_phone.setError("Telephone doit etre rempli");
            return false;
        }


        if (!phone.matches("\\d{10}")) {
            et_phone.setError("Entrez un numero valide");
            return false;
        }

        return true;
    }

}