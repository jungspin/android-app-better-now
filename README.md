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
// mcalendarview -> 얘네 지우면 오류남..참고
implementation 'sun.bob:mcalendarview:1.0.0'
// cosmocalendar
implementation 'com.github.applikeysolutions:cosmocalendar:1.0.4'
// richeditor
implementation 'jp.wasabeef:richeditor-android:2.0.0'
// androidsummernote
implementation'in.nashapp.androidsummernote:androidsummernote:1.0.5'
```


## 데이터베이스 모델링

*1. Diary (일기)
|컬럼명|타입|예시|설명|
|------|---|---|---|
|diary_id|Long|1|PK|
|diary_title|String|오늘 일기|제목|
|diary_content|String|재밌었다|내용|
|diary_url|String|https://camo.githubusercontent.com/a123b2c6011765dd07b9b58e|이미지 경로|
|created|Timestamp|yyyy-mm-dd HH:mm:ss|등록일|
|updated|Timestamp|yyyy-mm-dd HH:mm:ss|수정일|

*2. Habit (습관)
|컬럼명|타입|예시|설명|
|------|---|---|---|
|habit_id|Long|1|PK|
|habit_title|String|식후 30분 내 영양제 복용|습관 이름|
|habit_time|String|저녁 8시|지정 시간|
|habit_memo|String|영양제 바꿔야지..|습관에 관한 메모|
|category|String|생활습관|습관 카테고리|
|created|Timestamp|yyyy-mm-dd HH:mm:ss|등록일|
|updated|Timestamp|yyyy-mm-dd HH:mm:ss|수정일|

*3. Calender (일정)
|컬럼명|타입|예시|설명|
|------|---|---|---|
|calender_id|Long|1|PK|
|calender_title|String|ㅇㅇ이 만나기|일정 내용|
|calender_time|String|8월 24일 15시|일정 시간|

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

## Spring

### model
- Habit
- Calender
- Diary

### Service
- HabitService
- CalenderService
- DiaryService

### Controller
- HabitController
- CalenderController
- DiaryController

### Repository
- HabitRepository
- CalenderRepository
- DiaryRepository

### DTO 필요한 경우 , 기타 클래스의 경우 생성시 재논의