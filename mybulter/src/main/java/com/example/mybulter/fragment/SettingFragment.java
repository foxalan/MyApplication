package com.example.mybulter.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.mybulter.R;
import com.example.mybulter.adapter.SettingAdapter;
import com.example.mybulter.data.ListSource;
import com.example.mybulter.info.SettingInfo;
import com.example.mybulter.util.L;
import com.example.mybulter.util.UtilTools;
import com.example.mybulter.view.CustomDialog;
import com.example.mybulter.view.SetListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class SettingFragment extends Fragment {

    private CircleImageView cir_iv_view;
    private CustomDialog customDialog;
    private Button bt_add_camera;
    private Button bt_add_photos;
    private Button bt_cancel;
    private ButtonClick buttonClick;

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int PHOTO_REQUEST_CODE = 2;

    private SetListView setListView;

    private List<SettingInfo> settingInfoList = new ArrayList<>();
    private SettingAdapter adapter;

    private String fileName = "icon.png";
    private String filePath;

    private File file;

    private FileInputStream fis;
    private Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);

        initViews(view);
        initData();
        initEvent();

        return view;

    }

    private void initViews(View view) {

        cir_iv_view = (CircleImageView) view.findViewById(R.id.civ_user_view);
        customDialog = new CustomDialog(getContext(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.layout.dialog_add_pic, R.style.pop_anim_style, Gravity.BOTTOM, R.anim.pop_in);

        bt_add_camera = (Button) customDialog.findViewById(R.id.bt_add_camera);
        bt_add_photos = (Button) customDialog.findViewById(R.id.bt_add_photos);
        bt_cancel = (Button) customDialog.findViewById(R.id.bt_cancel);

        setListView = (SetListView) view.findViewById(R.id.lv_set_setting);

        buttonClick = new ButtonClick();
    }

    private void initData() {
        settingInfoList = new ListSource(getContext()).getAllData();
        adapter = new SettingAdapter(getContext(), settingInfoList);

    }


    private void initEvent() {
        cir_iv_view.setOnClickListener(buttonClick);
        bt_add_camera.setOnClickListener(buttonClick);
        bt_add_photos.setOnClickListener(buttonClick);
        bt_cancel.setOnClickListener(buttonClick);

        setListView.setAdapter(adapter);

        setListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //         MyToast.showMessage("点击了第" + position + "页");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        UtilTools.putImageToShare(getContext(), cir_iv_view);
        file = new File(getActivity().getCacheDir(), fileName);
        if (file.exists()) {
            L.d(file.getAbsolutePath());
            try {
                fis = new FileInputStream(file.getName());
                bitmap = BitmapFactory.decodeStream(fis);
                cir_iv_view.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.civ_user_view:
                    customDialog.show();
                    break;
                case R.id.bt_add_camera:
                    getPicFromCamera();
                    break;
                case R.id.bt_add_photos:
                    getPicFromPhotos();
                    //        toPhoneNumber();
                    break;
                case R.id.bt_cancel:
                    customDialog.dismiss();
                    break;
                default:
                    break;

            }
        }
    }


    private void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        customDialog.dismiss();
    }

    private void getPicFromPhotos() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_CODE);
        customDialog.dismiss();
    }

    public static final int RESULT_REQUEST_CODE = 102;
    public static final int PHONE_NUMBER_REQUEST_CODE = 103;
    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    private File tempFile = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
                    startPhotoZoom(Uri.fromFile(tempFile));
                    break;
                case PHOTO_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case RESULT_REQUEST_CODE:
                    //有可能点击舍弃
                    if (data != null) {
                        //拿到图片设置
                        setImageToView(data);
                        //既然已经设置了图片，我们原先的就应该删除
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
                case PHONE_NUMBER_REQUEST_CODE:
                    String[] selections = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.Contacts.DISPLAY_NAME};
                    Uri uri = data.getData();
                    Cursor cursor = getActivity().getContentResolver().query(uri, selections, null, null, null);
                    cursor.moveToFirst();
                    String phone_name = cursor.getColumnName(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String phone_number = cursor.getColumnName(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    cursor.close();
                    L.d(phone_name);
                    L.d(phone_number);
                    break;
                default:
                    break;
            }

        }
    }


    //裁剪
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            L.d("uri == null");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        //裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        //发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    //设置图片
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            file = new File(getActivity().getCacheDir(), fileName);
            L.d(file.getAbsolutePath());

//            if (file.exists()) {
//                file.delete();
//                L.d("delete");
//            }else {
//            }

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cir_iv_view.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UtilTools.putImageToShare(getActivity(), cir_iv_view);
    }

    private void toPhoneNumber() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PHONE_NUMBER_REQUEST_CODE);
    }
}
