package com.kodeflap.pico.uri

public data class Resource(
  val resId : Int,
  val mimeType: String = ""
)
public data class Asset(
  val path: String,
  val mimeType: String = ""
)
public data class Internet(
  val path: String,
  val mimeType: String = ""
)
