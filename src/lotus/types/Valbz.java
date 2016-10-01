package lotus.types;

import lotus.LotusClient;

public class Valbz extends Symbol {

    private static Valbz instance;

    public Valbz() {
        super("VALBZ");
    }

    @Override
    public void calc(LotusClient client) {
        
    }

    public static Valbz getInstance() {
        return instance == null ? instance = new Valbz() : instance;
    }
}
