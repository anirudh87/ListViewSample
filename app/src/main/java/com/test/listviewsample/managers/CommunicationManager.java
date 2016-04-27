package com.test.listviewsample.managers;

import android.content.Context;

import com.test.listviewsample.async.GetWebServiceData;
import com.test.listviewsample.interfaces.CallBack;


public class CommunicationManager {

    private Context context;
    GetWebServiceData gwsdObj;

    public CommunicationManager(Context contextObj) {
        this.context = contextObj;
    }

    public void callWebService(Context contextObj, String Url, CallBack listnerObj, int tasksID) {
        gwsdObj = new GetWebServiceData(contextObj, Url, listnerObj, tasksID);
        gwsdObj.execute();
    }

    public void cancelWebServiceCall() {
        if(!gwsdObj.isCancelled()) {
            gwsdObj.cancel(true);
        }
    }
    
    
}
