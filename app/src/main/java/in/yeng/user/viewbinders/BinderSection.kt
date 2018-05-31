package `in`.yeng.user.viewbinders

import jp.satorufujiwara.binder.Section

enum class BinderSection : Section {

    SECTION_1;

    override fun position(): Int {
        return ordinal
    }
}