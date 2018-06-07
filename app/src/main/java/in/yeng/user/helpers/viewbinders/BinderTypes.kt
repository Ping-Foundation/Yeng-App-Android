package `in`.yeng.user.helpers.viewbinders

import jp.satorufujiwara.binder.ViewType


enum class BinderTypes : ViewType {

    TYPE_NEWS_UPDATE,
    TYPE_SYLLABUS_DIR,
    TYPE_SYLLABUS_FILE,
    TYPE_TEAM_PROFILES
    ;

    override fun viewType(): Int {
        return ordinal
    }
}