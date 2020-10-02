/*Copyright 2019 Chris Basinger

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/
package com.evilthreads.easyshells

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.InputStream
import java.util.concurrent.TimeUnit
/*
            (   (                ) (             (     (
            )\ ))\ )    *   ) ( /( )\ )     (    )\ )  )\ )
 (   (   ( (()/(()/(  ` )  /( )\()|()/((    )\  (()/( (()/(
 )\  )\  )\ /(_))(_))  ( )(_)|(_)\ /(_))\((((_)( /(_)) /(_))
((_)((_)((_|_))(_))   (_(_()) _((_|_))((_))\ _ )(_))_ (_))
| __\ \ / /|_ _| |    |_   _|| || | _ \ __(_)_\(_)   \/ __|
| _| \ V /  | || |__    | |  | __ |   / _| / _ \ | |) \__ \
|___| \_/  |___|____|   |_|  |_||_|_|_\___/_/ \_\|___/|___/
....................../´¯/)
....................,/¯../
.................../..../
............./´¯/'...'/´¯¯`·¸
........../'/.../..../......./¨¯\
........('(...´...´.... ¯~/'...')
.........\.................'...../
..........''...\.......... _.·´
............\..............(
..............\.............\...
*/
private const val _SHELL_PATH = "system/bin/sh"
suspend fun shell(cmd: String): String {
    val process = Runtime.getRuntime().exec(_SHELL_PATH)
    val result =  process.start(cmd)
    return result
}

private fun Process.start(cmd: String): String {
    write(cmd)
    val input = read()
    destroy()
    return input
}

private fun Process.write(value: String){
    outputStream.bufferedWriter().use { writer ->
        writer.write(value)
        writer.flush()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun Process.read(): String {
    return StringBuffer().let { buf ->
        buf.readStream(errorStream)
        buf.readStream(inputStream)
        //test this with a ping or some other time consuming operation
        waitFor(20, TimeUnit.SECONDS)
        buf.appendln("EXIT VALUE: ${exitValue()}")
    }.toString()
}

private fun StringBuffer.readStream(stream: InputStream) = stream.bufferedReader().useLines { it.forEach { ln -> this.appendln(ln) } }
