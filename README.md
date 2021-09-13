# Android-project-better-now

## 공용 문서
<https://jungspin.notion.site/Android-APP-PROJECT-021b6d10cc4c4d5f8121208d73f2d7b3>


## Android Studio Library
```gradle
// lombok
compileOnly 'org.projectlombok:lombok:1.18.20'
annotationProcessor 'org.projectlombok:lombok:1.18.20'
// material-calendarview
implementation 'com.github.prolificinteractive:material-calendarview:1.4.3'
// mcalendarview 
implementation 'sun.bob:mcalendarview:1.0.0'
// cosmocalendar
implementation 'com.github.applikeysolutions:cosmocalendar:1.0.4'
// richeditor
implementation 'jp.wasabeef:richeditor-android:2.0.0'
// androidsummernote
implementation'in.nashapp.androidsummernote:androidsummernote:1.0.5'
```



# 패키지 구조
## Android Studio
### model
- Habit
- Calender
- Diary

### view
- 바텀 네비의 탭 별로 나눔!!
- 예시 :  Calender - CalenderFragment, 기타 포함되는 액티비티들
- 패키지명
    - calender
    - habit
    - diary
    - status
    - mypage

### viewModel
- view 구조랑 동일하게 만든다

### Service
- HabitService
- CalenderService
- DiaryService

### DTO 필요한 경우 , 기타 클래스의 경우 생성시 재논의
