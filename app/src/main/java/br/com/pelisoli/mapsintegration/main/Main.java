package br.com.pelisoli.mapsintegration.main;

import android.app.Application;

import br.com.pelisoli.mapsintegration.requests.IService;
import br.com.pelisoli.mapsintegration.requests.Service;

/**
 * Created by pelisoli on 25/11/15.
 */
public class Main extends Application {

    private static IService service;

    public static IService getServiceAPI(){
        if(service == null){
            service = new Service();
        }

        return service;
    }

}
