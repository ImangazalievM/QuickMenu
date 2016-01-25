# QuickMenu
Небольшая библиотека для создания меню с быстрыми настройками

## Подключение библиотеки

```gradle
compile 'com.github.imangazalievm:quickmenu:0.1.0'
```

Показываем меню:

```java
QuickMenu menu = new QuickMenu.Builder(this).build();
                
menu.show();
```

Дбавляем элементы меню:

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

#Лицензия

    The MIT License

    Copyright (c) 2016 Imangazaliev Mahach

    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
