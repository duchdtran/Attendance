package data.network;

public interface Callback {
    void getRespone(Object response, int stt);
    void getError(Object error,int stt);
}
