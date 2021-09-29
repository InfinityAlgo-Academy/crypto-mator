#!/bin/sh

# fix for https://github.com/cryptomator/cryptomator/issues/1370
arch=`uname -m`
export LD_PRELOAD=/usr/lib/${arch}-linux-gnu/jni/libjffi-1.2.so

/usr/lib/cryptomator/bin/cryptomator