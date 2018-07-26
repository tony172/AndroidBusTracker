package com.example.toni.gpssimulator;

import android.app.Application;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.support.v4.text.util.LinkifyCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private List<Location> b1List;
    private List<Location> b2List;
    private DatabaseReference ref;
    private Bus b1, b2, b3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1List = new ArrayList<>();
        b2List = new ArrayList<>();
        b1List.add(new Location(43.510808, 16.438490));
        b1List.add(new Location(43.511038, 16.438194));
        b1List.add(new Location(43.510869, 16.437875));
        b1List.add(new Location(43.510644, 16.438155));
        b1List.add(new Location(43.510450, 16.438561));
        b1List.add(new Location(43.510412, 16.438929));
        b1List.add(new Location(43.510484, 16.439294));
        b1List.add(new Location(43.510550, 16.439738));
        b1List.add(new Location(43.510699, 16.440121));
        b1List.add(new Location(43.510835, 16.440553));
        b1List.add(new Location(43.510950, 16.440994));
        b1List.add(new Location(43.510988, 16.441364));
        b1List.add(new Location(43.511252, 16.441528));
        b1List.add(new Location(43.511560, 16.441730));
        b1List.add(new Location(43.511891, 16.441930));
        b1List.add(new Location(43.512194, 16.442185));
        b1List.add(new Location(43.512373, 16.442674));
        b1List.add(new Location(43.512382, 16.443334));
        b1List.add(new Location(43.512310, 16.443907));
        b1List.add(new Location(43.512182, 16.444522));
        b1List.add(new Location(43.512073, 16.444984));
        b1List.add(new Location(43.511970, 16.445472));
        b1List.add(new Location(43.511782, 16.446054));
        b1List.add(new Location(43.511563, 16.446580));
        b1List.add(new Location(43.511445, 16.447209));
        b1List.add(new Location(43.511367, 16.447632));
        b1List.add(new Location(43.511336, 16.448333));
        b1List.add(new Location(43.511291, 16.449029));
        b1List.add(new Location(43.511231, 16.449620));
        b1List.add(new Location(43.511190, 16.450190));
        b1List.add(new Location(43.511142, 16.450788));
        b1List.add(new Location(43.511096, 16.451360));
        b1List.add(new Location(43.511059, 16.452034));
        b1List.add(new Location(43.511063, 16.452701));
        b1List.add(new Location(43.511116, 16.453414));
        b1List.add(new Location(43.511209, 16.454075));
        b1List.add(new Location(43.511290, 16.454758));
        b1List.add(new Location(43.511342, 16.455227));
        b1List.add(new Location(43.511444, 16.455849));
        b1List.add(new Location(43.511587, 16.456570));
        b1List.add(new Location(43.511676, 16.457178));
        b1List.add(new Location(43.511783, 16.457769));
        b1List.add(new Location(43.511876, 16.458399));
        b1List.add(new Location(43.511977, 16.459010));
        b1List.add(new Location(43.512062, 16.459638));
        b1List.add(new Location(43.512189, 16.460196));
        b1List.add(new Location(43.512300, 16.460834));
        b1List.add(new Location(43.512424, 16.461596));
        b1List.add(new Location(43.512553, 16.462520));
        b1List.add(new Location(43.512657, 16.463124));
        b1List.add(new Location(43.512767, 16.463675));
        b1List.add(new Location(43.512866, 16.464224));
        b1List.add(new Location(43.512942, 16.464802));
        b1List.add(new Location(43.512995, 16.465198));
        b1List.add(new Location(43.513094, 16.465797));
        b1List.add(new Location(43.513207, 16.466449));
        b1List.add(new Location(43.513319, 16.467162));
        b1List.add(new Location(43.513388, 16.467755));
        b1List.add(new Location(43.513493, 16.468376));
        b1List.add(new Location(43.513598, 16.468941));
        b1List.add(new Location(43.513644, 16.469439));
        b1List.add(new Location(43.513734, 16.470073));
        b1List.add(new Location(43.513849, 16.470759));
        b1List.add(new Location(43.513936, 16.471448));
        b1List.add(new Location(43.514036, 16.472104));
        b1List.add(new Location(43.514154, 16.472796));
        b1List.add(new Location(43.514278, 16.473486));
        b1List.add(new Location(43.514407, 16.474073));
        b1List.add(new Location(43.514540, 16.474709));
        b1List.add(new Location(43.514767, 16.475414));
        b1List.add(new Location(43.514922, 16.476001));
        b1List.add(new Location(43.515171, 16.476637));
        b1List.add(new Location(43.515385, 16.477251));
        b1List.add(new Location(43.515595, 16.477949));
        b1List.add(new Location(43.515756, 16.478705));
        b1List.add(new Location(43.515843, 16.479153));
        b1List.add(new Location(43.515975, 16.480006));
        b1List.add(new Location(43.516140, 16.480774));
        b1List.add(new Location(43.516285, 16.481678));
        b1List.add(new Location(43.516433, 16.482463));
        b1List.add(new Location(43.516549, 16.483277));
        b1List.add(new Location(43.516690, 16.484161));
        b1List.add(new Location(43.516751, 16.485036));
        b1List.add(new Location(43.516689, 16.485705));
        b1List.add(new Location(43.516597, 16.486358));
        b1List.add(new Location(43.516455, 16.487019));
        b1List.add(new Location(43.516230, 16.487774));
        b1List.add(new Location(43.516115, 16.488484));
        b1List.add(new Location(43.515939, 16.489174));
        b1List.add(new Location(43.515806, 16.489892));
        b1List.add(new Location(43.515679, 16.490515));
        b1List.add(new Location(43.515513, 16.491110));
        b1List.add(new Location(43.515354, 16.491725));

        b2List.add(new Location(43.510754, 16.438359));
        b2List.add(new Location(43.511058, 16.438134));
        b2List.add(new Location(43.510723, 16.437855));
        b2List.add(new Location(43.510490, 16.438478));
        b2List.add(new Location(43.510443, 16.439164));
        b2List.add(new Location(43.510591, 16.439786));
        b2List.add(new Location(43.510785, 16.440377));
        b2List.add(new Location(43.510964, 16.440967));
        b2List.add(new Location(43.510521, 16.441128));
        b2List.add(new Location(43.510093, 16.441374));
        b2List.add(new Location(43.509719, 16.441793));
        b2List.add(new Location(43.509392, 16.442168));
        b2List.add(new Location(43.509104, 16.442769));
        b2List.add(new Location(43.508809, 16.443166));
        b2List.add(new Location(43.508326, 16.442823));
        b2List.add(new Location(43.507953, 16.442533));
        b2List.add(new Location(43.507533, 16.442394));
        b2List.add(new Location(43.506933, 16.442565));
        b2List.add(new Location(43.506731, 16.443370));
        b2List.add(new Location(43.506490, 16.444260));
        b2List.add(new Location(43.506319, 16.445172));
        b2List.add(new Location(43.506140, 16.446095));
        b2List.add(new Location(43.505945, 16.447125));
        b2List.add(new Location(43.505782, 16.448026));
        b2List.add(new Location(43.505603, 16.448831));
        b2List.add(new Location(43.505509, 16.449754));
        b2List.add(new Location(43.505494, 16.450751));
        b2List.add(new Location(43.505447, 16.451824));
        b2List.add(new Location(43.505292, 16.452779));
        b2List.add(new Location(43.505058, 16.453734));
        b2List.add(new Location(43.504941, 16.454700));
        b2List.add(new Location(43.505074, 16.455815));
        b2List.add(new Location(43.505237, 16.456963));
        b2List.add(new Location(43.505447, 16.458079));
        b2List.add(new Location(43.505618, 16.459120));
        b2List.add(new Location(43.505844, 16.460386));
        b2List.add(new Location(43.506023, 16.461523));
        b2List.add(new Location(43.506202, 16.462617));
        b2List.add(new Location(43.506381, 16.463765));
        b2List.add(new Location(43.506747, 16.464549));
        b2List.add(new Location(43.507385, 16.464420));
        b2List.add(new Location(43.508132, 16.464216));
        b2List.add(new Location(43.508848, 16.463926));
        b2List.add(new Location(43.509548, 16.463690));
        b2List.add(new Location(43.509719, 16.464613));
        b2List.add(new Location(43.509859, 16.465514));
        b2List.add(new Location(43.510023, 16.466480));
        b2List.add(new Location(43.510163, 16.467284));
        b2List.add(new Location(43.510093, 16.468250));
        b2List.add(new Location(43.509945, 16.469205));
        b2List.add(new Location(43.510085, 16.470074));
        b2List.add(new Location(43.510233, 16.471029));
        b2List.add(new Location(43.510404, 16.471984));
        b2List.add(new Location(43.510560, 16.472917));
        b2List.add(new Location(43.511026, 16.473614));
        b2List.add(new Location(43.511626, 16.473078));
        b2List.add(new Location(43.512217, 16.472552));
        b2List.add(new Location(43.512909, 16.471973));
        b2List.add(new Location(43.513547, 16.471426));
        b2List.add(new Location(43.513991, 16.471748));
        b2List.add(new Location(43.514154, 16.472778));
        b2List.add(new Location(43.514325, 16.473808));
        b2List.add(new Location(43.514559, 16.474795));
        b2List.add(new Location(43.514831, 16.475717));
        b2List.add(new Location(43.515065, 16.476307));
        b2List.add(new Location(43.515601, 16.477992));
        b2List.add(new Location(43.515788, 16.479043));
        b2List.add(new Location(43.516201, 16.479966));
        b2List.add(new Location(43.516737, 16.479708));
        b2List.add(new Location(43.517033, 16.478861));
        b2List.add(new Location(43.517088, 16.477917));
        b2List.add(new Location(43.517570, 16.477101));
        b2List.add(new Location(43.518037, 16.476468));
        b2List.add(new Location(43.518255, 16.475599));
        b2List.add(new Location(43.518262, 16.474698));
        b2List.add(new Location(43.518138, 16.473700));
        b2List.add(new Location(43.517757, 16.473046));
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        ref = firebase.getReference("buses");
        b1 = new Bus(ref.push().getKey(), new Location(0, 0), 6, 0);
        b2 = new Bus(ref.push().getKey(), new Location(0, 0), 6, 0);
        b3 = new Bus(ref.push().getKey(), new Location(0, 0), 11, 0);
        b4 = new Bus(ref.push().getKey(), new Location(0, 0), 11, 0);
        ref.child(b1.getKey()).setValue(b1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int delay = 0, s = 0;
                for (int i = 0; i < b1List.size(); i++) {
                    s = new Random().nextInt(80);
                    if (s >= 50) {
                        delay = 2500;
                    } else if (s < 0 && s != 0) {
                        delay = 5000;
                    } else {
                        delay = 10000;
                    }

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ref.child(b1.getKey()).setValue(new Bus(b1.getKey(), b1List.get(i), b1.getLineNumber(), s));
                }


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int size = b1List.size();
                int delay = 0, s = 0;
                for (int i = 0; i < size; i++) {
                    s = new Random().nextInt(80);
                    if (s >= 50) {
                        delay = 2500;
                    } else if (s < 0 && s != 0) {
                        delay = 5000;
                    } else {
                        delay = 10000;
                    }

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ref.child(b2.getKey()).setValue(new Bus(b2.getKey(), b1List.get(size - 1 - i), b2.getLineNumber(), s));
                }


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int delay = 0, s = 0;
                for (int i = 0; i < b2List.size(); i++) {
                    s = new Random().nextInt(80);
                    if (s >= 50) {
                        delay = 2500;
                    } else if (s < 0 && s != 0) {
                        delay = 5000;
                    } else {
                        delay = 10000;
                    }

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ref.child(b3.getKey()).setValue(new Bus(b3.getKey(), b2List.get(i), b3.getLineNumber(), s));
                }


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int delay = 0, s = 0;
                int size = b2List.size();
                for (int i = 0; i < size; i++) {
                    s = new Random().nextInt(80);
                    if (s >= 50) {
                        delay = 2500;
                    } else if (s < 0 && s != 0) {
                        delay = 5000;
                    } else {
                        delay = 10000;
                    }

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ref.child(b4.getKey()).setValue(new Bus(b4.getKey(), b2List.get(size - 1 - i), b4.getLineNumber(), s));
                }


            }
        }).start();

    }

}
