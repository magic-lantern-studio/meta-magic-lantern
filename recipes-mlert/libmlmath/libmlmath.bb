SUMMARY = "Magic Lantern Studio libmlmath math library."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1f2a1f7f66f76e9f9f74a6a293f06b13"

SRCREV = "22f928893a9f3de02b645c59ce64125f1c59817d"
SRC_URI = "git://github.com/magic-lantern-studio/mle-core-math.git"

S = "${WORKDIR}/git"
D = "${TMPDIR}/MagicLanternRoot"

FILES_${PN} += "\
    /opt/MagicLantern/lib/*.so.* \
    /opt/MagicLantern/include/math/*"
FILES_${PN}-dev += "\
    /opt/MagicLantern/lib/*.so* \
    /opt/MagicLantern/include/math/*"
FILES_${PN}-staticdev += "\
    /opt/MagicLantern/lib/*.a \
    /opt/MagicLantern/include/math/*"

#inherit autotools

#DEPENDS += "libmlutil"
do_compile[depends] = "libmlutil:do_install"

#SYSROOT_DIRS += "${D}/opt/MagicLanternRoot"

do_configure() {
    bbdebug 1 "Running libmlmath.bb custom do_configure()"
    bbdebug 1 "D =" ${D}
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
    bbdebug 1 "D =" ${D}
    export MLE_HOME=${S}
    export MLE_ROOT=${D}/opt/MagicLantern
    cd ${S}/math/linux/libmlmath
    make
}

#do_install_append() {
do_install() {
    bbdebug 1 "Running libmlmath.bb custom do_install()"
    bbdebug 1 "D =" ${D}
    export MLE_HOME=${S}
    export MLE_ROOT=${D}/opt/MagicLantern
    #cd ${S}/math/linux/libmlmath
    #make install
    cd ${S}/math/linux/libmlmath/libmlmath
    mkdir -p ${D}/opt/MagicLantern/lib
    ../arm-poky-linux-gnueabi-libtool --mode=install install -c libmlmath.la ${D}/opt/MagicLantern/lib
    mkdir -p ${D}/opt/MagicLantern/include/math
    cp ${S}/math/common/include/math/*.h ${D}/opt/MagicLantern/include/math
}
