package com.my.package;

import android.support.annotation.Nullable;

import android.content.Context;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import com.facebook.react.modules.core.DeviceEventManagerModule;

public class BridgeModule extends ReactContextBaseJavaModule {
    private static ReactContext mReactContext;

    public static int duration;
    public static boolean isPlaying;

    public BridgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "BridgeModule";
    }

    @ReactMethod
    public void showFullscreen(String videoUri, int duraitonToSeek, boolean isVideoPlaying) {
        duration = duraitonToSeek;
        isPlaying = isVideoPlaying;
        Context context = getReactApplicationContext();
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra("VIDEO_URL", videoUri);
        getCurrentActivity().startActivity(intent);
    }

    public static void sendCurrentPosition(int currentPosition) {
        WritableMap params = Arguments.createMap();
        params.putInt("currentPosition", (int) currentPosition);
        
        mReactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit("sendCurrentPosition", params);
    }
}
