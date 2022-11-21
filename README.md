# 모바일프로그래밍 팀 프로젝트: 버캣리스트 (Buccat List)

## 팀 치즈 (Team CHEESE)
- 김동윤 (21학번)
- 윤서영 (21학번)
- 이보현 (21학번)
- 장수미 (19학번)
- 최지원 (21학번)


## 앱 소개 (Introduction)
- 버캣리스트는 버킷리스트(Bucket-list)와 고양이(cat)의 합성어로
일상 속 소소한 버킷리스트를 달성하며 그것을 사람들과 공유할 수 있는 플랫폼입니다. 계절 별 버캣리스트를 완수하면 랜덤으로 고양이를 꾸밀 수 있는 아이템을 받을 수 있으며 이것으로 자신의 고양이를 꾸밀 수 있습니다.

- 시연 영상: (유튜브 링크 추가)


## 개발 환경 (Development Environment)
- Android Studio : Dolphin | 2021.3.1 Patch 1
- Firebase


## 애플리케이션 버전 (Application Version)
- `minSdkVersion` : 21
- `targetSdkVersion` : 32
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


```kotlin  
dependencies {
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
}
```    




## 스크린샷 (Screenshot)
<div>
<img width="200" alt="로그인" src="https://user-images.githubusercontent.com/104475363/203085884-8cad3c9e-4b98-4534-a2bb-1a7c3e80646b.png">
<img width="200" alt="default_cat_setting" src="https://user-images.githubusercontent.com/104475363/203085953-a8ed5562-002c-4261-9b89-23c58c07e28c.png">
<img width="200" alt="나의 버캣리스트" src="https://user-images.githubusercontent.com/104475363/203086115-70eb8677-c4c3-443b-9d2e-385d7b986a6f.png">
<img width="200" alt="폴라로이드" src="https://user-images.githubusercontent.com/104475363/203086143-c51c3ed0-570e-4588-9870-8a22e4766310.png">
<div/>

<div>
<img width="200" alt="메인페이지_봄" src="https://user-images.githubusercontent.com/104475363/203086034-87c8837e-4859-464b-88ef-b5c4b83ffb7e.png">
<img width="200" alt="메인페이지_여름" src="https://user-images.githubusercontent.com/104475363/203086046-639760c8-4713-4696-b638-1ea9edf56bf7.png">
<img width="200" alt="메인페이지_가을" src="https://user-images.githubusercontent.com/104475363/203086355-dd2bd45c-a943-49ca-aa7c-4653e2b421ad.png">
<img width="200" alt="메인페이지_겨울" src="https://user-images.githubusercontent.com/104475363/203086375-e37b8e76-6609-4a29-9524-a00901db5e0d.png">
<div/>


