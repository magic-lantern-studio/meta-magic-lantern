DESCRIPTION = "Apache logging framework for C++ library"
SECTION = "libs"
#DEPENDS = "apr apr-util expat gdbm"
DEPENDS = "apr apr-util"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
HOMEPAGE = "http://logging.apache.org/log4cxx/"
PR = "r0"

SRC_URI = "https://downloads.apache.org/logging/log4cxx/1.1.0/apache-log4cxx-1.1.0.tar.gz"

#SRC_URI = "https://www.apache.org/dyn/closer.cgi/logging/log4cxx/1.2.0/apache-log4cxx-1.2.0.tar.gz"
#SRC_URI = "https://www.apache.org/dyn/closer.cgi/logging/log4cxx/1.2.0/apache-log4cxx-1.2.0.zip"

#build this:
S = "${WORKDIR}/apache-${PN}-${PV}"

#inherit autotools pkgconfig
inherit cmake

SRC_URI[sha256sum] = "1fc7d82697534184bc0f757348d969d24852b948f63d6b17283fd1ee29c2c28a"

#SRC_URI[md5sum] = "b30ffb8da3665178e68940ff7a61084c"
#SRC_URI[sha256sum] = "0de0396220a9566a580166e66b39674cb40efd2176f52ad2c65486c99c920c8c"
#SRC_URI[sha256sum] = "99f4225edb658ccf2f1198dbf94180b65d51b23ad2c1b656ac3ecf4cbb532b2a"
#SRC_URI[sha256sum] = "27211e8b533bd2321724f84fefd6eae2e4963ee355dca67e9c1d9ae4a716f601"


