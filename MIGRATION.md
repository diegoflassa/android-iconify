# Migration from 1.X.X

iconivoid 2.X.X introduces breaking changes, here's the full list:

**In build file**

- `compile 'io.github.diegoflassa.iconivoid.android:android-iconivoid:1.X.X'` -> `compile 'io.github.diegoflassa.iconivoid:android-iconivoid-fontawesome:2.X.X'`

**In layouts**

- `android.widget.IconButton` -> `io.github.diegoflassa.iconivoid.widget.IconButton`
- `android.widget.IconTextView` -> `io.github.diegoflassa.iconivoid.widget.IconTextView`
- `android.widget.IconToggleButton` -> `io.github.diegoflassa.iconivoid.widget.IconToggleButton`

**In code**

- `io.github.diegoflassa.android.iconivoid...` -> `io.github.diegoflassa.iconivoid...`
- `iconivoid.IconValue.fa_something` -> `FontAwesomeIcons.fa_something`
- Nothing in `Application` class -> `iconivoid.with(new FontAwesomeModule())`
