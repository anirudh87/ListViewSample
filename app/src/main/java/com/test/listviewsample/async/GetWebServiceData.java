package com.test.listviewsample.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.test.listviewsample.interfaces.CallBack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class GetWebServiceData extends AsyncTask<String, Void, String> {

    String url;
    Context context;
    CallBack callbackObj;

    int tasksID;

    public GetWebServiceData(Context contextObj, String Url, CallBack listnerObj, int tasksID) {
        this.context = contextObj;
        this.url = Url;
        this.callbackObj = listnerObj;
        this.tasksID = tasksID;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inStream = openHttpConnection(this.url);
        String result = null;
        try {
            if (inStream != null) {
                result = convertInputStreamToString(inStream);
            }
        } catch (Exception e) {
            Log.e("Error", "Conversion error in Input Stream");
        }
        return result;
    }

    private InputStream openHttpConnection(String urlStr) {
        InputStream in;
        try {
            URL url = new URL(urlStr);
            URLConnection urlConnection = url.openConnection();
            in = urlConnection.getInputStream();
            return in;
        } catch (MalformedURLException e) {
            Log.e("MalformedURLException", ">" + e);
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("IOException", ">" + e);
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("Exception", ">" + e);
            e.printStackTrace();
        }
        return null;
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        inputStream.close();

        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        callbackObj.onResult(result, tasksID);
    }
}
