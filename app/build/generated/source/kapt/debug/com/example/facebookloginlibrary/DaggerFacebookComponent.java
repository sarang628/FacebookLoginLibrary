// Generated by Dagger (https://dagger.dev).
package com.example.facebookloginlibrary;

import com.example.loginlibrary.FaceBookLoginModule;
import com.example.loginlibrary.FaceBookLoginModule_ProvideFacebooLoginProviderFactory;
import dagger.internal.Preconditions;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerFacebookComponent implements FacebookComponent {
  private final FaceBookLoginModule faceBookLoginModule;

  private DaggerFacebookComponent(FaceBookLoginModule faceBookLoginModuleParam) {
    this.faceBookLoginModule = faceBookLoginModuleParam;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static FacebookComponent create() {
    return new Builder().build();
  }

  @Override
  public void inject(MainActivity mainActivity) {
    injectMainActivity(mainActivity);
  }

  private MainActivity injectMainActivity(MainActivity instance) {
    MainActivity_MembersInjector.injectFacebookLoginProvider(instance, FaceBookLoginModule_ProvideFacebooLoginProviderFactory.provideFacebooLoginProvider(faceBookLoginModule));
    return instance;
  }

  public static final class Builder {
    private FaceBookLoginModule faceBookLoginModule;

    private Builder() {
    }

    public Builder faceBookLoginModule(FaceBookLoginModule faceBookLoginModule) {
      this.faceBookLoginModule = Preconditions.checkNotNull(faceBookLoginModule);
      return this;
    }

    public FacebookComponent build() {
      if (faceBookLoginModule == null) {
        this.faceBookLoginModule = new FaceBookLoginModule();
      }
      return new DaggerFacebookComponent(faceBookLoginModule);
    }
  }
}
