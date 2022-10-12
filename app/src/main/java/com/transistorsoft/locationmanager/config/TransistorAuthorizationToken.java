
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package com.transistorsoft.locationmanager.config;

public class TransistorAuthorizationToken {
//    public static final java.lang.String ACTION_GET;
//    public static final java.lang.String ACTION_DESTROY;
//    private static final java.lang.String TOKEN_KEY;
//    private static final java.lang.String REGISTRATION_PATH;
//    private final java.lang.String mAccessToken;
//    private final java.lang.String mRefreshToken;
    private long mExpires;

//    public static void findOrCreate(android.content.Context context, java.lang.String s, java.lang.String s1, java.lang.String s2, com.transistorsoft.locationmanager.config.TransistorAuthorizationToken.Callback callback) { /* compiled code */ }

    public static boolean hasTokenForHost(android.content.Context context, java.lang.String s) {
        return true;
    }

    public static void destroyTokenForUrl(android.content.Context context, java.lang.String s, @androidx.annotation.Nullable com.transistorsoft.locationmanager.adapter.callback.TSCallback tsCallback) { /* compiled code */ }

//    public static com.transistorsoft.locationmanager.config.TransistorAuthorizationToken fromJson(org.json.JSONObject jsonObject) { /* compiled code */ }

    public TransistorAuthorizationToken(java.lang.String s, java.lang.String s1, long l) { /* compiled code */ }

//    public java.lang.String getAccessToken() { /* compiled code */ }
//
//    public java.lang.String getRefreshToken() { /* compiled code */ }
//
//    public long getExpires() { /* compiled code */ }

//    public java.util.Map<java.lang.String,java.lang.Object> toMap() { /* compiled code */ }
//
//    public org.json.JSONObject toJson() { /* compiled code */ }

//    private static class b implements java.lang.Runnable {
//        private final android.content.Context a;
//        private final java.lang.String b;
//        private final java.lang.String c;
//        private final java.lang.String d;
//        private final com.transistorsoft.locationmanager.config.TransistorAuthorizationToken.Callback e;
//
//        b(android.content.Context context, java.lang.String s, java.lang.String s1, java.lang.String s2, com.transistorsoft.locationmanager.config.TransistorAuthorizationToken.Callback callback) { /* compiled code */ }
//
//        private void a(com.transistorsoft.locationmanager.config.TransistorAuthorizationToken transistorAuthorizationToken) { /* compiled code */ }
//
//        private void a(java.lang.String s) { /* compiled code */ }
//
//        public void run() { /* compiled code */ }
//    }

    public static interface Callback {
        void onSuccess(com.transistorsoft.locationmanager.config.TransistorAuthorizationToken transistorAuthorizationToken);

        void onFailure(java.lang.String s);
    }
}