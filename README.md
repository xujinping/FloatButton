# FloatButton
浮动按钮

#使用
整个布局使用如下

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:test="http://schemas.android.com/apk/res-to/com.example.xjp.test.MyTextView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin">

    <com.xjp.library.FloatButton
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:tag="sticky"
            android:text="folatButton" />

    </com.xjp.library.FloatButton>

</RelativeLayout>

#注意点
浮动的按钮 需要设置  android:tag="sticky"
浮动按钮需要被 <com.xjp.library.FloatButton/>布局包裹。
