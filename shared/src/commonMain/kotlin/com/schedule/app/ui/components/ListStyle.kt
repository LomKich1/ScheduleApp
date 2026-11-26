package com.schedule.app.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

// ─── Общие размеры карточек списков ──────────────────────────────────────────

/** Высота карточек файлов, групп и преподавателей. */
val ListCardMinHeight = 72.dp

/** Зазор между карточками внутри слитной группы (файлы, звонки). */
val GroupedListGap = 2.dp

private val GroupedOuterCorner = 20.dp
private val GroupedInnerCorner = 4.dp

/**
 * Форма карточки в слитной группе: внешние углы группы (верх первой и низ
 * последней карточки) скруглены сильно, все стыки между соседями — слабо.
 * Единственный элемент списка получает большое скругление со всех сторон.
 */
fun groupedListShape(index: Int, count: Int): Shape {
    val top = if (index == 0) GroupedOuterCorner else GroupedInnerCorner
    val bottom = if (index == count - 1) GroupedOuterCorner else GroupedInnerCorner
    return RoundedCornerShape(topStart = top, topEnd = top, bottomEnd = bottom, bottomStart = bottom)
}
