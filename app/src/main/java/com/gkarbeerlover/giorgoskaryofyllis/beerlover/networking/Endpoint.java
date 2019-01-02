package com.gkarbeerlover.giorgoskaryofyllis.beerlover.networking;


import java.util.concurrent.ThreadLocalRandom;

public class Endpoint {

    private static final String BASE_URL = "https://api.punkapi.com/v2/";
    private static final String BEERS_URL = BASE_URL + "beers?page=";
    public static final String SEARCH_URL = BASE_URL + "beers?beer_name=";

    public static String buildUrl() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        return BEERS_URL + randomNum;
    }

}
