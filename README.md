# GreenDAO : Android ORM
Saving data locally is basic requirement of every Android application. In Android, we can save data mainly in three ways.
  1. SharedPreferences
  2. SQLite
  3. File System
  
The simplest way to save data locally by using the SQLite database and working through `SqliteOpenHelper`. 

This approach requires writing raw queries and manipulation through `cursors`. 

It becomes difficult to manage when codebase becomes large and it is also possible to more manual errors.

#### So how do we solve our problems?

The solution is `Android ORM(Object-Relational Mapping)`.

#### What is ORM?

ORM is a programming technique for converting data between relational databases and object oriented programming languages. When we work with an object-oriented system, there is a mismatch between the object model and the relational database. RDBMSs represent data in a tabular format whereas object-oriented languages, such as Java or C# represent it as an interconnected graph of objects.

#### In Android there're many ORM
1. GreenDAO
2. OrmLite
3. Room Persistence Library
4. SugarORM
5. Realm
and many more...

# In this repository we learn about GreenDAO

### What is DAO and GreenDAO exactly :confused:? 

DAO is `Data Access Objects`.

greenDAO is an `open source` Android ORM making development for SQLite databases easy. It relieves developers from dealing with low-level database requirements while saving development time. greenDAO frees you from writing SQL and parsing query results, which are quite tedious and time-consuming tasks, by mapping `Java objects to database tables` called ORM. This way you can store, update, delete, and query for Java objects using a simple object-oriented API.


#### greenDAO 3 uses Annotation Processing to generate the DAO classes.

### So the question is how do we start  ?

>Here is the app build using [greenDAO](http://greenrobot.org/greendao/)step by step for you to understand.

### Here is step to USE greenDAO in Android.
- [ ] Add Gradle dependency in app/build.gradle.
- [ ] Add GreenDAO Gradle plugin for the Annotation processing in the root project build.gradle.
- [ ] Then use this plugin in the app/build.gradle, below com.android.application plugin
- [ ] GreenDAO requires us to create the schema of the table in the form of a class with greenDAO annotations.
- [ ] Explore the DAO generated classes at app/build/generated/source/greendao. We can see few classes in this folder.
- [ ] To use the database we need to construct the DaoSession Object in Application Class.
- [ ] Test the table in the Main activity.
- [ ] specify the greendao schema version of the database in the app/build.gradle.


### So, Let's start the coding.
- [X] Add Gradle dependency in app/build.gradle.

```
Open app/build.gradle and this lines.
ext{
    greenDao = '3.2.2'
}
dependencies {
    implementation "org.greenrobot:greendao:$greenDao"
}
```

- [X] Add GreenDAO Gradle plugin for the Annotation processing in the root project build.gradle.

```
buildscript {
    repositories {
        jcenter()
        mavenCentral() // add repository
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2'
    }
}
```

- [X] Then use this plugin in the app/build.gradle, below com.android.application plugin
```
apply plugin: 'com.android.application'
apply plugin: 'org.greenrobot.greendao'
```

- [X] GreenDAO requires us to create the schema of the table in the form of a class with greenDAO annotations. 

##### This is basic JAVA POJO model but when we use GreenDAO annotations as abstract layer so the pojo work as database table.

```
@Entity(nameInDb = "noteEntity")
public class NoteModel {

    @Id(autoincrement = true)
    @Property(nameInDb = "noteId")
    private Long id;

    @Property(nameInDb = "note")
    private String note;

    @Property(nameInDb = "createdAt")
    private Long createdAt;

    @Property(nameInDb = "lastUpdatedAt")
    private Long lastUpdatedAt;
    }
```


 ``` generate getter, setters, and constructors for this class.```
 
 #### 1. @Entity: It defines the table name in the database.
 #### 2 .@Id: It defines the primary key of the table & autoincrement parameter show that value is creating automatic and never use once its use.
 #### 3 .@Property: It defines the row name in the note table.
 
 >Here is more [greenDAO annotation](http://greenrobot.org/files/greendao/javadoc/3.1/org/greenrobot/greendao/annotation/package-summary.html).
