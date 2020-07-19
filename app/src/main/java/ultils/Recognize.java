package ultils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import data.model.ImageDto;
import edmt.dev.edmtdevcognitiveface.Contract.CreatePersonResult;
import edmt.dev.edmtdevcognitiveface.Contract.Face;
import edmt.dev.edmtdevcognitiveface.Contract.FaceRectangle;
import edmt.dev.edmtdevcognitiveface.Contract.IdentifyResult;
import edmt.dev.edmtdevcognitiveface.Contract.Person;
import edmt.dev.edmtdevcognitiveface.Contract.TrainingStatus;
import edmt.dev.edmtdevcognitiveface.FaceServiceClient;
import edmt.dev.edmtdevcognitiveface.FaceServiceRestClient;
import edmt.dev.edmtdevcognitiveface.Rest.ClientException;

public class Recognize {

    private static String API_KEY = "9a4e889caa9c49f3b6a36924e737b570";
    private static String API_LINK = "https://facepj21.cognitiveservices.azure.com/face/v1.0";

    private static FaceServiceClient faceServiceClient = new FaceServiceRestClient(API_LINK, API_KEY);
    // biến này là nhóm người dùng đã tạo trước
    public static String personGroupID = "abc001";

    public static String personName = "Trần Đình Đức";

    //mảng các khuôn mặt được phát hiện , phục vụ cho việc nhận dạng
    public static Face[] faceDetected;

    // 2 ArrayList lưu trữ nhóm nhân viên được phát hiện từ người quản lí
    public static List<String> list1 = new ArrayList<>();
    public static List<Bitmap> listBitMap_crop = new ArrayList<>();


    public static class detectTask extends AsyncTask<Bitmap, String, Face[]> {
        @Override
        protected Face[] doInBackground(Bitmap... bitmaps) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmaps[0].compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

                Face[] result = faceServiceClient.detect(inputStream, true, false, null);

                if (result == null) {
                    return null;
                } else {
                    // lưu list khuôn mặt sau khi crop sát mặt !
                    get_face_crop(bitmaps[0], result);
                    return result;
                }
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Face[] faces) {
            if (faces.length == 0) {
                Log.v("TAG", "No face Detected");
            } else {
                // trả về các khuôn mặt phát hiện

                faceDetected = faces;
                Log.v("TAG", String.valueOf(faces.length));

            }
        }
    }


public static class TrainTask extends AsyncTask<Void, String, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            faceServiceClient.trainPersonGroup(personGroupID);
            TrainingStatus trainingStatus = faceServiceClient.getPersonGroupTrainingStatus(personGroupID);
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.v("TAG", "Traning sucess");
    }
}

public static class AddPersonToGroup extends AsyncTask<ImageDto, String, Void> {

    @Override
    protected Void doInBackground(ImageDto... bitmaps) {
        try {
            faceServiceClient.getPersonGroup(personGroupID);
            CreatePersonResult personResult = faceServiceClient.createPerson(personGroupID, bitmaps[0].getName(), null);

            for (int i = 0; i < bitmaps.length; i++) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmaps[0].getImage().compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                ByteArrayInputStream s = new ByteArrayInputStream(outputStream.toByteArray());
                faceServiceClient.addPersonFace(personGroupID, personResult.personId, s, null, null);
            }
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        new TrainTask().execute();
    }

}

public static void get_face_crop(Bitmap bitmap, Face[] faces){
        listBitMap_crop.clear();
        if(faces!=null){
        for(Face face:faces){
        UUID[]faceId=new UUID[1];
        faceId[0]=face.faceId;
        FaceRectangle faceRectangle=face.faceRectangle;
        Bitmap bitmap4=Bitmap.createBitmap(bitmap,faceRectangle.left,faceRectangle.top,
        faceRectangle.width,faceRectangle.height);
        try{
        IdentifyResult[]result=faceServiceClient.identity(personGroupID,faceId,1);
        if(result[0].candidates.size()==0){
        listBitMap_crop.add(bitmap4);
        list1.add("null");
        }else{
        listBitMap_crop.add(bitmap4);
        Person person=faceServiceClient.getPerson(personGroupID,result[0].candidates.get(0).personId);
        list1.add(person.name);
        }
        }catch(ClientException e){
        e.printStackTrace();
        }catch(IOException e){
        e.printStackTrace();
        }
        }
        }else{
        Log.v("tag_test_face","no face to add !");
        }
        }
        }
