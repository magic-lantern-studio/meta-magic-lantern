SUMMARY = "Magic Lantern Studio libutil utility library."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c71c8102293c2dce5947d7611f986063"

SRCREV = "ced026a568fb0b42b5612f0aab93ca3997e58ccc"
SRC_URI = "git://github.com/magic-lantern-studio/mle-core-util.git"

S = "${WORKDIR}/git"
D = "${TMPDIR}/MagicLantern"

FILES_${PN} += "/opt/MagicLantern/lib/*.so.* /opt/MagicLantern/include/*"
FILES_${PN}-dev += "/opt/MagicLantern/lib/*.so* /opt/MagicLantern/include/*"
FILES_${PN}-staticdev += "/opt/MagicLantern/lib/*.a /opt/MagicLantern/include/*"

#inherit autotools

do_configure() {
    bbdebug 1 "Running libmlutil.bb custom do_configure()"
    export MLE_HOME=${S}
    export MLE_ROOT=${TMPDIR}/MagicLanternRoot/opt/MagicLantern
    cd ${S}/util/linux/build
    libtoolize
    aclocal
    automake --add-missing
    autoconf
    ${S}/util/linux/build/configure --host=arm-poky-linux-gnueabi --prefix=$MLE_ROOT
}

do_compile() {
    bbdebug 1 "Running libmlutil.bb custom do_compile()"
    export MLE_HOME=${S}
    export MLE_ROOT=${TMPDIR}/MagicLanternRoot/opt/MagicLantern
    cd ${S}/util/linux/build
    make
}

do_install() {
    bbdebug 1 "Running libmlutil.bb custom do_install()"
    export MLE_HOME=${S}
    export MLE_ROOT=${TMPDIR}/MagicLanternRoot/opt/MagicLantern
    cd ${S}/util/linux/build
    make install
}
