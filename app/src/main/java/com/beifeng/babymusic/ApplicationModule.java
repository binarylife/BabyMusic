package com.beifeng.babymusic;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/11/30
 */
@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {

        this.context = context;
    }

    @Provides
    BabyApplication provideApplication() {

        return (BabyApplication) context.getApplicationContext();
    }

    @Provides
    Context provideContext() {

        return context;
    }
}
