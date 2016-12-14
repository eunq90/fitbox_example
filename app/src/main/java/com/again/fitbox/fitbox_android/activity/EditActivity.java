package com.again.fitbox.fitbox_android.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.again.fitbox.fitbox_android.R;
import com.again.fitbox.fitbox_android.database.dao.BoardDataDao;
import com.again.fitbox.fitbox_android.model.Aticle;
import com.again.fitbox.fitbox_android.model.Board;
import com.again.fitbox.fitbox_android.util.EmoticonHandler;
import com.again.fitbox.fitbox_android.util.SimpleDB;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvSubject;
    private TextView tvArticleNumber;
    private TextView tvAuthor;
    private EditText etDescription;
    private EditText etTest;
    private Button tagpin;

    private BoardDataDao boarddao;

    private EmoticonHandler mEmoticonHandler;

    private PopupWindow popupWindow;
    private Uri mImageCaptureUri;


    private String absoultePath;
    private int id_view;


    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE = 2;
    private static final int PICK_FROM_CAMERA = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);



        tvSubject = (TextView) findViewById(R.id.tvSubject);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        etDescription = (EditText) findViewById(R.id.etDescription);
        tagpin = (Button) findViewById(R.id.tagpin);
        etTest = (EditText) findViewById(R.id.etTest);

        Intent intent = getIntent();


        String key = intent.getStringExtra("key");
        boarddao = new BoardDataDao(getApplicationContext());
        Board board = boarddao.getBoard(key);

        tvSubject.setText(board.getDate());
        etDescription.setText(board.getDate());

        mEmoticonHandler = new EmoticonHandler(etDescription);

        tagpin.setOnClickListener(this);

        etDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                hideSoftInputWindow(view,b);
                Log.d("etDesc Focus : ",b+"");
            }
        });


/*

        tagpin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //imoButtonClick(view);
                  mEmoticonHandler.insert("<tagpin>",R.drawable.cloud);
              }
          });

*/

    }



    @Override
    public void onClick(View view) {

        id_view = view.getId();
        Log.d("onClick : ","테그핀");

        if(view.getId() == R.id.tagpin){

            Log.d("onClick : ","테그핀");

            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    doTakeAlbumAction();

                }
            };

            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    doTakePhotoAction();

                }
            };


            DialogInterface.OnClickListener cancelListner = new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            };

            new AlertDialog.Builder(this).setTitle("업로드할 이미지 선택")
                    .setNeutralButton("사진 촬영",cameraListener)
                    .setPositiveButton("앨범 열기",albumListener)
                    .setNegativeButton("취소",cancelListner)
                    .show();



        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode != RESULT_OK) {
            return ;
        }

        switch(requestCode)
        {
            case PICK_FROM_ALBUM : {
                //mImageCaptureUri = data.getClipData();
                ClipData cd = data.getClipData();
                mImageCaptureUri = cd.getItemAt(1).getUri();


                Log.d("카메라 앨범", "앨범처리");

            }

            case PICK_FROM_CAMERA:
            {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri,"image/*");

                intent.putExtra("outputX",200);
                intent.putExtra("outputY",200);
                intent.putExtra("aspectX",1);
                intent.putExtra("aspectY",1);
                intent.putExtra("scale",true);
                intent.putExtra("return-data",true);
                startActivityForResult(intent,CROP_FROM_IMAGE);
                break;

            }
            case CROP_FROM_IMAGE:
            {
                if(resultCode != RESULT_OK){
                    return;
                }

                final Bundle extras = data.getExtras();

                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Fitbox/"+System.currentTimeMillis()+".jpg";

                if(extras != null){

                    Bitmap photo = extras.getParcelable("data");

                    storeCropImage(photo,filePath);
                    absoultePath = filePath;
                    break;
                }

            }
        }
    }

    private void storeCropImage(Bitmap bitmap,String filePath){

        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Fitbox";
        File directory_Fitbox = new File(dirPath);


        if(!directory_Fitbox.exists())
            directory_Fitbox.mkdir();

        File copyFile = new File(filePath);
        BufferedOutputStream out = null;


        try {

            copyFile.createNewFile();
            out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);

            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.fromFile(copyFile)));

            out.flush();
            out.close();



        }catch (Exception e ){
            e.printStackTrace();
        }

    }

    public void imoButtonClick(View v){
        int start = etDescription.getSelectionStart();
        etDescription.append("<tagpin>");
        int end = etDescription.getSelectionEnd();

        Spannable span = etDescription.getText();
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.cloud);
        span.setSpan(new ImageSpan(bm),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    public void doTakeAlbumAction(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent,PICK_FROM_ALBUM);
    }

    public void doTakePhotoAction(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String url = "tmp_" + String.valueOf(System.currentTimeMillis())+".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),url));

        intent.putExtra(MediaStore.EXTRA_OUTPUT,mImageCaptureUri);
        startActivityForResult(intent,PICK_FROM_CAMERA);

    }

    public boolean hideSoftInputWindow(View edit_view, boolean bState) {

        InputMethodManager imm = (InputMethodManager) getSystemService
                (Context.INPUT_METHOD_SERVICE);

        if ( bState )
            return imm.showSoftInput(edit_view, 0);
        else
            return imm.hideSoftInputFromWindow
                    (edit_view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }
}
