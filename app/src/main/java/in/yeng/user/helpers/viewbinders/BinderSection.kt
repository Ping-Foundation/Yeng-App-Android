package `in`.yeng.user.helpers.viewbinders

import jp.satorufujiwara.binder.Section

enum class BinderSection : Section {

    SECTION_1,
    SECTION_2;

    override fun position(): Int {
        return ordinal
    }
}