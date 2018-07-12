package service;

public class OfferService {

    private static OfferService instance;

    public static OfferService getInstance() {
        if (instance == null) {
            instance = new OfferService();
        }
        return instance;
    }


}
