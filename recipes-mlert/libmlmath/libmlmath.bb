SUMMARY = "Magic Lantern Studio libmlmath math library."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c71c8102293c2dce5947d7611f986063"

SRCREV = "22f928893a9f3de02b645c59ce64125f1c59817d"
SRC_URI = "git://github.com/magic-lantern-studio/mle-core-math.git"

S = "${WORKDIR}/git"
D = "${TMPDIR}/MagicLantern"

FILES_${PN} += "/opt/MagicLantern/lib/*.so.* /opt/MagicLantern/include/*"
FILES_${PN}-dev += "/opt/MagicLantern/lib/*.so* /opt/MagicLantern/include/*"
FILES_${PN}-staticdev += "/opt/MagicLantern/lib/*.a /opt/MagicLantern/include/*"

#inherit autotools

DEPENDS += "libmlutil"

SYSROOT_DIRS += "${D}/MagicLantern/opt/MagicLantern"

do_configure() {
    bbdebug 1 "Running libmlmath.bb custom do_configure()"
    export MLE_HOME=${S}
    export MLE_ROOT=${D}/opt/MagicLantern
    cd ${S}/math/linux/libmlmath
    libtoolize
    aclocal
    automake --add-missing
    autoconf
    ${S}/math/linux/libmlmath/configure --host=arm-poky-linux-gnueabi --prefix=$MLE_ROOT
}

do_compile() {
    bbdebug 1 "Running libmlmath.bb custom do_compile()"
    export MLE_HOME=${S}
    export MLE_ROOT=${D}/opt/MagicLantern
    cd ${S}/math/linux/libmlmath
    make
}

do_install_append() {
    bbdebug 1 "Running libmlmath.bb custom do_install()"
    export MLE_HOME=${S}
    export MLE_ROOT=${D}/opt/MagicLantern
    #cd ${S}/math/linux/libmlmath
    #make install ${D}
    cd ${S}/math/linux/libmlmath/libmlmath
    mkdir -p ${D}/opt/MagicLantern/lib
    ../arm-poky-linux-gnueabi-libtool --mode=install install -c libmlmath.la ${D}/opt/MagicLantern/lib
}
