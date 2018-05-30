package `in`.yeng.user.Adaptors

import jp.satorufujiwara.binder.ViewType


enum class BinderTypes : ViewType {

    TYPE_NEWS_UPDATE,
    TYPE_SYLLABUS_DIR,
    TYPE_SYLLABUS_FTLES;

    override fun viewType(): Int {
        return ordinal
    }
}