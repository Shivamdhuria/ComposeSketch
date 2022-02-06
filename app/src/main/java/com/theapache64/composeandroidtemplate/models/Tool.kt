package com.theapache64.composeandroidtemplate.models

sealed interface Tool

object Undo : Tool
object Redo : Tool
object Delete : Tool