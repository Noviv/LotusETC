package lotus;

import java.util.ArrayList;
import java.util.Map;

public class LotusUtil {

    private LotusUtil() {
    }

    public static <K, V> ArrayList<K> cvtMapToKeyArray(Map<K, V> map) {
        ArrayList<K> ret = new ArrayList<>();
        ret.addAll(map.keySet());
        return ret;
    }
}
