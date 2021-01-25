package br.com.woodriver.extensions

import com.google.common.io.Resources

fun getResourcePath(resource: String): String =
    Resources.getResource(resource).path