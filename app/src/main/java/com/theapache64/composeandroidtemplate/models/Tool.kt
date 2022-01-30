package com.theapache64.composeandroidtemplate.models

sealed class Tool

object Pen : Tool()
object Highlighter : Tool()
object Undo : Tool()
object Redo : Tool()
object Delete : Tool()