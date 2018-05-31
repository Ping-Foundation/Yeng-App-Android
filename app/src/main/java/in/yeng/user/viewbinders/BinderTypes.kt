package `in`.yeng.user.viewbinders

import jp.satorufujiwara.binder.ViewType


enum class BinderTypes : ViewType {

    TYPE_NEWS_UPDATE,
    TYPE_SYLLABUS_DIR,
    TYPE_SYLLABUS_FILE;

    override fun viewType(): Int {
        return ordinal
    }
}