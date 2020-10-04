# 토랑 페이스북 라이브러리
토랑 앱에서 페이스북 로그인 라이브러리 연동을 위해 만들었습니다.


## 사용법

## 1. 프로젝트 clone

## 2. settings.gradle 파일에 프로젝트 import 하기
settings.gradle
```
include ':fblogin'
project(':fblogin').projectDir = new File('/Users/sarangyang/AndroidStudioProjects/FacebookLoginLibrary/FacebookLoginLibrary')
```

## 3. Dagger를 사용하여 모듈 등록하기

build.gradle
```
implementation 'com.google.dagger:dagger-android:2.29.1'
kapt 'com.google.dagger:dagger-compiler:2.29.1'
```

AppApplication.kt
```
@Component(modules = [FaceBookLoginModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
```

## 4. FacebookLoginProvider 사용하기

### 로그인 요청

### 로그아웃 요청