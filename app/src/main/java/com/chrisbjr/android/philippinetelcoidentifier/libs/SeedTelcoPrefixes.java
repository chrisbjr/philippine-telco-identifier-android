package com.chrisbjr.android.philippinetelcoidentifier.libs;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.chrisbjr.android.philippinetelcoidentifier.R;
import com.chrisbjr.android.philippinetelcoidentifier.models.Prefix;
import com.chrisbjr.android.philippinetelcoidentifier.models.Telco;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chrisbjr on 8/17/14.
 */
public class SeedTelcoPrefixes {

    private final Context context;
    private final SeedTelcoPrefixesInterface seedTelcoPrefixesInterface;

    public SeedTelcoPrefixes(Context context, SeedTelcoPrefixesInterface seedTelcoPrefixesInterface) {
        this.context = context;
        this.seedTelcoPrefixesInterface = seedTelcoPrefixesInterface;
    }

    public void execute() {
        seedTelcoPrefixesInterface.onStart();
        String telcoPrefixesDataString = readTxt(R.raw.telco_prefixes);
        JsonArray telcoPrefixesData = new JsonParser().parse(telcoPrefixesDataString.trim()).getAsJsonArray();

        for (int i = 0; i < telcoPrefixesData.size(); i++) {
            JsonObject telcoPrefixes = telcoPrefixesData.get(i).getAsJsonObject();
            ActiveAndroid.beginTransaction();
            try {
                Telco telco = new Telco();
                telco.name = telcoPrefixes.get("telco").getAsString();
                telco.save();
                JsonArray telcoPrefixesArray = telcoPrefixes.get("prefixes").getAsJsonArray();
                for (int j = 0; j < telcoPrefixesArray.size(); j++) {
                    Prefix prefix = new Prefix();
                    prefix.telco = telco;
                    prefix.prefix = telcoPrefixesArray.get(j).getAsString();
                    prefix.save();
                }
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
        }
        seedTelcoPrefixesInterface.onFinish();
    }

    private String readTxt(int rawResource) {

        InputStream inputStream = context.getResources().openRawResource(rawResource);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }

    public interface SeedTelcoPrefixesInterface {
        public void onStart();

        public void onFinish();
    }
}
