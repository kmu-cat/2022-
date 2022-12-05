# 모바일프로그래밍 팀 프로젝트: 버캣리스트 (Buccat List)

## 팀 치즈 (Team CHEESE)
- 김동윤 (소프트웨어학부, 20212674, Back-End)
- 윤서영 (소프트웨어학부, 20210153, Back-End)
- 이보현 (소프트웨어학부, 20213038, Front-End)
- 장수미 (소프트웨어학부, 20193216, Front-End)
- 최지원 (소프트웨어학부, 20213091, Front-End)


## 앱 소개 (Introduction)
- 버캣리스트는 버킷리스트(Bucket-list)와 고양이(cat)의 합성어로
일상 속 소소한 버킷리스트를 달성하며 그것을 사람들과 공유할 수 있는 플랫폼입니다. 계절 별 버캣리스트를 완수하면 랜덤으로 아이템을 받을 수 있으며 이것으로 자신의 고양이를 꾸밀 수 있습니다.

- 시연 영상: https://youtu.be/Y4q8qhzRjRw


## 개발 환경 및 가상기기 (Development Environment & Android Virtual Device)
- Android Studio : Dolphin | 2021.3.1 Patch 1
- Firebase - Firestore
- Resizable (Size: 6.0", Revolution: 1080x2340, Density: 420dpi)


## 애플리케이션 버전 (Application Version)
- `minSdkVersion` : 21
- `targetSdkVersion` : 32 <br/>
애플리케이션 실행에 필요한 최소 API level은 21 입니다.


## 빌드 시 유의사항 (Gradle)
- 뷰 결합(View Binding) 설정
  - findViewById 대신 뷰 결합(View Binding) 사용함
  - 사용 설정을 위해 viewBinding 요소를 build.gradle 파일에 추가
  
```kotlin  
android {
        ...
        viewBinding {
            enabled = true
        }
    }
```    

<br />

- RecyclerView 라이브러리 추가
  - 상품 페이지에 RecyclerView를 사용함
  - 애플리케이션  모듈의 build.gradle 파일에 필요한 아티팩트의 종속 항목을 추가

- Firebase 라이브러리 


```kotlin  
dependencies {
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    
    // firebase-firestore
    implementation 'com.google.firebase:firebase-firestore-ktx:24.3.1'
    // firebase-storage
    implementation 'com.google.firebase:firebase-storage-ktx:20.0.2'
    implementation 'com.firebaseui:firebase-ui-storage:8.0.0'
    // firebase etc.
    implementation 'com.google.android.gms:play-services-auth:19.2.0'
    implementation 'com.google.firebase:firebase-bom:29.0.0'
    implementation 'com.google.firebase:firebase-messaging-ktx:23.0.0'
    implementation 'com.google.firebase:firebase-analytics-ktx:20.0.0'
    implementation platform('com.google.firebase:firebase-bom:30.4.1')
    implementation 'com.firebaseui:firebase-ui-auth:7.2.0'
}
```    

## 유의사항 (Precautions)
- 이미지 업로드시 미디어 접근 권한을 허용해야 함
- 미디어 접근 권한 비허용시 사진 업로드가 불가하므로 설정-버캣리스트-권한 에서 '허용'으로 직접 변경해야 함
- 이미지 업로드시 .jpg파일로 변환가능한 파일을 업로드 해야함.

## 스크린샷 (Screenshot)
<div>
<img width="200" alt="로그인" src="https://user-images.githubusercontent.com/104475363/203085884-8cad3c9e-4b98-4534-a2bb-1a7c3e80646b.png">
<img width="200" alt="default_cat_setting" src="https://user-images.githubusercontent.com/104475363/203085953-a8ed5562-002c-4261-9b89-23c58c07e28c.png">
<img width="200" alt="버캣리스트" src="https://user-images.githubusercontent.com/104475363/203806546-0c88f810-f044-4899-b3cd-38bb0d1f6590.png">
<img width="200" alt="폴라로이드" src="https://user-images.githubusercontent.com/104475363/204072304-59aada26-315a-4d48-9073-bcb088fcff53.png">
<div/>



<div>
<img width="200" alt="메인페이지_봄" src="https://user-images.githubusercontent.com/104475363/203086034-87c8837e-4859-464b-88ef-b5c4b83ffb7e.png">
<img width="200" alt="메인페이지_여름" src="https://user-images.githubusercontent.com/104475363/203086046-639760c8-4713-4696-b638-1ea9edf56bf7.png">
<img width="200" alt="메인페이지_가을" src="https://user-images.githubusercontent.com/104475363/203086355-dd2bd45c-a943-49ca-aa7c-4653e2b421ad.png">
<img width="200" alt="메인페이지_겨울" src="https://user-images.githubusercontent.com/104475363/203086375-e37b8e76-6609-4a29-9524-a00901db5e0d.png">
<div/>


