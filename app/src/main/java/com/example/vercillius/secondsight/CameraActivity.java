package com.example.vercillius.secondsight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Window;
import android.view.WindowManager;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;

import java.util.List;

public class CameraActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    // A tag for log output
    private static final String TAG = CameraActivity.class.getSimpleName();

    // A key for storing the active camera index
    private static final String STATE_CAMERA_INDEX = "cameraIndex";

    // A key for storing active image size index
    private static final String STATE_IMAGE_SIZE_INDEX = "imageSizeIndex";

    // ID  for items in the image size submenu
    private static final int MENU_GROUP_ID_SIZE = 2;

    // The index of the active camera
    private int mCameraIndex;

    // The index of the active image size
    private int mImageSizeIndex;

    // Is the the camera front-facing
    private boolean mIsCameraFrontFacing;

    // The number if cameras on the device
    private int mNumCameras;

    // The camera view
    private CameraBridgeViewBase mCameraView;

    // The image sizes supported by the active camera
    private List<Size> mSupportedImageSizes;

    // Whether the next camera frame should be saved as a photo
    private boolean mIsPhotoPending;

    // Matrix to save the photos
    private Mat mBgr;

    // Whether a menu action is in progress.
    // Menu interaction should be disabled
    private boolean mIsMenuLocked;

    // The OpenCV loader callback
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(final int status) {
            switch (status){
                case BaseLoaderCallback.SUCCESS:
                    Log.d(TAG,"OpenCV loaded successfully");
                    mCameraView.enableView();
                    mBgr = new Mat();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // keep the screen on
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_camera);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        return null;
    }
}
