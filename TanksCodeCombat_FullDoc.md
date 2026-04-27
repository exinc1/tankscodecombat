<div dir="rtl">

# Tanks Code Combat

**מגיש:** גיא עמיבר
**ת.ז.:** 329643209
**בית ספר:** העמק המערבי יפעת
**מנחה:** גבי לוינהיים
**חלופה:** פיתוח אפליקציות לטלפונים חכמים
**תאריך הגשה:** אפריל 2026

---

## תוכן עניינים
1. **[1. מבוא](#1-מבוא)**
    - [רקע](#רקע)
    - [שם הפרויקט](#שם-הפרויקט)
    - [תיאור הפרויקט](#תיאור-הפרויקט)
    - [קהל יעד](#קהל-יעד)
    - [סיבות לבחירת הנושא](#סיבות-לבחירת-הנושא)
    - [האפליקציה ומטרותיה](#האפליקציה-ומטרותיה)
2. **[2. מחקר](#2-מחקר)**
    - [אפליקציות דומות בשוק](#אפליקציות-דומות-בשוק)
    - [סקירת השוק](#סקירת-השוק)
3. **[3. ניהול הנתונים בפרויקט](#3-ניהול-הנתונים-בפרויקט)**
    - [אובייקטים נחוצים (במשחק ומחוץ למשחק)](#אובייקטים-נחוצים)
4. **[4. מבנה / ארכיטקטורה](#4-מבנה--ארכיטקטורה)**
    - [קבצי הפרויקט](#קבצי-הפרויקט)
    - [מסכי הפרויקט (LogIn, SignUp, HomePage, VisualGame)](#מסכי-הפרויקט)
    - [תרשים מסכים (Screen Flow)](#תרשים-מסכים)
5. **[5. מימוש הפרויקט](#5-מימוש-הפרויקט)**
    - [קבצי gradle ו-manifest](#מימוש-הפרויקט)
    - [תיאור מחלקות UML](#תיאור-מחלקות-uml)
6. **[6. בסיס נתונים](#6-בסיס-נתונים)**
    - [סקירה (Authentication & Realtime Database)](#בסיס-נתונים)
7. **[7. מחלקות הפרויקט](#7-מחלקות-הפרויקט)**
    - [Activities](#activities)
    - [Fragments](#fragments)
    - [Game Engine](#game-engine)
    - [Models / Utilities](#models-utilities)
    - [ממשק פנימי](#ממשק-פנימי)
8. **[8. מדריך למשתמש](#8-מדריך-למשתמש)**
    - [דרישות והוראות התקנה](#מדריך-למשתמש)
    - [הסבר מפורט על שימוש באפליקציה](#מדריך-למשתמש)
9. **[9. רפלקציה / סיכום אישי](#9-רפלקציה--סיכום-אישי)**
10. **[10. ביבליוגרפיה](#10-ביבליוגרפיה)**
11. **[11. נספחים](#11-נספחים)**

---

<a name="1-מבוא"></a>
## 1. מבוא

<a name="רקע"></a>
### רקע:
בעולם המודרני, פיתוח חשיבה אלגוריתמית ויכולת כתיבת קוד הופכים לכלים חיוניים. הפרויקט "Tanks Code Combat" שואף לשלב בין עולם המשחקים האינטראקטיביים לבין לימוד יסודות התכנות, תוך מתן פלטפורמה תחרותית ומהנה למשתמשים.

<a name="שם-הפרויקט"></a>
### שם הפרויקט:
**Tanks Code Combat**

<a name="תיאור-הפרויקט"></a>
### תיאור הפרויקט:
הפרויקט הוא משחק אסטרטגיה ותכנות דו-שחקני. בניגוד למשחקי טנקים מסורתיים בהם השליטה היא ידנית בזמן אמת, כאן המשתמש כותב "בוט" (Bot) – סקריפט המכתיב את התנהגות הטנק. המשחק כולל מערכת לניהול משתמשים, מסד נתונים לסנכרון מצב הקרב, וסימולטור ויזואלי המציג את תוצאות הקוד שנכתב. הקרב מתנהל בזירה דו-מימדית בה הטנקים צריכים לתמרן, לסרוק את השטח ולירות במטרה להשמיד את היריב.

<a name="קהל-יעד"></a>
### קהל יעד:
קהל היעד כולל תלמידים, סטודנטים וחובבי טכנולוגיה המעוניינים לתרגל פתרון בעיות, לוגיקה ותכנות בסביבה חזותית. המשחק מותאם למי שמחפש אתגר אינטלקטואלי מעבר למשחקי פעולה רגילים.

<a name="סיבות-לבחירת-הנושא"></a>
### סיבות לבחירת הנושא:
בחרתי בנושא זה בשל העניין האישי שלי בשילוב שבין לוגיקה מורכבת לייצוג ויזואלי בזמן אמת. האתגר הטכני של בניית מנוע משחק שיודע להריץ קוד של משתמש קצה (JavaScript) בתוך סביבת אנדרואיד היה המניע המרכזי לפיתוח. בנוסף, רציתי לחקור את השימוש ב-Firebase לסנכרון נתונים אסינכרוני במערכת מרובת משתתפים.

<a name="האפליקציה-ומטרותיה"></a>
### האפליקציה ומטרותיה:
האפליקציה נועדה לספק סביבה בטוחה ומהנה ללימוד תכנות. המטרות העיקריות הן:
1. הנגשת עולם התכנות דרך משחק.
2. יצירת זירה תחרותית המעודדת אופטימיזציה של קוד.
3. מתן ממשק מובייל נוח לכתיבה ובחינה של לוגיקה קרבית.

---

<a name="2-מחקר"></a>
## 2. מחקר

<a name="אפליקציות-דומות-בשוק"></a>
### אפליקציות דומות בשוק:
1. **Robocode:** מערכת ותיקה למחשב האישי שבה מתכנתים טנקים ב-Java. היא מהווה את ההשראה המרכזית, אך Tanks Code Combat מביא את הקונספט לעולם המובייל עם דגש על פשטות ונגישות.
2. **Screeps:** משחק MMO למתכנתים שבו השליטה במושבה מתבצעת דרך JavaScript. המשחק מאוד מורכב ויכול להרתיע מתחילים, בעוד שהפרויקט שלי מתמקד בקרבות קצרים ומהירים.
3. **Chess.com:** אמנם מדובר במשחק לוח מסורתי, אך הוא מדגים את החשיבות של ניהול משתמשים, דירוגים וסנכרון מצב משחק – אלמנטים שיושמו גם כאן.

<a name="סקירת-השוק"></a>
### סקירת השוק:
ממחקר השוק שערכתי, רוב האפליקציות המשלבות תכנות במובייל הן "קורסים" סטטיים. קיימות מעט מאוד אפליקציות המאפשרות תחרות אקטיבית מבוססת קוד בזמן אמת עם ממשק גרפי מלוטש. רוב המשחקים הקיימים הם Arcade ואינם דורשים חשיבה אסטרטגית מוקדמת. Tanks Code Combat תופס את הנישה שבין לימוד תיאורטי לבין משחק פעולה.

---

<a name="3-ניהול-הנתונים-בפרויקט"></a>
## 3. ניהול הנתונים בפרויקט

<a name="אובייקטים-נחוצים"></a>
### אובייקטים נחוצים:

**במשחק:**
- **Tank (טנק):** מייצג את הישות של השחקן. מכיל נתונים כמו מיקום (Location), כיוון (Direction), כמות חיים, סוג תחמושת ומהירות.
- **Board (לוח):** מנהל את המרחב הדו-מימדי, כולל מכשולים וזיהוי התנגשויות בין פגזים לטנקים.
- **Action (פעולה):** אובייקט המייצג הוראה בודדת שנוצרה על ידי הקוד (למשל: תזוזה קדימה, סיבוב צריח).
- **Logs (יומנים):** מתעד כל שלב בקרב לצורך הצגה חוזרת (Replay) בסימולטור הויזואלי.

**מחוץ למשחק:**
- **User (משתמש):** פרטי המשתמש נחלקים לשניים:
    1. **Authentication:** אימות (מייל וסיסמה) מנוהל ב-Firebase Auth.
    2. **Database Profile:** שם תצוגה, מספר נצחונות וסטטיסטיקות נשמרים ב-Realtime Database.

---

<a name="4-מבנה--ארכיטקטורה"></a>
## 4. מבנה / ארכיטקטורה

<a name="קבצי-הפרויקט"></a>
### קבצי הפרויקט:
הפרויקט בנוי במבנה Android תקני:
- `java/com/example/tankscodecombat/`: מכיל את כל מחלקות הלוגיקה והממשק.
- `res/layout/`: קבצי ה-XML המגדירים את נראות המסכים.
- `res/raw/`: קבצי מדיה (כמו מוזיקת הרקע).

<a name="מסכי-הפרויקט"></a>
### מסכי הפרויקט:

**שם המסך: LogIn (מסך כניסה)**
- **תיאור:** דף הכניסה למשתמשים קיימים.
- **אלמנטים:** תיבות טקסט למייל וסיסמה, כפתור כניסה וקישור להרשמה.
  ![מסך הבית](/home/exinc/.cache/Google/AndroidStudio2025.3.4/projects/tankscodecombat.c9424150/.artifacts/20260427-175638-aedbe539-be3d-49f0-9753-dbecbc54ebc6/Screenshot-20260427-19:16:56.png)


**שם המסך: SignIn / SignUp (מסך הרשמה)**
- **תיאור:** דף ליצירת חשבון חדש.
- **אלמנטים:** שם משתמש, מייל, סיסמה וכפתור אישור השומר את הנתונים ב-Firebase.

![מסך הרשמה](/home/exinc/.cache/Google/AndroidStudio2025.3.4/projects/tankscodecombat.c9424150/.artifacts/20260427-175638-aedbe539-be3d-49f0-9753-dbecbc54ebc6/Screenshot-20260427-19:16:24.png)

**שם המסך: HomePage (מסך הבית)**
- **תיאור:** מרכז הניווט של האפליקציה.
- **אלמנטים:** תפריט תחתון (Bottom Navigation) למעבר בין מסך הבית, הוראות וניתוק.

![מסך כניסה](/home/exinc/.cache/Google/AndroidStudio2025.3.4/projects/tankscodecombat.c9424150/.artifacts/20260427-175638-aedbe539-be3d-49f0-9753-dbecbc54ebc6/Screenshot-20260427-19:15:54.png)

**שם המסך: MainActivityFragment (מסך הבית)**
- **תיאור:** איפה שאתה מאתחל את המשחק.
- **אלמנטים:** העלאת בוטים,
  ![מסך כניסה](/home/exinc/.cache/Google/AndroidStudio2025.3.4/projects/tankscodecombat.c9424150/.artifacts/20260427-175638-aedbe539-be3d-49f0-9753-dbecbc54ebc6/Screenshot-20260427-19:17:41.png)


**שם המסך: VisualGame (זירת הקרב)**
- **תיאור:** הזירה הויזואלית שבה מתבצעת הסימולציה.
- **אלמנטים:** `GameBoardView` (תצוגת הלוח), כפתורי שליטה בסימולציה (Play, Pause, Forward).

![מסך הקרב](/home/exinc/.cache/Google/AndroidStudio2025.3.4/projects/tankscodecombat.c9424150/.artifacts/20260427-175638-aedbe539-be3d-49f0-9753-dbecbc54ebc6/Screenshot-20260427-19:17:11.png)

<a name="תרשים-מסכים"></a>
### תרשים מסכים:
*(כאן יופיע תרשים זרימה המראה את המעבר בין LogIn -> HomePage -> VisualGame)*

---

<a name="5-מימוש-הפרויקט"></a>
## 5. מימוש הפרויקט

<a name="מימוש-הפרויקט"></a>
### קבצי gradle ו-manifest:

**gradle (Module: app):**
```gradle
dependencies {
    implementation 'com.google.firebase:firebase-auth:22.3.1'
    implementation 'com.google.firebase:firebase-database:20.3.1'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'org.mozilla:rhino:1.7.14'
}
```

**AndroidManifest.xml:**
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.TanksCodeCombat">
        <activity android:name=".MainActivity" android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".HomePage" />
        <activity android:name=".VisualGame" />
        <activity android:name=".LogIn" />
        <activity android:name=".SignIn" />
        <service android:name=".MusicService" />
    </application>
</manifest>
```

<a name="תיאור-מחלקות-UML"></a>
### תיאור מחלקות UML:
<!-- IMAGE: UML Class Diagram placeholder -->

---

<a name="6-בסיס-נתונים"></a>
## 6. בסיס נתונים

<a name="בסיס-נתונים"></a>
### סקירה:
הפרויקט עושה שימוש ב-**Firebase**, שירות ענן של גוגל.
- **Authentication:** ניהול רישום וכניסה מאובטחת. כל משתמש מקבל UID ייחודי.
- **Realtime Database:** בסיס נתונים NoSQL בתצורת עץ JSON. 
    - ענף `users/`: שומר תחת כל UID את השם (`name`), מספר הנצחונות (`wins`) וכמות המשחקים (`games_played`).
    - ענף `codes/`: שומר את קוד ה-JavaScript האחרון שכתב המשתמש.

---

<a name="7-מחלקות-הפרויקט"></a>
## 7. מחלקות הפרויקט

<a name="activities"></a>
### Activities (אקטיביטיז)

#### LogIn.java
**תפקיד המחלקה:**
מחלקה זו אחראית על מסך הכניסה של האפליקציה. היא מאפשרת למשתמשים קיימים להתחבר באמצעות אימייל וסיסמה דרך Firebase Authentication.

**תכונות המחלקה:**
- `ETemail`: תיבת טקסט להזנת אימייל.
- `ETpassword`: תיבת טקסט להזנת סיסמה.
- `ref`: אובייקט FirebaseAuth לניהול האימות.

**פעולות המחלקה:**
- **onCreate**: מאתחל את הממשק ומקשר את ה-Views.
- **Login**: מבצע אימות מול Firebase.

#### VisualGame.java
**תפקיד המחלקה:**
המסך המרכזי של המשחק. אחראי על הרצת הסימולציה, הצגת הלוח וניהול כפתורי השליטה.

**פעולות המחלקה:**
- **saveGame**: שמירת תוצאות הקרב ויומני המהלכים ב-Database.
- **logsListToArray**: המרת נתונים ממבנה JSON למערך אובייקטים מסוג Logs.

<a name="fragments"></a>
### Fragments (פרגמנטים)

#### MainActivityFragment.java
**תפקיד המחלקה:**
מנהל את התוכן המרכזי של דף הבית, כולל טעינת בוטים והתחלת משחקים.

#### InstructionsFragment.java
**תפקיד המחלקה:**
מציג את הוראות ה-API והשימוש באפליקציה.

<a name="game-engine"></a>
### Game Engine (מנוע המשחק)

#### Game.java
**תפקיד המחלקה:**
המנוע המרכזי שמריץ את הקרב. היא אחראית על ניהול התורות, הפעלת הבוטים ותיעוד כל פעולה.

**פעולות המחלקה:**
- **run**: מריץ את לולאת המשחק המרכזית (100 תורות).
- **document**: תיעוד מצב כל טנק בסוף כל תור.

#### Board.java
**תפקיד המחלקה:**
ניהול המרחב הדו-מימדי וזיהוי התנגשויות בין פגזים לטנקים.

#### JSBotTank.java
**תפקיד המחלקה:**
מחלקה המאפשרת הרצת קוד JavaScript שכתב המשתמש בתוך סביבת ה-Java של האפליקציה באמצעות מנוע Rhino.

<a name="models-utilities"></a>
### Models / Utilities (מודלים וכלי עזר)

#### Tank.java
**תפקיד המחלקה:**
מייצג את הישות של הטנק במשחק.

#### Direction.java / Location.java
**תפקיד המחלקה:**
ניהול כיוונים ומיקומים במרחב הדו-מימדי.

---

<a name="ממשק-פנימי"></a>
### ממשק פנימי:
במחלקה `RoomManager` קיים ממשק פנימי המאפשר לממש פונקציות למדא לקריאת נתונים מחדר המשחק:
```java
public interface IGameRoomRead {
    void onGameRoomRead(boolean resigned, String enemyTroopId, int[] reversedPos);
}
```

---

<a name="8-מדריך-למשתמש"></a>
## 8. מדריך למשתמש

<a name="מדריך-למשתמש"></a>
### דרישות והוראות התקנה:
- מכשיר אנדרואיד בגרסה 9.0 ומעלה.
- חיבור אינטרנט פעיל.
- התקנה מקובץ APK.

<a name="הסבר-מפורט-על-שימוש-באפליקציה"></a>
### הסבר מפורט על שימוש באפליקציה:

#### 1. כניסה והרשמה
בפעם הראשונה שתפתח את האפליקציה, תתבקש ליצור חשבון. המידע יישמר ב-Firebase.

#### 2. כתיבת קוד (Bot Programming)
עליך לכתוב פונקציית `run` שתקבל את מצב הטנק ותחזיר פעולה.
**ה-API העומד לרשותך:**
- `move(speed)`: תנועה קדימה/אחורה.
- `rotate(degrees)`: סיבוב גוף הטנק.
- `rotateTurret(degrees)`: סיבוב הצריח.
- `fire()`: ירי פגז.

![מסך הוראות](/home/exinc/.cache/Google/AndroidStudio2025.3.4/projects/tankscodecombat.c9424150/.artifacts/20260427-175638-aedbe539-be3d-49f0-9753-dbecbc54ebc6/Screenshot-20260427-19:17:23.png)

#### 3. צפייה בקרב
לאחר הרצת הקוד, תועבר לסימולטור הויזואלי שבו תוכל לראות את ביצועי הבוט שלך.

---

<a name="9-רפלקציה--סיכום-אישי"></a>
## 9. רפלקציה / סיכום אישי
פיתוח "Tanks Code Combat" היה מסע לימודי משמעותי עבורי. האתגר הטכני הגדול ביותר היה בניית מנוע שיודע להריץ לוגיקה של משתמש בצורה מבודדת. למדתי לעומק על עבודה עם Realtime Database ועל חשיבות ההפרדה בין לוגיקת המשחק לתצוגה הויזואלית.

---

<a name="10-ביבליוגרפיה"></a>
<a name="ביבליוגרפיה"></a>
## 10. ביבליוגרפיה
1. תיעוד רשמי של Firebase: https://firebase.google.com/docs
2. מדריכי Android Developers: https://developer.android.com
3. Rhino JavaScript Engine: https://github.com/mozilla/rhino

---

<a name="11-נספחים"></a>
<a name="נספחים"></a>
## 11. נספחים

### fragment_instructions.xml:
```xml
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#121212"
    android:padding="16dp">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Tanks Code Combat - API Guide"
            android:textColor="#FFD700"
            android:textSize="24sp"
            android:textStyle="bold"
            android:gravity="center" />
    </LinearLayout>
</ScrollView>
```

</div>
