package com.example.farukov_pr_24;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    File directory;
    final int TYPE_PHOTO = 1;
    final int TYPE_VIDEO = 2;

    final int REQUEST_CODE_PHOTO = 1;
    final int REQUEST_CODE_VIDEO = 2;

    final String TAG = "myLogs";

    ImageView[] imgArray = new ImageView[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDirectory();

        imgArray[0] = (ImageView) findViewById(R.id.imageView1);
        imgArray[1] = (ImageView) findViewById(R.id.imageView2);
        imgArray[2] = (ImageView) findViewById(R.id.imageView3);
        imgArray[3] = (ImageView) findViewById(R.id.imageView4);
        imgArray[4] = (ImageView) findViewById(R.id.imageView5);
        imgArray[5] = (ImageView) findViewById(R.id.imageView6);
        imgArray[6] = (ImageView) findViewById(R.id.imageView7);
        imgArray[7] = (ImageView) findViewById(R.id.imageView8);

    }

    public void onClickPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_PHOTO));
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

//    public void onClickVideo(View view) {
//        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
////        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_VIDEO));
//        startActivityForResult(intent, REQUEST_CODE_VIDEO);
//    }

    int idIMG = 0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (intent == null) {
                    Log.d(TAG, "Intent is null");
                } else {
                    Log.d(TAG, "Photo uri: " + intent.getData());
                    Bundle bndl = intent.getExtras();
                    if (bndl != null) {
                        Object obj = intent.getExtras().get("data");
                        if (obj instanceof Bitmap) {
                            Bitmap bitmap = (Bitmap) obj;
                            Log.d(TAG, "bitmap " + bitmap.getWidth() + " x "
                                    + bitmap.getHeight());
                            imgArray[idIMG].setImageBitmap(bitmap);
                            idIMG += 1;

                            if (idIMG == 8) {
                                idIMG = 0;
                            }

//                            ImageView pic = new ImageView(this);
//                            pic.setImageBitmap(bitmap);
//                            pic.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
//                            pic.setAdjustViewBounds(true);
//                            pic.setScaleType(ImageView.ScaleType.FIT_XY);
//                            pic.setY(60);
//                            pic.setX(20);
//                            pic.setMaxHeight(250);
//                            pic.setMaxWidth(250);
//
//                            RelativeLayout relLayout = findViewById(R.id.relativeLayout);
//                            relLayout.addView(pic);

                        }
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "Canceled");
            }
        }

//        if (requestCode == REQUEST_CODE_VIDEO) {
//            if (resultCode == RESULT_OK) {
//                if (intent == null) {
//                    Log.d(TAG, "Intent is null");
//                } else {
//                    Log.d(TAG, "Video uri: " + intent.getData());
//                }
//            } else if (resultCode == RESULT_CANCELED) {
//                Log.d(TAG, "Canceled");
//            }
//        }
    }

//    private Uri generateFileUri(int type) {
//        File file = null;
//        switch (type) {
//            case TYPE_PHOTO:
//                file = new File(directory.getPath() + "/" + "photo_"
//                        + System.currentTimeMillis() + ".jpg");
//                break;
//            case TYPE_VIDEO:
//                file = new File(directory.getPath() + "/" + "video_"
//                        + System.currentTimeMillis() + ".mp4");
//                break;
//        }
//        Log.d(TAG, "fileName = " + file);
//        return Uri.fromFile(file);
//    }

    private void createDirectory() {
        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyFolder");
        if (!directory.exists())
            directory.mkdirs();
    }
//
//    public void insertIMG(Bitmap img) {
//        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
//
//        int idImg = idIMG(db);
//
//        byte[] data = getBitmapAsByteArray(img);
//
//        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER, data BLOB, UNIQUE(id))");
//        db.execSQL("INSERT OR IGNORE INTO users VALUES (" + idImg + ", " + data + ");");
//
//        db.close();
//    }
//
//    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
//        return outputStream.toByteArray();
//    }
//
//    public int idIMG(SQLiteDatabase db) {
//        Cursor query = db.rawQuery("SELECT * FROM users;", null);
//        int costUser = 0;
//        while(query.moveToNext()){
//            costUser += 1;
//        }
//        query.close();
//        return costUser+1;
//    }
//
//    public Bitmap getIMG(int i){
//
//        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
//
//        int idImg = idIMG(db) - 1;
//
//        String qu = "select img  from table where feedid=" + i ;
//        Cursor cur = db.rawQuery(qu, null);
//
//        if (cur.moveToFirst()){
//            byte[] imgByte = cur.getBlob(0);
//            cur.close();
//            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
//        }
//        if (cur != null && !cur.isClosed()) {
//            cur.close();
//        }
//
//        return null;
//    }

}