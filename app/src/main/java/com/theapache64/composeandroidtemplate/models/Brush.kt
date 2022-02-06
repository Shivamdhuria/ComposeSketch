package com.theapache64.composeandroidtemplate.models

sealed class Brush : Tool
object Pen : Brush()
object Pencil : Brush()
object Highlighter : Brush()