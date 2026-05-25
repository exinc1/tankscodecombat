<div dir="rtl" style="text-align: right;">

# Tanks Code Combat

**מגיש:** אביב פרקש

**ת.ז.:** 312398449

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
    - [אובייקטים נחוצים](#אובייקטים-נחוצים)
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
## רקע:

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
3. **Planet wars:** משחק של לכבוש את כל הכוכבים של הצד השני עד שאתה מנצח. שני הצדדים זה בוטים שכתובים בפייתון. 

<a name="סקירת-השוק"></a>
### סקירת השוק:
ממחקר השוק שערכתי, רוב האפליקציות המשלבות תכנות במובייל הן "קורסים" סטטיים. קיימות מעט מאוד אפליקציות המאפשרות תחרות אקטיבית מבוססת קוד בזמן אמת עם ממשק גרפי. רוב המשחקים הקיימים הם Arcade ואינם דורשים חשיבה אסטרטגית מוקדמת. Tanks Code Combat תופס את הנישה שבין לימוד תיאורטי לבין משחק פעולה.

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
הפרויקט בנוי במבנה Android תקני ומאורגן בחבילות (Packages) להפרדת אחריות:
- `com.example.tankscodecombat`: החבילה הראשית.
- `Activities`: `LogIn.java`, `SignIn.java`, `HomePage.java`, `VisualGame.java`.
- `Fragments`: `MainActivityFragment.java`, `InstructionsFragment.java`, `LogoutFragment.java`.
- `Game Engine`: `Game.java`, `Board.java`, `TankHandler.java`, `JSBotTank.java`.
- `Models`: `Tank.java`, `Action.java`, `TankState.java`, `Location.java`, `Direction.java`.
- `res/layout/`: קבצי ה-XML המגדירים את נראות המסכים.

<a name="מסכי-הפרויקט"></a>
### מסכי הפרויקט:

**שם המסך: LogIn (מסך כניסה)**
- **תיאור:** דף הכניסה למשתמשים קיימים.
- **אלמנטים:** תיבות טקסט למייל וסיסמה, כפתור כניסה וקישור להרשמה.
  ![מסך כניסה](pictures/Screenshot-20260427-19:15:54.png)

**שם המסך: SignUp (מסך הרשמה)**
- **תיאור:** דף ליצירת חשבון חדש.
- **אלמנטים:** שם משתמש, מייל, סיסמה וכפתור אישור השומר את הנתונים ב-Firebase.
  ![מסך הרשמה](pictures/Screenshot-20260427-19:16:24.png)

**שם המסך: HomePage (מסך הבית)**
- **תיאור:** מרכז הניווט של האפליקציה.
- **אלמנטים:** תפריט תחתון (Bottom Navigation) למעבר בין מסך הבית, הוראות וניתוק.
  ![מסך הבית](pictures/Screenshot-20260427-19:16:56.png)

**שם המסך: MainActivityFragment (טעינת בוטים)**
- **תיאור:** המסך המאפשר בחירת בוטים ממערכת הקבצים או מה-Database.
- **אלמנטים:** כפתורי "Select Bot", כפתור "Start Game".
  ![מסך טעינה](pictures/Screenshot-20260427-19:17:41.png)

**שם המסך: VisualGame (זירת הקרב)**
- **תיאור:** הזירה הויזואלית שבה מתבצעת הסימולציה.
- **אלמנטים:** `GameBoardView` (תצוגת הלוח), כפתורי שליטה בסימולציה (Play, Pause, Forward).
  ![מסך הקרב](pictures/Screenshot-20260427-19:17:11.png)

<a name="תרשים-מסכים"></a>
### תרשים מסכים (Screen Flow):
הניווט באפליקציה מתבצע בצורה הבאה:
- **LogIn Activity**: נקודת הכניסה. ניתן לעבור ל-SignIn או להתחבר.
- **SignIn Activity**: יצירת חשבון וחזרה ל-LogIn או מעבר ל-HomePage.
- **HomePage Activity**: מסך הבית עם Bottom Navigation:
    - **Home (MainActivityFragment)**: בחירת בוטים ומעבר ל-VisualGame.
    - **Instructions (InstructionsFragment)**: קריאת התיעוד.
    - **Logout (LogoutFragment)**: התנתקות וחזרה ל-LogIn.
- **VisualGame Activity**: הרצת הקרב והצגת התוצאות.

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

<a name="תיאור-מחלקות-uml"></a>
### תיאור מחלקות UML:

**מבנה היררכי:**
![UML Hierarchic](pictures/tankscodecombat-hierachic-groups.png)

**מבנה רדיאלי (קשרי גומלין):**
![UML Radial](pictures/tankscodecombat-radial.png)

**קשרים עם AppCompatActivity:**
![UML Radial Activity](pictures/tankscodecombat-radial-with-AppCompatActivity.png)

---

<a name="6-בסיס-נתונים"></a>
## 6. בסיס נתונים

<a name="בסיס-נתונים"></a>
### סקירה:
הפרויקט עושה שימוש ב-**Firebase**, שירות ענן של גוגל.
- **Authentication:** ניהול רישום וכניסה מאובטחת. כל משתמש מקבל UID ייחודי.
- **Realtime Database:** בסיס נתונים NoSQL בתצורת עץ JSON.
    - ענף `users/`: שומר תחת כל UID את השם (`name`), מספר הנצחונות (`wins`) וכמות המשחקים (`games_played`).
    - ענף `bots/`: שומר את קוד ה-JavaScript האחרון שכתב המשתמש.

---

<a name="7-מחלקות-הפרויקט"></a>
## 7. מחלקות הפרויקט

<a name="activities"></a>
### Activities (אקטיביטיז)

#### LogIn.java
**תפקיד המחלקה:**
מחלקה זו אחראית על מסך הכניסה של האפליקציה. היא מאפשרת למשתמשים קיימים להתחבר באמצעות אימייל וסיסמה דרך Firebase Authentication וניהול כניסה אוטומטית בעזרת SharedPreferences.

**תכונות המחלקה:**
- `ETemail`: EditText להזנת אימייל המשתמש.
- `ETpassword`: EditText להזנת סיסמת המשתמש.
- `ref`: אובייקט FirebaseAuth לניהול האימות מול השרת.
- `prefs`: SharedPreferences לשמירת נתוני חיבור מקומיים לצורך כניסה מהירה בעתיד.

**פעולות המחלקה:**

**שם:** `onCreate`
**תוכן:**
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_log_in);
    ETemail = findViewById(R.id.email);
    ETpassword = findViewById(R.id.password);
    ref = FirebaseAuth.getInstance();
    prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
    // Auto-login logic: check if user is already signed in via ref or prefs
}
```
**תיאור:** מאתחלת את רכיבי הממשק, מקשרת אותם למשתני המחלקה ובודקת אם המשתמש כבר מחובר לצורך כניסה אוטומטית.

**שם:** `Login`
**תוכן:**
```java
public void Login(View view) {
    String email = ETemail.getText().toString();
    String password = ETpassword.getText().toString();
    if (email.isEmpty() || password.isEmpty()) return;
    ref.signInWithEmailAndPassword(email, password)
       .addOnCompleteListener(this, task -> {
           if (task.isSuccessful()) {
               startActivity(new Intent(this, HomePage.class));
               finish();
           }
       });
}
```
**תיאור:** פונקציה המופעלת בלחיצה על כפתור ה-Login. היא מושכת את הטקסט מהשדות ומנסה לבצע אימות מול Firebase. במקרה של הצלחה, המשתמש מועבר למסך הבית.

**שם:** `goToSignIn`
**תיאור:** מעבירה את המשתמש למסך ההרשמה (`SignIn.java`).

---

#### SignIn.java
**תפקיד המחלקה:**
ניהול מסך ההרשמה ליצירת משתמשים חדשים במערכת.

**תכונות המחלקה:**
- `ETemail`: EditText להזנת אימייל חדש.
- `ETpassword`: EditText להזנת סיסמה חדשה.
- `ref`: אובייקט FirebaseAuth ליצירת המשתמש בשרת.

**פעולות המחלקה:**

**שם:** `createUser`
**תוכן:**
```java
public void createUser(View view) {
    String email = ETemail.getText().toString();
    String password = ETpassword.getText().toString();
    ref.createUserWithEmailAndPassword(email, password)
       .addOnCompleteListener(this, task -> {
           if (task.isSuccessful()) {
               startActivity(new Intent(this, HomePage.class));
               finish();
           }
       });
}
```
**תיאור:** יוצרת משתמש חדש ב-Firebase Authentication ומעבירה אותו למסך הבית.

---

#### HomePage.java
**תפקיד המחלקה:**
ניהול התפריט הראשי והמעבר בין הפרגמנטים השונים (בית, הוראות, התנתקות) באמצעות Bottom Navigation.

**פעולות:**

**שם:** `onCreate`
**תיאור:** מגדירה את המאזין ל-BottomNavigationView וטוענת את הפרגמנט ההתחלתי (`MainActivityFragment`).

**שם:** `loadFragment`
**תוכן:**
```java
private void loadFragment(Fragment fragment) {
    getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit();
}
```
**תיאור:** פונקציית עזר המחליפה את הפרגמנט המוצג בתוך ה-Container הראשי של המסך.

---

#### VisualGame.java
**תפקיד המחלקה:**
המסך המרכזי שבו מוצגת סימולציית הקרב בין הבוטים. אחראי על הרצת הלוגיקה והצגת התוצאות בצורה ויזואלית.

**תכונות המחלקה:**
- `game`: אובייקט ה-Game המנהל את לוגיקת הקרב הנסתרת.
- `gameBoard`: Custom View (GameBoardView) לציור שדה הקרב.
- `replayLogs`: רשימה של אובייקטי Logs המכילים את כל מהלכי המשחק לצורך הצגתם.

**פעולות המחלקה:**

**שם:** `saveGame`
**תיאור:** פונקציה פרטית השומרת את תוצאות המשחק ויומני המהלכים ב-Firebase Realtime Database תחת תיקיית המשתמש המחובר.

**שם:** `showGameResult`
**תיאור:** מציגה דיאלוג למשתמש בסיום הקרב המודיע מי המנצח (או אם היה תיקו).

---

<a name="fragments"></a>
### Fragments (פרגמנטים)

#### MainActivityFragment.java
**תפקיד המחלקה:**
מנהל את התוכן המרכזי של דף הבית – בחירת בוטים והרצת משחקים.

**פעולות:**

**שם:** `loadBot1 / loadBot2`
**תיאור:** מפעיל את ה-ActivityResultLauncher לבחירת קובץ JavaScript ממערכת הקבצים של המכשיר.

**שם:** `startGame`
**תיאור:** אוסף את קוד הבוטים שנבחרו ועובר לאקטיביטי `VisualGame` להרצת הסימולציה.

**שם:** `loadBotFromDB`
**תיאור:** מושכת רשימת בוטים שנשמרו בעבר מה-Database של המשתמש ומאפשרת לבחור אותם לקרב.

---

#### InstructionsFragment.java
**תפקיד המחלקה:**
מציג למשתמש את ה-API וההוראות לכתיבת הבוט (JavaScript API).

---

#### LogoutFragment.java
**תפקיד המחלקה:**
ביצוע ניתוק מהחשבון (`signOut`) וחזרה למסך הכניסה.

---

<a name="game-engine"></a>
### Game Engine (מנוע המשחק)

#### Game.java
**תפקיד המחלקה:**
המנוע המרכזי המריץ את לוגיקת הקרב בתורות. היא אחראית על תיאום בין הבוטים, עדכון הלוח ותיעוד המהלכים.

**תכונות המחלקה:**
- `MAX_NUMBER_OF_TURNS`: קבוע המגדיר את אורך המשחק (100 תורות).
- `m_tanks`: מערך המכיל את שני אובייקטי הטנקים המשתתפים.
- `m_logs`: מערך השומר את היסטוריית הקרב לצורך שידור חוזר.
- `m_tankHandler`: אובייקט עזר לביצוע פעולות פיזיות על הלוח.

**פעולות המחלקה:**

**שם:** `run`
**תוכן:**
```java
public int run() {
    for (int tIdx = 0; tIdx < MAX_NUMBER_OF_TURNS; tIdx++) {
        for (int i = 0; i < 2; i++) {
            TankState state = m_tankHandler.getState(i);
            Action action = m_tanks[i].run(state);
            int winner = m_tankHandler.action(i, action);
            document(i, tIdx, action);
            if (winner != -1) return winner;
        }
    }
    return 0; // Draw
}
```
**תיאור:** מריצה את לולאת המשחק המרכזית. בכל תור, כל בוט מקבל את מצבו הנוכחי, מחזיר פעולה, והפעולה מבוצעת על הלוח. אם יש מנצח, הלולאה עוצרת.

---

#### Board.java
**תפקיד המחלקה:**
ניהול המרחב הדו-מימדי (100x100) שבו מתנהל הקרב. אחראית על מיקומי הטנקים, חישוב מרחקים וזיהוי פגיעות.

**פעולות:**

**שם:** `getRadar`
**תוכן:**
```java
public static Direction getRadar(int tankId) {
    Location myLoc = m_tanksLocation[tankId];
    Location enemyLoc = m_tanksLocation[1 - tankId];
    double dx = enemyLoc.getX() - myLoc.getX();
    double dy = enemyLoc.getY() - myLoc.getY();
    return new Direction((int) Math.toDegrees(Math.atan2(dy, dx)));
}
```
**תיאור:** מחשב את הזווית המדויקת מהטנק הנוכחי לעבר היריב.

---

#### JSBotTank.java
**תפקיד המחלקה:**
מימוש של `Tank` המאפשר הרצת קוד JavaScript שכתב המשתמש בתוך סביבת ה-Java באמצעות מנוע Rhino.

**פעולות:**

**שם:** `run`
**תוכן:**
```java
@Override
public Action run(TankState state) {
    Object result = runFunction.call(cx, scope, scope, new Object[]{state});
    return parseAction(result);
}
```
**תיאור:** קוראת לפונקציית ה-JavaScript של המשתמש, מעבירה לה את מצב הטנק, וממירה את הערך המוחזר לאובייקט Java מסוג `Action`.

---

<a name="models-utilities"></a>
### Models / Utilities (מודלים וכלי עזר)

#### Tank.java (Abstract)
**תפקיד המחלקה:**
מחלקה מופשטת המגדירה את המאפיינים הבסיסיים של כל טנק (חיים, תחמושת, מהירות) ואת ממשק ה-`run` שעל כל בוט לממש.

**תכונות המחלקה:**
- `health`: כמות החיים הנוכחית של הטנק.
- `_direction`: כיוון גוף הטנק.
- `_turretDirection`: כיוון הצריח.
- `_ammo`: סוג התחמושת.

**פעולות המחלקה:**

**שם:** `fire`
**תוכן:**
```java
public void fire() {
    if (canFire()) {
        ammoCount--;
        // ... Shell creation logic ...
    }
}
```
**תיאור:** מבצעת ירי פגז ומקטינה את מלאי התחמושת.

---

#### Location.java / Direction.java
**תפקיד המחלקה:**
אובייקטים לניהול קואורדינטות (X, Y) וזוויות (0-360) במרחב המשחק.

**פעולות:**

**שם:** `move` (Location)
**תוכן:**
```java
public void move(Direction direction, Speed speed) {
    double rad = Math.toRadians(direction.getDegrees());
    this.x += (int)(Math.cos(rad) * speed.getSpeedVal());
    this.y += (int)(Math.sin(rad) * speed.getSpeedVal());
}
```
**תיאור:** מעדכנת את המיקום על סמך כיוון ומהירות.

---

#### GameBoardView.java
**תפקיד המחלקה:**
רכיב ממשק מותאם אישית (Custom View) האחראי על הציור הפיזי של שדה הקרב על המסך.

**פעולות:**

**שם:** `onDraw`
**תיאור:** הלב של הממשק הויזואלי. הפונקציה מציירת את הרקע, את גוף הטנקים, את הצריחים שלהם ואת הפיצוצים בזמן אמת על סמך היומנים.

---

#### Logs.java
**תפקיד:** אובייקט המתעד מצב טנק ברגע נתון (מיקום, כיוון, חיים) לצורך שידור חוזר.

---

#### MusicService.java
**תפקיד המחלקה:**
שירות הרץ ברקע ומנהל את מוזיקת המשחק בלולאה אינסופית.

---

---

<a name="ממשק-פנימי"></a>
### ממשק פנימי:
במחלקה `RoomManager` (אם הייתה קיימת בעבר, כאן נעשה שימוש בפרדיגמת ה-State וה-Callback) קיים ממשק פנימי המאפשר לממש פונקציות למדא לקריאת נתונים מחדר המשחק:
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

#### 2. טעינת בוטים
עליך לכתוב פונקציית `run` שתקבל את מצב הטנק ותחזיר פעולה.
**ה-API העומד לרשותך:**
- `move(speed)`: תנועה קדימה/אחורה.
- `rotate(degrees)`: סיבוב גוף הטנק.
- `rotateTurret(degrees)`: סיבוב הצריח.
- `fire()`: ירי פגז.

![מסך הוראות](pictures/Screenshot-20260427-19:17:23.png)

#### 3. צפייה בקרב
לאחר הרצת הקוד, תועבר לסימולטור הויזואלי שבו תוכל לראות את ביצועי הבוט שלך.

---

<a name="9-רפלקציה--סיכום-אישי"></a>
## 9. רפלקציה / סיכום אישי
פיתוח "Tanks Code Combat" היה מסע לימודי משמעותי עבורי. האתגר הטכני הגדול ביותר היה התעסקות בין המרה של בוט שכתוב בjs לבין לוגיקאת המשחק. למדתי לעומק על עבודה עם Realtime Database ועל חשיבות ההפרדה בין לוגיקת המשחק לתצוגה הויזואלית.

---

<a name="10-ביבליוגרפיה"></a>
## 10. ביבליוגרפיה
1. planet wars: https://wingfighter.fandom.com/wiki/Planet_Wars
2. code wars: https://www.codewars.com/
3. firebase realtime docometion: https://firebase.google.com/docs/database#how_does_it_work

---

<a name="11-נספחים"></a>
## 11. נספחים

### activity_log_in.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/background"
    tools:context=".LogIn">

    <EditText
        android:id="@+id/email"
        android:layout_width="250dp"
        android:layout_height="48dp"
        android:ems="10"
        android:inputType="textEmailAddress"
        android:hint="email"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.211" />

    <EditText
        android:id="@+id/password"
        android:layout_width="250dp"
        android:layout_height="48dp"
        android:ems="10"
        android:inputType="textPassword"
        android:hint="password"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.31" />

    <TextView
        android:id="@+id/massage"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="login"
        android:textSize="50sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.051" />

    <Button
        android:id="@+id/SignIn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="Login"
        android:text="login"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.306"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.427" />

    <Button
        android:id="@+id/signin"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="signin"
        android:onClick="goToSignIn"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.668"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.427" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### activity_sign_in.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/background"
    tools:context=".SignIn">


    <EditText
        android:id="@+id/email"
        android:layout_width="250dp"
        android:layout_height="48dp"
        android:ems="10"
        android:inputType="textEmailAddress"
        android:hint="email"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.211" />

    <EditText
        android:id="@+id/password"
        android:layout_width="250dp"
        android:layout_height="48dp"
        android:ems="10"
        android:inputType="textPassword"
        android:hint="password"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.31" />

    <TextView
        android:id="@+id/massage"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="sign in"
        android:textSize="50sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.051" />

    <Button
        android:id="@+id/SignIn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="createUser"
        android:text="sign in"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.323"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.428" />

    <Button
        android:id="@+id/login"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="login"
        android:onClick="goToLogin"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.668"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.428" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### activity_home_page.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <FrameLayout
        android:id="@+id/fragmentContainer"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/bottomNavigation"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/>

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottomNavigation"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:menu="@menu/bottom_menu"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

### activity_visual_game.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".VisualGame">

    <com.example.tankscodecombat.GameBoardView
        android:id="@+id/gameBoard"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:background="#000000"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@+id/buttonsScrollView"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <HorizontalScrollView
        android:id="@+id/buttonsScrollView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <LinearLayout
            android:id="@+id/buttonsLayout"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:gravity="center"
            android:padding="12dp"
            android:background="#222222">

            <Button
                android:id="@+id/zoomOut"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Zoom Out" />

            <Button
                android:id="@+id/skipStart"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Start" />

            <Button
                android:id="@+id/skipBack10"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="-10" />

            <Button
                android:id="@+id/prevButton"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Prev" />

            <Button
                android:id="@+id/nextButton"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Next" />

            <Button
                android:id="@+id/skipForward10"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="+10" />

            <Button
                android:id="@+id/skipEnd"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="End" />

            <Button
                android:id="@+id/zoomIn"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Zoom In" />
        </LinearLayout>
    </HorizontalScrollView>

</androidx.constraintlayout.widget.ConstraintLayout>
```

### fragment_main.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/tank_background"
    android:layoutDirection="ltr"

    tools:context=".MainActivityFragment">

    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="44dp"
        android:shadowColor="@android:color/white"
        android:shadowDx="2"
        android:shadowDy="2"
        android:shadowRadius="7"
        android:text="tanks code combat"
        android:textColor="@color/black"
        android:textSize="40sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/btnSelectBot1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Select Bot 1"
        app:layout_constraintTop_toBottomOf="@id/title"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp"/>

    <TextView
        android:id="@+id/tvBot1Status"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="No file selected"
        android:textColor="@android:color/white"
        android:shadowColor="@android:color/black"
        android:shadowRadius="3"
        app:layout_constraintTop_toBottomOf="@id/btnSelectBot1"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="8dp"/>

    <Button
        android:id="@+id/btnSelectBot2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Select Bot 2"
        app:layout_constraintTop_toBottomOf="@id/tvBot1Status"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp"/>

    <TextView
        android:id="@+id/tvBot2Status"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="No file selected"
        android:textColor="@android:color/white"
        android:shadowColor="@android:color/black"
        android:shadowRadius="3"
        app:layout_constraintTop_toBottomOf="@id/btnSelectBot2"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="8dp"/>

    <ImageButton
        android:id="@+id/startGame"
        android:layout_width="108dp"
        android:layout_height="120dp"
        android:background="@android:color/transparent"
        android:src="@drawable/explosion"
        android:contentDescription="ScoreBoard"
        android:padding="8dp"
        android:scaleType="fitCenter"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.445" />

    <TextView
        android:id="@+id/textView3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="start"
        android:textColor="@color/black"
        android:textSize="20dp"
        android:background="#22FFFFFF"
        android:padding="2dp"
        app:layout_constraintStart_toStartOf="@id/startGame"
        app:layout_constraintEnd_toEndOf="@id/startGame"
        app:layout_constraintTop_toTopOf="@id/startGame"
        app:layout_constraintBottom_toBottomOf="@id/startGame"/>


</androidx.constraintlayout.widget.ConstraintLayout>
```

### fragment_instructions.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
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
            android:layout_marginBottom="16dp"
            android:gravity="center"
            android:text="Tanks Code Combat - API Guide"
            android:textColor="#FFD700"
            android:textSize="24sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="24dp"
            android:text="Your bot's 'run' function must return an 'Action' object. Use the following commands to dominate the battlefield:"
            android:textColor="#FFFFFF"
            android:textSize="16sp" />

        <!-- MOVE -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="1. MOVE"
            android:textColor="#00FF00"
            android:textSize="18sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="12dp"
            android:text="Usage: new Action(Action.ActionType.MOVE, speed)\nSpeeds: -1 (Reverse), 0 (Stop), 1 (Slow), 2 (Fast)"
            android:textColor="#CCCCCC" />

        <!-- ROTATE -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="2. ROTATE"
            android:textColor="#00FF00"
            android:textSize="18sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="12dp"
            android:text="Usage: new Action(Action.ActionType.ROTATE, degrees)\nDescription: Rotates the tank chassis by the given degrees."
            android:textColor="#CCCCCC" />

        <!-- ROTATE TURRET -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="3. ROTATE TURRET"
            android:textColor="#00FF00"
            android:textSize="18sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="12dp"
            android:text="Usage: new Action(Action.ActionType.ROTATE_TURRET, degrees)\nDescription: Rotates the gun turret independently."
            android:textColor="#CCCCCC" />

        <!-- FIRE -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="4. FIRE"
            android:textColor="#FF4500"
            android:textSize="18sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="12dp"
            android:text="Usage: new Action(Action.ActionType.FIRE)\nDescription: Fires a projectile from the current turret direction."
            android:textColor="#CCCCCC" />

        <!-- RADAR -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="5. RADAR (State Data)"
            android:textColor="#1E90FF"
            android:textSize="18sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="24dp"
            android:text="The 'TankState' object provides real-time intel:\n- state.radar: Direction to opponent\n- state.distance: Distance to opponent"
            android:textColor="#CCCCCC" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="Good luck, Commander!"
            android:textColor="#FFD700"
            android:textSize="16sp"
            android:textStyle="italic" />

    </LinearLayout>
</ScrollView>
```

### fragment_logout.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".LogoutFragment">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="@string/hello_blank_fragment" />

</FrameLayout>
```

### Action.java:
```java
package com.example.tankscodecombat;

public class Action {
        public enum ActionType { MOVE, ROTATE, ROTATE_TURRET, FIRE, RELOAD }
    private int param;
    private ActionType type;

    public Action(ActionType type, int param) {
        this.type = type;
        this.param = param;
    }

    public Action(ActionType type) {
        this(type, 0);
    }

    public ActionType getType() {
        return type;
    }

    public int getParam() {
        return param;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public void setParam(int param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "Action{" +
                "type: " + type +
                ", param: " + param +
                '}';
    }
}
```

### Board.java:
```java
package com.example.tankscodecombat;

import android.util.Log;
import java.util.Random;

public class Board {
    Random ran = new Random();
    private final int SIZE = 100;
    private final Tank[] m_tanks;
    private static Location[] m_tanksLocation;
    public Board(Tank[] tanks) {
        m_tanks = tanks;

        m_tanksLocation = new Location[2];
        int minDistance = 10;
        int maxDistance = 30;
        int x1, y1, x2, y2;
        do {
            x1 = ran.nextInt(SIZE);
            y1 = ran.nextInt(SIZE);
            x2 = ran.nextInt(SIZE);
            y2 = ran.nextInt(SIZE);
        } while (Math.hypot(x1 - x2, y1 - y2) < minDistance || Math.hypot(x1 - x2, y1 - y2) > maxDistance);
        m_tanksLocation[0] = new Location(x1, y1);
        m_tanksLocation[1] = new Location(x2, y2);
        
        m_tanks[0].set_direction(ran.nextInt(360));
        m_tanks[1].set_direction(ran.nextInt(360));

        Log.d("debug", "Initial tank positions: Tank0=" + m_tanksLocation[0] +
                " Tank1=" + m_tanksLocation[1]);
    }

    public int tankActionToBoard(int tankId, Action action, boolean canAct) {

        if (!canAct) return 0;

        Tank tank = m_tanks[tankId];
        Location loc = m_tanksLocation[tankId];

        if (action.getType() == Action.ActionType.MOVE) {
            loc.move(tank.get_direction(), tank.get_speed());

            Log.d("debug",
                    "Tank " + tankId +
                            " MOVE to " + loc +
                            " dir=" + tank.get_direction().getDegrees() +
                            " speed=" + tank.get_speed()
            );
        }
        else {
            Log.d("debug",
                    "Tank " + tankId +
                            " performed " + action.getType() +
                            " at " + loc
            );
        }

        if (action.getType() == Action.ActionType.FIRE) {
            Direction radar = getRadar(tankId);
            Direction turret = tank.get_turretDirection();

            int diff = Math.abs(radar.getDegrees() - turret.getDegrees()) % 360;
            if (diff <= 10 || diff >= 350) {
                Log.d("debug", "Tank " + tankId + " HIT!");
                int enemy = 1 - tankId;
                int damage = tank.get_ammo().getDamage();
                m_tanks[enemy].setHealth(m_tanks[enemy].getHealth() - damage);
                if (m_tanks[enemy].getHealth() <= 0) {
                    return tankId + 1;
                }
            }
        }

        return 3;
    }

    public static Location getLocation(int tankId) {
        return m_tanksLocation[tankId];
    }

    public static Direction getRadar(int tankId) {
        int enemy = 1 - tankId;
        double dx = m_tanksLocation[enemy].getX() - m_tanksLocation[tankId].getX();
        double dy = m_tanksLocation[enemy].getY() - m_tanksLocation[tankId].getY();
        return new Direction((int)Math.toDegrees(Math.atan2(dy, dx)));
    }
    
    public static double getDistance(int tankId) {
        int enemy = 1 - tankId;
        double dx = m_tanksLocation[enemy].getX() - m_tanksLocation[tankId].getX();
        double dy = m_tanksLocation[enemy].getY() - m_tanksLocation[tankId].getY();
        return Math.hypot(dx, dy);
    }
}
```

### Direction.java:
```java
package com.example.tankscodecombat;

import java.util.Objects;

public class Direction {
    private int degrees; // always 0 - 360

    public Direction(int degrees) {
        // keep it between 0 - 360
        this.degrees = ((degrees % 360) + 360) % 360;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = ((degrees % 360) + 360) % 360;
    }

    public Direction rotate(int delta) {
        // keep it between 0 - 360
        return new Direction((((degrees + delta) % 360) + 360) % 360);
    }

    @Override
    public String toString() {
        return degrees + "°";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return degrees == direction.degrees;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(degrees);
    }
}
```

### Game.java:
```java
package com.example.tankscodecombat;

import android.content.Context;
import android.util.Log;

public class Game {
    private final Context context;
    public final int MAX_NUMBER_OF_TURNS = 100;
    private final Tank[] m_tanks;
    private Logs[] m_logs;
    private final TankHandler m_tankHandler;

    public Game(Context context, Tank bot1, Tank bot2) throws Exception {
        Log.d("debug", "Game has started");

        this.context = context;

        m_tanks = new Tank[2];
        m_tanks[0] = bot1;
        m_tanks[1] = bot2;
        m_tankHandler = new TankHandler(m_tanks);
    }

    public int run() {
        m_logs = new Logs[MAX_NUMBER_OF_TURNS * 2];

        int turn = 0;
        int game_state = 3;
        for (turn = 0; turn < MAX_NUMBER_OF_TURNS; turn++) {

            Action action1;
            try {
                TankState state1 = m_tanks[0].getState(Board.getRadar(0), Board.getDistance(0));
                action1 = m_tanks[0].run(state1);
            } catch (Exception e) {
                action1 = new Action(Action.ActionType.RELOAD, 0);
            }
            game_state = m_tankHandler.action(0, action1);
            document(turn, 0, action1);

            if (game_state != 3) break;

            Action action2;
            try {
                TankState state2 = m_tanks[1].getState(Board.getRadar(1), Board.getDistance(1));
                action2 = m_tanks[1].run(state2);
            } catch (Exception e) {
                action2 = new Action(Action.ActionType.RELOAD, 0);
            }
            game_state = m_tankHandler.action(1, action2);
            document(turn, 1, action2);

            if (game_state != 3) break;
        }

        if (turn >= MAX_NUMBER_OF_TURNS)
        {
            Log.d("debug", "MAX_NUMBER_OF_TURNS reached");
            game_state = -1;
        }
        return game_state;
    }

    private void document(int turnIndex, int tankId, Action action) {
        int logIndex = turnIndex * 2 + tankId;
        if (logIndex >= 0 && logIndex < m_logs.length) {
            m_logs[logIndex] = new Logs(tankId, action, Board.getLocation(tankId), m_tanks[tankId].get_direction(), m_tanks[tankId].get_turretDirection(), m_tanks[tankId].getHealth());
        }
    }

    public Logs[] getLog() {
        return m_logs;
    }
}
```

### GameBoardView.java:
```java
package com.example.tankscodecombat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class GameBoardView extends View {

    private final int GRID_SIZE = 100;
    private final Paint gridPaint = new Paint();
    private final Paint tank1Paint = new Paint();
    private final Paint tank2Paint = new Paint();
    private final Paint tankDirPaint = new Paint();
    private final Paint turretDirPaint = new Paint();
    private final Paint bulletPaint = new Paint();
    private final Paint healthBarPaint = new Paint();
    private final Paint smokePaint = new Paint();
    private final Paint turnIndicatorPaint = new Paint();

    private Bitmap backgroundBitmap;
    private Bitmap explosionBitmap;
    private Rect viewRect = new Rect(0, 0, getWidth(), getHeight());

    private Logs[] logs = new Logs[0];
    private int currentIndex = 0;

    private float cachedMinX, cachedMinY, cachedMaxX, cachedMaxY;
    private float cachedScale, cachedOffsetX, cachedOffsetY;
    private int cachedCurrentIndex = -1;

    private float zoomFactor = 1.0f;
    private ScaleGestureDetector scaleDetector;

    public GameBoardView(Context context) {
        super(context);
        init();
    }

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gridPaint.setColor(Color.DKGRAY);
        gridPaint.setStrokeWidth(2);

        tank1Paint.setColor(Color.RED);
        tank2Paint.setColor(Color.GREEN);

        tankDirPaint.setColor(Color.YELLOW); // tank facing
        tankDirPaint.setStrokeWidth(3);

        turretDirPaint.setColor(Color.CYAN); // turret facing
        turretDirPaint.setStrokeWidth(2);

        bulletPaint.setColor(Color.WHITE);
        bulletPaint.setStrokeWidth(3);

        healthBarPaint.setStyle(Paint.Style.FILL);

        smokePaint.setColor(Color.GRAY);
        smokePaint.setAlpha(100);

        turnIndicatorPaint.setColor(Color.WHITE);
        turnIndicatorPaint.setTextSize(40);
        turnIndicatorPaint.setTextAlign(Paint.Align.CENTER);

        // Load bitmaps
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tank_background);
        explosionBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.explosion);

        scaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    public void setLogs(Logs[] logs) {
        this.logs = logs != null ? logs : new Logs[0];
        currentIndex = 0;
        invalidate();
    }

    public boolean isAtEnd() {
        return logs.length > 0 && currentIndex == logs.length - 1;
    }

    public int getLogsCount() {
        return logs.length;
    }

    public void nextMove() {
        if (currentIndex < logs.length - 1) currentIndex++;
        invalidate();
    }

    public void prevMove() {
        if (currentIndex > 0) currentIndex--;
        invalidate();
    }

    public void skipForward10() {
        currentIndex = Math.min(currentIndex + 10, logs.length - 1);
        invalidate();
    }

    public void skipBackward10() {
        currentIndex = Math.max(currentIndex - 10, 0);
        invalidate();
    }

    public void skipToStart(View v) {
        currentIndex = 0;
        invalidate();
    }

    public void skipToEnd() {
        currentIndex = logs.length - 1;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw background
        if (backgroundBitmap != null) {
            canvas.drawBitmap(backgroundBitmap, null, viewRect, null);
        } else {
            canvas.drawColor(Color.BLACK);
        }

        if (logs.length == 0) return;

        if (currentIndex != cachedCurrentIndex) {
            // --- Compute bounds around tanks ---
            float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
            float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;

            for (int i = 0; i <= currentIndex; i++) {
                Logs log = logs[i];
                if (log == null) continue;
                Location loc = log.get_location();
                if (loc == null) continue;

                minX = Math.min(minX, loc.getX());
                minY = Math.min(minY, loc.getY());
                maxX = Math.max(maxX, loc.getX());
                maxY = Math.max(maxY, loc.getY());
            }

            // Add padding
            float padding = 10f;
            minX -= padding; minY -= padding;
            maxX += padding; maxY += padding;

            float width = maxX - minX;
            float height = maxY - minY;

            // Apply zoom factor to make view less zoomed-in
            float scaleX = getWidth() / width * zoomFactor;
            float scaleY = getHeight() / height * zoomFactor;
            float scale = Math.min(scaleX, scaleY);

            // Centering
            float offsetX = getWidth()/2f - (minX + width/2f) * scale;
            float offsetY = getHeight()/2f - (minY + height/2f) * scale;

            cachedMinX = minX; cachedMinY = minY; cachedMaxX = maxX; cachedMaxY = maxY;
            cachedScale = scale; cachedOffsetX = offsetX; cachedOffsetY = offsetY;
            cachedCurrentIndex = currentIndex;
        }

        // Draw grid lines every 10 units
        for (int gx = (int)Math.floor(cachedMinX / 10) * 10; gx <= cachedMaxX; gx += 10) {
            float screenX = gx * cachedScale + cachedOffsetX;
            canvas.drawLine(screenX, 0, screenX, getHeight(), gridPaint);
        }
        for (int gy = (int)Math.floor(cachedMinY / 10) * 10; gy <= cachedMaxY; gy += 10) {
            float screenY = gy * cachedScale + cachedOffsetY;
            canvas.drawLine(0, screenY, getWidth(), screenY, gridPaint);
        }

        // Draw tanks
        for (int i = 0; i <= currentIndex; i++) {
            Logs log = logs[i];
            if (log == null) continue;
            Location loc = log.get_location();
            if (loc == null) continue;

            float x = loc.getX() * cachedScale + cachedOffsetX;
            float y = loc.getY() * cachedScale + cachedOffsetY;

            Paint paint = log.get_tankId() == 1 ? tank1Paint : tank2Paint;

            float size = cachedScale * 3; // tank square size
            canvas.drawRect(x, y, x + size, y + size, paint);

            float centerX = x + size / 2;
            float centerY = y + size / 2;

            // Tank direction
            Direction tankDir = log.get_tankDirection();
            if (tankDir != null) {
                double rad = Math.toRadians(tankDir.getDegrees());
                float lineLen = cachedScale * 5;
                canvas.drawLine(centerX, centerY,
                        (float) (centerX + lineLen * Math.cos(rad)),
                        (float) (centerY + lineLen * Math.sin(rad)),
                        tankDirPaint);
            }

            // Turret direction
            Direction turretDir = log.get_turretDirection();
            if (turretDir != null) {
                double rad = Math.toRadians(turretDir.getDegrees());
                float lineLen = cachedScale * 4;
                canvas.drawLine(centerX, centerY,
                        (float) (centerX + lineLen * Math.cos(rad)),
                        (float) (centerY + lineLen * Math.sin(rad)),
                        turretDirPaint);
            }

            // Draw bullet if firing
            if (log.get_action().getType() == Action.ActionType.FIRE) {
                if (turretDir != null) {
                    double rad = Math.toRadians(turretDir.getDegrees());
                    float bulletLen = cachedScale * 20;
                    canvas.drawLine(centerX, centerY,
                            (float) (centerX + bulletLen * Math.cos(rad)),
                            (float) (centerY + bulletLen * Math.sin(rad)),
                            bulletPaint);
                }
            }

            // Draw health bar
            float health = log.get_health();
            float healthBarWidth = size;
            float healthBarHeight = cachedScale / 4;
            float healthX = x;
            float healthY = y - healthBarHeight - 2;

            // Background red
            healthBarPaint.setColor(Color.RED);
            canvas.drawRect(healthX, healthY, healthX + healthBarWidth, healthY + healthBarHeight, healthBarPaint);

            // Foreground green/yellow/red
            if (health > 66) healthBarPaint.setColor(Color.GREEN);
            else if (health > 33) healthBarPaint.setColor(Color.YELLOW);
            else healthBarPaint.setColor(Color.RED);
            canvas.drawRect(healthX, healthY, healthX + healthBarWidth * (health / 100f), healthY + healthBarHeight, healthBarPaint);

            // Draw smoke if low health
            if (health < 50) {
                canvas.drawCircle(centerX - cachedScale, centerY - cachedScale, cachedScale, smokePaint);
                canvas.drawCircle(centerX + cachedScale, centerY - cachedScale, cachedScale, smokePaint);
            }

            // Draw explosion if health <=0
            if (health <= 0 && explosionBitmap != null) {
                canvas.drawBitmap(explosionBitmap, x - size/2, y - size/2, null);
            }
        }

        // Draw turn indicator
        String turnText = "Turn: Tank " + ((currentIndex % 2) + 1);
        canvas.drawText(turnText, getWidth() / 2, 50, turnIndicatorPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleDetector.onTouchEvent(event);
        return true;
    }

    public void zoomIn() {
        zoomFactor *= 1.2f;
        zoomFactor = Math.min(zoomFactor, 5.0f);
        refresh();
    }

    private void refresh() {
        ((VisualGame) getContext()).runOnUiThread(this::invalidate);
    }

    public void zoomOut() {
        zoomFactor /= 1.2f;
        zoomFactor = Math.max(zoomFactor, 0.1f);
        refresh();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            zoomFactor *= detector.getScaleFactor();
            zoomFactor = Math.max(0.1f, Math.min(zoomFactor, 5.0f));
            refresh();
            return true;
        }
    }
}
```

### HomePage.java:
```java
package com.example.tankscodecombat;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_logout) {
                loadFragment(new LogoutFragment());
                return true;
            }
            else if (id == R.id.nav_main) {
                loadFragment(new MainActivityFragment());
                return true;
            }
            else if (id == R.id.nav_instructions) {
                loadFragment(new InstructionsFragment());
                return true;
            }

            return false;
        });
        loadFragment(new MainActivityFragment());
        startService(new Intent(this, MusicService.class));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, MusicService.class));
        super.onDestroy();
    }
}
```

### InstructionsFragment.java:
```java
package com.example.tankscodecombat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class InstructionsFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public InstructionsFragment() {
        // Required empty public constructor
    }


    public static InstructionsFragment newInstance(String param1, String param2) {
        InstructionsFragment fragment = new InstructionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructions, container, false);
    }
}
```

### JSBotTank.java:
```java
package com.example.tankscodecombat;

import org.mozilla.javascript.*;

public class JSBotTank extends Tank {

    private final Scriptable scope;
    private final Function runFunction;

    public JSBotTank(String jsCode) {
        Context cx = Context.enter();
        try {
            cx.setOptimizationLevel(-1);
            cx.setClassShutter(new ClassShutter() {
                @Override
                public boolean visibleToScripts(String className) {
                    return false;
                }
            });

            scope = cx.initStandardObjects();
            cx.evaluateString(scope, jsCode, "bot", 1, null);

            Object obj = scope.get("run", scope);
            if (!(obj instanceof Function)) {
                throw new RuntimeException("JS bot must define function run()");
            }
            runFunction = (Function) obj;
        } finally {
            Context.exit();
        }
    }

    @Override
    public Action run(TankState state) {
        Context cx = Context.enter();
        try {
            cx.setOptimizationLevel(-1);

            NativeObject jsState = new NativeObject();
            jsState.put("radar", jsState, state.radar.getDegrees());
            jsState.put("direction", jsState, state.direction.getDegrees());
            jsState.put("turret", jsState, state.turret.getDegrees());
            jsState.put("ammo", jsState, state.ammo.ordinal());
            jsState.put("ammoName", jsState, state.ammo.name());
            jsState.put("ammoCount", jsState, state.ammoCount);
            jsState.put("speed", jsState, state.speed.getSpeedVal());
            jsState.put("health", jsState, state.health);
            jsState.put("distance", jsState, (int)state.distance);

            Object result = runFunction.call(cx, scope, scope, new Object[]{ jsState });
            return parseAction(result);
        } catch (Exception e) {
            return new Action(Action.ActionType.RELOAD, 0);
        } finally {
            Context.exit();
        }
    }

    private Action parseAction(Object result) {
        if (!(result instanceof NativeObject)) {
            return new Action(Action.ActionType.RELOAD, 0);
        }

        NativeObject obj = (NativeObject) result;

        Object typeObj = obj.get("type", obj);
        if (typeObj == null) {
            return new Action(Action.ActionType.RELOAD, 0);
        }

        String type = typeObj.toString();
        int param = 0;

        if (obj.has("param", obj)) {
            Object p = obj.get("param", obj);
            if (p instanceof Number) {
                param = ((Number) p).intValue();
            }
        }

        try {
            return new Action(Action.ActionType.valueOf(type), param);
        } catch (IllegalArgumentException e) {
            return new Action(Action.ActionType.RELOAD, 0);
        }
    }

}
```

### Location.java:
```java
package com.example.tankscodecombat;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(Direction direction, Tank.Speed speed) {
        int amount = speed.getSpeedVal(); // FAST=2, SLOW=1, REVERSE=-1, STOP=0

        // x += distance * cos(degree)
        // y += distance * sin
        double rad = Math.toRadians(direction.getDegrees());
        setX(getX() + (int)((amount * 10) * cos(rad)));
        setY(getY() + (int)((amount * 10) * sin(rad)));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
```

### LogIn.java:
```java
package com.example.tankscodecombat;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.content.SharedPreferences;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import android.content.Intent;

public class LogIn extends AppCompatActivity {
    private EditText ETpassword;
    private EditText ETemail;
    private FirebaseAuth ref;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ETpassword = findViewById(R.id.password);
        ETemail = findViewById(R.id.email);
        ref = FirebaseAuth.getInstance();

        // Initialize SharedPreferences
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        // Check if a user is already saved in SharedPreferences
        String savedUid = prefs.getString("uid", null);
        if (savedUid != null) {
            // go to MainActivity automatically
            Intent intent = new Intent(LogIn.this, HomePage.class);
            startActivity(intent);
            finish();
        }
    }

    public void Login(View view) {
        String email = ETemail.getText().toString().trim();
        String password = ETpassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LogIn.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Connecting...");
            pd.setMessage("Signing in...");
            pd.show();

            // sign in with Firebase Authentication
            ref.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    pd.dismiss();
                    if (task.isSuccessful()) {
                        Log.i("LogIn", "signInWithEmailAndPassword:success");
                        FirebaseUser user = ref.getCurrentUser();
                        Toast.makeText(LogIn.this, "Login successful\nUid: " + user.getUid(), Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("uid", user.getUid());
                        editor.apply();

                        Log.d("debug", "main has started");

                        // go to MainActivity after successful login
                        Intent intent = new Intent(LogIn.this, HomePage.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Exception exp = task.getException();
                        if (exp instanceof FirebaseAuthInvalidUserException) {
                            Toast.makeText(LogIn.this, "Invalid email address.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(LogIn.this, "Incorrect password.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseNetworkException) {
                            Toast.makeText(LogIn.this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LogIn.this, "Authentication failed: " + exp.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    public void goToSignIn(View view) {
        // go to sign in
        Intent intent = new Intent(LogIn.this, SignIn.class);
        startActivity(intent);
        finish();
    }
}
```

### LogoutFragment.java:
```java
package com.example.tankscodecombat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Delay by 1 frame to avoid fragment transaction conflicts
        new Handler(Looper.getMainLooper()).post(() -> {
            // Log out from Firebase
            FirebaseAuth.getInstance().signOut();

            // Clear saved UID from SharedPreferences
            SharedPreferences prefs = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
            prefs.edit().remove("uid").apply(); // remove only the UID

            // Redirect to LogIn and clear back stack
            Intent intent = new Intent(requireActivity(), LogIn.class);
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );
            startActivity(intent);
        });
    }
}
```

### Logs.java:
```java
package com.example.tankscodecombat;

public class Logs {
    private int tankId;
    private Action action;
    private Location location;
    private Direction tankDirection;
    private Direction turretDirection;
    private int health;

    public Logs() {
        // default constructor for loading
    }

    public Logs(int tankId, Action action, Location location, Direction tankDirection, Direction turretDirection, int health) {
        this.tankId = tankId;
        this.action = action;
        this.location = location;
        this.tankDirection = tankDirection;
        this.turretDirection = turretDirection;
        this.health = health;
    }

    public int get_tankId() {
        return tankId;
    }

    public Action get_action() {
        return action;
    }

    public Location get_location() {
        return location;
    }

    public Direction get_tankDirection() {
        return tankDirection;
    }

    public Direction get_turretDirection() {
        return turretDirection;
    }

    public int get_health() {
        return health;
    }

    public void set_tankId(int tankId) {
        this.tankId = tankId;
    }

    public void set_action(Action action) {
        this.action = action;
    }

    public void set_location(Location location) {
        this.location = location;
    }

    public void set_tankDirection(Direction tankDirection) {
        this.tankDirection = tankDirection;
    }

    public void set_turretDirection(Direction turretDirection) {
        this.turretDirection = turretDirection;
    }

    public void set_health(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Log{" +
                "tankId=" + tankId +
                ", action=" + action +
                ", location=" + location +
                ", tankDirection=" + tankDirection.getDegrees() +
                ", turretDirection=" + turretDirection.getDegrees() +
                ", health=" + health +
                '}';
    }
}
```

### MainActivityFragment.java:
```java
package com.example.tankscodecombat;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivityFragment extends Fragment {
    public static String BOT1_CODE_KEY = "BOT1_CODE";
    public static String BOT2_CODE_KEY = "BOT2_CODE";

    private String bot1Code, bot2Code;
    private TextView tvBot1Status, tvBot2Status;
    private int botSlotToLoad;

    private final ActivityResultLauncher<String> selectBot1Launcher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    bot1Code = readJsFile(uri);
                    if (bot1Code != null) {
                        tvBot1Status.setText("Bot 1 Ready!");
                        showSaveDialog(bot1Code);
                    } else {
                        tvBot1Status.setText("Error loading");
                    }
                }
            });

    private final ActivityResultLauncher<String> selectBot2Launcher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    bot2Code = readJsFile(uri);
                    if (bot2Code != null) {
                        tvBot2Status.setText("Bot 2 Ready!");
                        showSaveDialog(bot2Code);
                    } else {
                        tvBot2Status.setText("Error loading");
                    }
                }
            });

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable android.os.Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        tvBot1Status = view.findViewById(R.id.tvBot1Status);
        tvBot2Status = view.findViewById(R.id.tvBot2Status);

        view.findViewById(R.id.btnSelectBot1).setOnClickListener(this::loadBot1);

        view.findViewById(R.id.btnSelectBot2).setOnClickListener(this::loadBot2);

        view.findViewById(R.id.startGame).setOnClickListener(this::startGame);

        return view;
    }

    private void startGame(View v) {
        if (bot1Code == null || bot2Code == null) {
            Toast.makeText(requireContext(),
                    "Please select both bots first!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(requireActivity(), VisualGame.class);
        intent.putExtra(BOT1_CODE_KEY, bot1Code);
        intent.putExtra(BOT2_CODE_KEY, bot2Code);

        Log.d("debug", "Starting VisualGame with JS bots");
        startActivity(intent);
    }

    private void loadBot1(View v) {
        String[] items = {"select from db", "select from device"};

        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setItems(items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            botSlotToLoad = 1;
                            loadBotFromDB();
                            break;
                        case 1:
                            selectBot1Launcher.launch("*/*");
                            break;
                        case 2:
                            break;
                    }
                })
                .create();

        alertDialog.show();
    }

    private void loadBot2(View v) {
        String[] items = {"select from db", "select from device"};

        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setItems(items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            botSlotToLoad = 2;
                            loadBotFromDB();
                            break;
                        case 1:
                            selectBot2Launcher.launch("*/*");
                            break;
                        case 2:
                            break;
                    }
                })
                .create();

        alertDialog.show();
    }
    private String readJsFile(Uri uri) {
        try (InputStream in = requireContext().getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadBotFromDB() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(requireContext(), "Please log in first", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tankscodecombat-default-rtdb.firebaseio.com");
        DatabaseReference userBotsReference = database.getReference("users").child(user.getUid()).child("bots");

        userBotsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> bots = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        bots.add(snapshot.getKey());
                    }
                }

                if (bots.isEmpty()) {
                    Toast.makeText(requireContext(), "No bots found in database", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] botsArray = bots.toArray(new String[0]);
                new AlertDialog.Builder(requireContext())
                        .setTitle("Select a Bot")
                        .setItems(botsArray, (dialog, which) -> {
                            String selectedBot = bots.get(which);
                            getBotCodeFromDB(selectedBot);
                        }).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to load bots", error.toException());
                Toast.makeText(requireContext(), "Failed to load bots: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getBotCodeFromDB(String botName) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tankscodecombat-default-rtdb.firebaseio.com");
        DatabaseReference botCodeReference = database.getReference("users").child(user.getUid()).child("bots").child(botName);

        botCodeReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                if (snapshot != null && snapshot.exists()) {
                    String code = snapshot.child("code").getValue(String.class);

                    if (code != null) {
                        if (botSlotToLoad == 1) {
                            bot1Code = code;
                            tvBot1Status.setText("Bot 1 Ready!");
                        } else if (botSlotToLoad == 2) {
                            bot2Code = code;
                            tvBot2Status.setText("Bot 2 Ready!");
                        }
                        Toast.makeText(requireContext(), "Bot loaded successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Bot code is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Bot not found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Exception e = task.getException();
                Log.e("Firebase", "Error getting bot code", e);
                String errorMessage = (e != null) ? e.getMessage() : "Unknown error";
                Toast.makeText(requireContext(), "Error taking bot from db: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSaveDialog(String code) {
        EditText input = new EditText(requireContext());
        input.setHint("Enter bot name");

        new AlertDialog.Builder(requireContext())
                .setTitle("Save Bot to Database?")
                .setMessage("Do you want to save this bot for future use?")
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {
                    String botName = input.getText().toString().trim();
                    if (!botName.isEmpty()) {
                        saveBotToDB(botName, code);
                    } else {
                        Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void saveBotToDB(String botName, String code) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return;

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tankscodecombat-default-rtdb.firebaseio.com");
        DatabaseReference botRef = database.getReference("users")
                .child(user.getUid())
                .child("bots")
                .child(botName);

        Map<String, Object> botData = new HashMap<>();
        botData.put("code", code);

        botRef.setValue(botData).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "Bot saved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("Firebase", "Failed to save bot", task.getException());
                Toast.makeText(requireContext(), "Failed to save bot to DB", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

### MusicService.java:
```java
package com.example.tankscodecombat;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.music);
        player.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
```

### SignIn.java:
```java
package com.example.tankscodecombat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private EditText ETpassword;
    private EditText ETemail;
    private FirebaseAuth ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ETpassword = findViewById(R.id.password);
        ETemail = findViewById(R.id.email);
        ref = FirebaseAuth.getInstance();
    }

    public void createUser(View view) {
        String email = ETemail.getText().toString();
        String password = ETpassword.getText().toString();
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignIn.this, "please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Connecting...");
            pd.setMessage("connecting user...");
            pd.show();
            ref.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    pd.dismiss();
                    if (task.isSuccessful()) {
                        Log.i("MainActivity", "createUserWithEmailAndPassword:success");
                        FirebaseUser user = ref.getCurrentUser();
                        Toast.makeText(SignIn.this, "User created successfully\nUid: " + user.getUid(), Toast.LENGTH_SHORT).show();

                        // go to MainActivity after successful sign in
                        Intent intent = new Intent(SignIn.this, HomePage.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Exception exp = task.getException();
                        if (exp instanceof FirebaseAuthInvalidUserException) {
                            Toast.makeText(SignIn.this, "invalid email address.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseAuthWeakPasswordException) {
                            Toast.makeText(SignIn.this, "Password too weak.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignIn.this, "User already exists.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(SignIn.this, "General authentication failure.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseNetworkException) {
                            Toast.makeText(SignIn.this, "Network error. please check your connection.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignIn.this, "An error occured. please try again later.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
        }
    }

    public void goToLogin(View view) {
        // go to login
        Intent intent = new Intent(SignIn.this, LogIn.class);
        startActivity(intent);
        finish();
    }
}
```

### Tank.java:
```java
package com.example.tankscodecombat;

public abstract class Tank {
    public enum Ammo {
        NONE(0, 0),
        BULLET(5, 999),
        SHOTGUN(3, 3),
        MISSILE(15, 1);
        
        private final int damage;
        private final int magazineSize;
        
        Ammo(int damage, int magazineSize) {
            this.damage = damage;
            this.magazineSize = magazineSize;
        }
        public int getDamage() { return damage; }
        public int getMagazineSize() { return magazineSize; }
    }
    
    public enum Speed { REVERSE(-1), STOP(0), SLOW(1), FAST(2);
        private final int speedVal;
        Speed(int speedVal) { this.speedVal = speedVal; }
        public int getSpeedVal() { return speedVal; }
    }

    private Speed _speed = Speed.STOP;
    private Direction _direction = new Direction(0);
    private Direction _turretDirection = new Direction(0);
    private Ammo _ammo = Ammo.BULLET;
    private int ammoCount = 999;
    private int health = 50;

    public Ammo get_ammo() { return _ammo; }
    public int get_ammoCount() { return ammoCount; }
    public Direction get_direction() { return _direction; }
    public Direction get_turretDirection() { return _turretDirection; }
    public Speed get_speed() { return _speed; }

    public void set_direction(int direction) { _direction.setDegrees(direction); }
    public void set_turretDirection(int direction) { _turretDirection.setDegrees(direction); }
    public void set_speed(int speed) {
        Speed[] speeds = Speed.values();
        if (speed >= 0 && speed < speeds.length) _speed = speeds[speed];
    }
    public void set_ammo(Ammo ammo) { _ammo = ammo; }
    public void set_ammoCount(int count) { ammoCount = count; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = Math.max(0, health); }
    
    public boolean canFire() {
        if (_ammo == Ammo.NONE) return false;
        if (_ammo.getMagazineSize() == 999) return true;
        return ammoCount > 0;
    }
    
    public void fire() {
        if (_ammo.getMagazineSize() != 999) {
            ammoCount--;
        }
    }
    
    public void reload() {
        ammoCount = _ammo.getMagazineSize();
    }

    TankState getState(Direction radar, double distance) {
        return new TankState(radar, _turretDirection, _direction, _speed, _ammo, ammoCount, health, distance);
    }

    public abstract Action run(TankState state);
}
```

### TankHandler.java:
```java
package com.example.tankscodecombat;

import android.util.Log;

public class TankHandler {
    private final Tank[] m_tanks;
    private Board m_board;

    public TankHandler(Tank[] tanks) {
        m_tanks = tanks;
        m_board = new Board(m_tanks);
    }

    public int action(int tankId, Action action) {
        if (tankId < 0 || tankId >= m_tanks.length) {
            Log.d("debug", "Invalid tankId: " + tankId);
            return 0;
        }

        Tank tank = m_tanks[tankId];
        boolean canAct = true;
        int result;

        int param = action.getParam();
        switch (action.getType()) {
            case MOVE:
                Tank.Speed[] speeds = Tank.Speed.values();
                if (param < 0 || param >= speeds.length) param = 0;
                tank.set_speed(param);
                break;

            case RELOAD:
                Tank.Ammo[] ammoTypes = Tank.Ammo.values();
                if (param < 0 || param >= ammoTypes.length) param = 0;
                tank.set_ammo(ammoTypes[param]);
                tank.reload();
                break;

            case ROTATE:
                m_tanks[tankId].set_direction(action.getParam());
                break;

            case ROTATE_TURRET:
                m_tanks[tankId].set_turretDirection(action.getParam());
                break;

            case FIRE:
                if (!tank.canFire()) {
                    Log.d("debug", "Tank " + tankId + " cannot fire - no ammo");
                    return 0;
                }
                tank.fire();
                break;

            default:
                Log.d("debug", "Unknown action type: " + action.getType());
                return 0;
        }

        result = m_board.tankActionToBoard(tankId, action, canAct);

        if (result == 0) Log.d("debug", "Tank " + tankId + " action was illegal on the board: " + action.getType());
        else if (result == 1) Log.d("debug", "Tank 1 wins!");
        else if (result == 2) Log.d("debug", "Tank 2 wins!");

        return result;
    }
}
```

### TankState.java:
```java
package com.example.tankscodecombat;

public class TankState {

    public Direction radar;
    public Direction turret;
    public Direction direction;
    public Tank.Speed speed;
    public Tank.Ammo ammo;
    public int ammoCount;
    public int health;
    public double distance;

    public TankState(Direction radar,
                     Direction turret,
                     Direction direction,
                     Tank.Speed speed,
                     Tank.Ammo ammo,
                     int ammoCount,
                     int health,
                     double distance) {

        this.radar = radar;
        this.turret = turret;
        this.direction = direction;
        this.speed = speed;
        this.ammo = ammo;
        this.ammoCount = ammoCount;
        this.health = health;
        this.distance = distance;
    }
}
```

### VisualGame.java:
```java
package com.example.tankscodecombat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualGame extends AppCompatActivity {
    public static final String LOADED_GAME_KEY = "LOADED_GAME";
    private Game game;
    private GameBoardView gameBoard;
    private int gameResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_visual_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gameBoard = findViewById(R.id.gameBoard);

        // Zoom buttons
        Button zoomIn = findViewById(R.id.zoomIn);
        Button zoomOut = findViewById(R.id.zoomOut);
        if (zoomIn != null) zoomIn.setOnClickListener(v -> gameBoard.zoomIn());
        if (zoomOut != null) zoomOut.setOnClickListener(v -> gameBoard.zoomOut());

        // Buttons
        Button skipStart = findViewById(R.id.skipStart);
        Button skipBack10 = findViewById(R.id.skipBack10);
        Button prevButton = findViewById(R.id.prevButton);
        Button nextButton = findViewById(R.id.nextButton);
        Button skipForward10 = findViewById(R.id.skipForward10);
        Button skipEnd = findViewById(R.id.skipEnd);

        skipStart.setOnClickListener(v -> {
            gameBoard.skipToStart(v);
            checkGameStatus();
        });
        prevButton.setOnClickListener(v -> {
            gameBoard.prevMove();
            checkGameStatus();
        });
        nextButton.setOnClickListener(v -> {
            gameBoard.nextMove();
            checkGameStatus();
        });
        skipBack10.setOnClickListener(v -> {
            gameBoard.skipBackward10();
            checkGameStatus();
        });
        skipForward10.setOnClickListener(v -> {
            gameBoard.skipForward10();
            checkGameStatus();
        });
        skipEnd.setOnClickListener(this::skipEnd);


        String jsCode1 = getIntent().getStringExtra(MainActivityFragment.BOT1_CODE_KEY);
        String jsCode2 = getIntent().getStringExtra(MainActivityFragment.BOT2_CODE_KEY);


        boolean isLoadedGame = getIntent().getBooleanExtra(LOADED_GAME_KEY, false);

        if (jsCode1 == null || jsCode2 == null) {
            Toast.makeText(this, "Bot code missing", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isLoadedGame) {
            // Load saved game
            gameResult = getIntent().getIntExtra("GAME_RESULT", -1);
            ArrayList<Map<String, Object>> logsList = (ArrayList<Map<String, Object>>) getIntent().getSerializableExtra("LOGS");
            Logs[] logs = logsListToArray(logsList);
            gameBoard.setLogs(logs);
        } else {
            try {
                // Create JS bots directly from code strings
                Tank bot1 = new JSBotTank(jsCode1);
                Tank bot2 = new JSBotTank(jsCode2);

                // run the Game
                game = new Game(this, bot1, bot2);
                gameResult = game.run();

                // display logs
                Logs[] logs = game.getLog();
                for (Logs l : logs) if (l != null) Log.d("debug", l.toString());

                gameBoard.setLogs(logs);

                // Save game to Firebase Realtime Database
                saveGame(jsCode1, jsCode2, gameResult, logs);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error loading bots: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }

    private void skipEnd(View v) {
        gameBoard.skipToEnd();
        showGameResult();
    }

    private void checkGameStatus() {
        if (gameBoard.isAtEnd()) {
            showGameResult();
        }
    }

    private void showGameResult() {
        String message;
        switch (gameResult) {
            case 1: message = "Tank 1 wins!"; break;
            case 2: message = "Tank 2 wins!"; break;
            case 0: message = "Illegal action!"; break;
            case -1:
            case 3: message = "It's a draw!"; break;
            default: message = "Game Over"; break;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void saveGame(String jsCode1, String jsCode2, int gameResult, Logs[] logs) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Log.d("debug", "User not logged in, cannot save game");
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tankscodecombat-default-rtdb.firebaseio.com");
        String userId = user.getUid();

        // Create a unique key for the new game
        String gameId = database.getReference("users").child(userId).child("games").push().getKey();

        // Create game object
        Map<String, Object> game = new HashMap<>();
        game.put("bot1Code", jsCode1);
        game.put("bot2Code", jsCode2);
        game.put("result", gameResult);

        List<Map<String, Object>> logsList = new ArrayList<>();
        for (Logs l : logs) {
            if (l != null) {
                logsList.add(logToMap(l));
            }
        }
        game.put("logs", logsList);

        // Save to Firebase Realtime Database
        database.getReference("users").child(userId).child("games").child(gameId)
                .setValue(game)
                .addOnSuccessListener(aVoid -> Log.d("debug", "Game saved with ID: " + gameId))
                .addOnFailureListener(e -> Log.d("debug", "Error saving game", e));
    }

    private Map<String, Object> logToMap(Logs log) {
        Map<String, Object> map = new HashMap<>();
        map.put("tankId", log.get_tankId());
        Map<String, Object> actionMap = new HashMap<>();
        actionMap.put("type", log.get_action().getType().toString());
        actionMap.put("param", log.get_action().getParam());
        map.put("action", actionMap);
        Map<String, Object> locationMap = new HashMap<>();
        locationMap.put("x", log.get_location().getX());
        locationMap.put("y", log.get_location().getY());
        map.put("location", locationMap);
        map.put("tankDirection", log.get_tankDirection().getDegrees());
        map.put("turretDirection", log.get_turretDirection().getDegrees());
        map.put("health", log.get_health());
        return map;
    }

    private Logs[] logsListToArray(ArrayList<Map<String, Object>> logsList) {
        Logs[] logsArray = new Logs[logsList.size()];
        for (int i = 0; i < logsList.size(); i++) {
            Map<String, Object> logMap = logsList.get(i);
            Map<String, Object> actionMap = (Map<String, Object>) logMap.get("action");
            Action action = new Action(Action.ActionType.valueOf((String) actionMap.get("type")), ((Number) actionMap.get("param")).intValue());
            Map<String, Object> locationMap = (Map<String, Object>) logMap.get("location");
            Location location = new Location(((Number) locationMap.get("x")).intValue(), ((Number) locationMap.get("y")).intValue());
            Direction tankDirection = new Direction(((Number) logMap.get("tankDirection")).intValue());
            Direction turretDirection = new Direction(((Number) logMap.get("turretDirection")).intValue());
            int health = 100; // default
            if (logMap.containsKey("health")) {
                health = ((Number) logMap.get("health")).intValue();
            }
            Logs log = new Logs(((Number) logMap.get("tankId")).intValue(), action, location, tankDirection, turretDirection, health);
            logsArray[i] = log;
        }
        return logsArray;
    }
}
```

</div>
