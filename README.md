# QuickMenu 
[ ![Download](https://api.bintray.com/packages/imangazaliev/maven/quickmenu/images/download.svg) ](https://bintray.com/imangazaliev/maven/quickmenu/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-QuickMenu-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3824)

[Русская версия (Russian version)](README-RU.md)

Small library for creating menus with fast settings

## Screenshots

<img src="https://github.com/ImangazalievM/QuickMenu/blob/master/screenshots/1.jpg" width="33%">
<img src="https://github.com/ImangazalievM/QuickMenu/blob/master/screenshots/2.jpg" width="33%">
<img src="https://github.com/ImangazalievM/QuickMenu/blob/master/screenshots/3.png" width="33%">

## Connecting library to project

```gradle
compile 'com.github.imangazalievm:quickmenu:1.1.0'
```

## How to show menu:

File **main.xml**:

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

Our menu will be shown in FrameLayout with ID `quickMenuContainer`

```java
QuickMenu menu = new QuickMenu.Builder(this).build();
menu.show();
```

## Adding new elements to menu

```java
QuickMenu menu = new QuickMenu.Builder(this)
        .withItems(new SpinnerMenuItem(spinnerItems),
                new DividerMenuItem(this),
                new SpinnerMenuItem(spinnerItems))
        .withProperties(properties)
        .build();
```

There are only 3 types of elements:

- **SpinnerMenuItem** - customizable Spinner
- **TextMenuItem** - text
- **DividerMenuItem** - divider line

We also can create custom elements, implementing <b>QuickMenuItem</b> interface:

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

## Changing user-interface

Menu's appearance can be changed using QuickMenuProporeties.

```java
QuickMenuProperties properties = new QuickMenuProperties.Builder(this)
        .withWidthInPercentages(menuWidthInPercentages)
        .withGravity(Gravity.BOTTOM)
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

- **withWidth(int)** - menu width in pixels
- **withWidthInPercentages()** - menu width in percents
- **withGravity()** - menu position
- **withMargins(int, int, int, int)** - outer menu indent
- **withMargins(int, int, int, int)** - inner menu indent
- **withBackground(Drawable)** - menu background
- **withLayoutBackground(Drawable)** - background of area around menu
- **withCancelOnTouchOutside(boolean)** - close menu when touched outside

##Setup listener

You can also setup show/hide listener:

```java
menu.setQuickMenuListener(new QuickMenuListener() {
            @Override
            public void onMenuShowed() {
                Toast.makeText(BottomPanelActivity.this, "Menu showed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuHided() {
                Toast.makeText(BottomPanelActivity.this, "Menu hided", Toast.LENGTH_SHORT).show();
            }
        });
```

## License

The MIT License

Copyright (c) 2016 Mahach Imangazaliev 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
