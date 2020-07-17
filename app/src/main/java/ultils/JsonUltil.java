package ultils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class JsonUltil<T> {
    public static final <T> List<T> converToArr( final String json,final Class<T[]> clazz)
    {
        final T[] jsonToObject = new Gson().fromJson(json, clazz);
        return Arrays.asList(jsonToObject);
    }
    public  static String getJson(Object o){
        Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(o);
        return json;
    }
}
