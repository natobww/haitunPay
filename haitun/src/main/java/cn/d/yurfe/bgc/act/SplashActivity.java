package cn.d.yurfe.bgc.act;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.widget.VideoView;

import cn.d.yurfe.bgc.util.Logger;
import cn.d.yurfe.bgc.util.PackageUtil;
import cn.d.yurfe.bgc.util.UIUtils;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import cn.d.yurfe.bgc.R;
import cn.d.yurfe.bgc.base.BaseActivity;
import cn.d.yurfe.bgc.base.BaseApplication;
import cn.d.yurfe.bgc.net.AccessAddresses;
import cn.d.yurfe.bgc.util.SharedPreferencesUtils;

public class SplashActivity extends BaseActivity {

    private boolean isFirtIn = SharedPreferencesUtils.getBoolean(BaseApplication.getContext(), "isFirst", false);
    private VideoView spl_videoview;
    private String mJsonDomain;

    @Override
    public void initView() {
        setContentView(R.layout.activity_splash);
        spl_videoview = (VideoView) findViewById(R.id.spl_videoview);
        mJsonDomain = getCorrectJsonDomain();

    }

    private String getCorrectJsonDomain() {
        String jsonDomain = SharedPreferencesUtils.getString(UIUtils.getContext(), "jsonDomain", "");
        if (!TextUtils.isEmpty(jsonDomain)) {
            if (jsonDomain.startsWith("http://")) {
                if (jsonDomain.endsWith("/")) {
                    if (jsonDomain.contains(".")) {
                        return jsonDomain;
                    } else {
                    }
                } else {
                }
            } else {
            }
        } else {
        }
        return AccessAddresses.final_JsonDomain;
    }

    @Override
    public void initListener() {
        spl_videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVolume(0f, 0f);
                mediaPlayer.start();
                BaseApplication.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }
                }, 2500);
            }
        });
    }

    @Override
    public void initData() {
        spl_videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.start));
    }
}