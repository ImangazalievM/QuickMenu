# QuickMenu
[ ![Download](https://api.bintray.com/packages/imangazaliev/maven/quickmenu/images/download.svg) ](https://bintray.com/imangazaliev/maven/quickmenu/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-QuickMenu-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3824)

Небольшая библиотека для создания меню с быстрыми настройками

##Скриншоты

<img src="https://github.com/ImangazalievM/QuickMenu/blob/master/screenshots/1.jpg" width="33%">
<img src="https://github.com/ImangazalievM/QuickMenu/blob/master/screenshots/2.jpg" width="33%">
<img src="https://github.com/ImangazalievM/QuickMenu/blob/master/screenshots/3.jpg" width="33%">

## Подключение библиотеки

```gradle
compile 'com.github.imangazalievm:quickmenu:0.1.0'
```

##Показываем меню:

Файл **main.xml**:

```java
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="showMenu"
        android:text="Show menu"
        android:layout_centerInParent="true"
        />

    <FrameLayout
        android:id="@id/quickMenuContainer"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
```

Во FrameLayout'е с идентификатором `quickMenuContainer` будет показано наше меню.

```java
QuickMenu menu = new QuickMenu.Builder(this).build();
menu.show();
```

##Добавление элементов

```java
QuickMenu menu = new QuickMenu.Builder(this)
        .withItems(new SpinnerMenuItem(spinnerItems),
                new DividerMenuItem(this)),
                new SpinnerMenuItem(spinnerItems))
        .withProperties(properties)
        .build();
```

В библиотеке есть только 3 типа элементов:

- **SpinnerMenuItem** - настраиваемый Spinner
- **TextMenuItem** - текст
- **DividerMenuItem** - разделительная линия

Также можно добавить свои элементы реализовав интерфейс <b>QuickMenuItem</b>:

```java
public class CustomMenuItem implements QuickMenuItem {

    public CustomItem() {
    }

    @Override
    public View getView(Context context, ViewGroup parent) {
        
        MyCustomView view = ...
        
        //создаем View
        
        return view;
    }
}
```

##Настройка внешнего вида

Внешний вид меню можно настроить с помощью QuickMenuProperties:

```java
QuickMenuProperties properties = new QuickMenuProperties.Builder(this)
        .withWidthInPercentages(menuWidthInPercentages)
        .withBackground(drawable)
        .withMargins(left, top, right, bottom)
        .withLayoutBackground(drawable)
        .withCancelOnTouchOutside(true)
        .build();

QuickMenu menu = new QuickMenu.Builder(this)
        .withItems(new SpinnerMenuItem(spinnerItems),
                new DividerMenuItem(this).withColor(Color.parseColor("#FFA19348")),
                new SpinnerMenuItem(spinnerItems))
        .withProperties(properties)
        .build();
        
menu.show();
```

- **withWidth(int)** - ширина меню в пикселях
- **withWidthInPercentages()** - ширина меню в процентах
- **withMargins(int, int, int, int)** - отступы меню
- **withBackground(Drawable)** - фон меню
- **withLayoutBackground(Drawable)** - фон области вокруг меню
- **withCancelOnTouchOutside(boolean)** - закрытие при нажатии на область за границей меню

##Лицензия

The MIT License

Copyright (c) 2016 Mahach Imangazaliev 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
