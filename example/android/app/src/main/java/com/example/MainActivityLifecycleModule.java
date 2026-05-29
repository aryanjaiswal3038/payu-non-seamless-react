package com.example;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class MainActivityLifecycleModule extends ReactContextBaseJavaModule {

    private static final String MODULE_NAME = "MainActivityLifecycleEmitter";
    private static final String EVENT_NAME = "MainActivityLifecycle";
    private static ReactApplicationContext reactContext;

    MainActivityLifecycleModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void addListener(String eventName) {
        // Required by NativeEventEmitter on newer React Native versions.
    }

    @ReactMethod
    public void removeListeners(double count) {
        // Required by NativeEventEmitter on newer React Native versions.
    }

    public static void emitLifecycleEvent(String methodName) {
        if (reactContext == null || !reactContext.hasActiveCatalystInstance()) {
            return;
        }

        WritableMap payload = Arguments.createMap();
        payload.putString("methodName", methodName);
        payload.putDouble("timestamp", System.currentTimeMillis());

        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(EVENT_NAME, payload);
    }
}
