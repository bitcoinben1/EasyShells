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

import java.net.*
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

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
/*NETWORKING*/
/*These are helper KTX functions for sending a response if you happen to want to use them. I used them for testing*/

suspend fun Socket.send(data: ByteArray) = getOutputStream().use { writer -> writer.write(data) }

suspend fun SSLSocket.respond(response: String, host: String, port: Int) = SSLSocketFactory.getDefault().createSocket(host, port).use { sock -> sock.send(response.toByteArray()) }

suspend fun Socket.respond(response: String, host: String, port: Int) = Socket(host, port).use { sock -> getOutputStream().write(response.toByteArray()) }

suspend fun DatagramSocket.respond(response: String, host: String, port: Int) = DatagramSocket(0).use { it.send(DatagramPacket(response.toByteArray(), 8124, InetAddress.getByName(host), port)) }

suspend fun MulticastSocket.respond(response: String, host: String, port: Int) = this.apply {
    joinGroup(InetAddress.getByName(host))
    val packet = DatagramPacket(response.toByteArray(), 8124)
    send(packet)
    leaveGroup(InetAddress.getByName(host))
}.close()